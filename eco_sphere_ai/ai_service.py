# -*- coding: utf-8 -*-
import logging
from context_loader import ContextLoader
from prompt_builder import PromptBuilder
from gemini_client import GeminiClient
from context_router import ContextRouter

_logger = logging.getLogger(__name__)

class AIService:
    """
    Main orchestration service for the ESG AI Copilot.
    Handles routing user query types to specific prompt builders, loading context,
    routing context intelligently to save tokens, and invoking Gemini.
    """
    def __init__(self, api_key=None, mock_path=None):
        self.context_loader = ContextLoader(mock_path=mock_path)
        self.prompt_builder = PromptBuilder()
        self.context_router = ContextRouter()
        self.gemini_client = GeminiClient(api_key=api_key)

    def ask(self, question: str) -> str:
        """
        Processes a user question, loads context, routes/filters context blocks,
        builds the prompt, invokes Gemini, and returns the response.
        """
        # 1. Load full context
        full_context = self.context_loader.load_context()
        if not full_context:
            return "Error: Could not load ESG context data."

        # 2. Intelligently filter/route context based on question intent
        routed_context = self.context_router.route_context(question, full_context)

        # 3. Match question category to prompt builder
        question_lower = question.lower()
        
        if any(kw in question_lower for kw in ["department", "manufacturing", "logistics", "admin"]):
            system_prompt, user_prompt = self.prompt_builder.build_department_prompt(routed_context, question)
        elif any(kw in question_lower for kw in ["recommend", "improve", "suggest", "goal", "action"]):
            system_prompt, user_prompt = self.prompt_builder.build_recommendation_prompt(routed_context, question)
        elif any(kw in question_lower for kw in ["audit", "compliance", "policy", "issue", "violation"]):
            system_prompt, user_prompt = self.prompt_builder.build_audit_prompt(routed_context, question)
        else:
            # Default to dashboard summary / general analysis
            system_prompt, user_prompt = self.prompt_builder.build_dashboard_prompt(routed_context, question)

        # 4. Call Gemini
        _logger.info("Invoking Gemini 3.5 Flash...")
        response = self.gemini_client.generate_response(user_prompt, system_instruction=system_prompt)
        return response
