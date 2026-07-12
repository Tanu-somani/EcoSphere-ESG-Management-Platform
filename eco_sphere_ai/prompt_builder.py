# -*- coding: utf-8 -*-
import os
import json
import logging

_logger = logging.getLogger(__name__)

class PromptBuilder:
    """
    Standalone PromptBuilder loading filesystem templates.
    Responsible for compiling concise, conversational prompts for the live
    Executive ESG Copilot, supporting adaptive layouts and strict word limits.
    """
    def __init__(self):
        current_dir = os.path.dirname(os.path.abspath(__file__))
        self.prompts_dir = os.path.join(current_dir, 'prompts')

    def _read_template(self, filename: str) -> str:
        path = os.path.join(self.prompts_dir, filename)
        if not os.path.exists(path):
            _logger.warning("Template file not found: %s", path)
            return ""
        try:
            with open(path, 'r', encoding='utf-8') as f:
                return f.read()
        except Exception as e:
            _logger.error("Failed to read template: %s", str(e))
            return ""

    def _compile(self, template_name: str, context: dict, question: str) -> tuple:
        system_prompt = self._read_template("system_prompt.txt")
        template = self._read_template(template_name)
        
        context_str = json.dumps(context, indent=2)
        try:
            user_prompt = template.format(context=context_str, question=question)
        except Exception as e:
            _logger.error("Formatting error in template %s: %s", template_name, str(e))
            user_prompt = f"Context:\n{context_str}\n\nQuestion: {question}"
            
        return system_prompt, user_prompt

    def build_dashboard_prompt(self, context: dict, question: str) -> tuple:
        return self._compile("dashboard_summary.txt", context, question)

    def build_department_prompt(self, context: dict, question: str) -> tuple:
        return self._compile("department_analysis.txt", context, question)

    def build_recommendation_prompt(self, context: dict, question: str) -> tuple:
        return self._compile("recommendation.txt", context, question)

    def build_audit_prompt(self, context: dict, question: str) -> tuple:
        return self._compile("audit_summary.txt", context, question)
