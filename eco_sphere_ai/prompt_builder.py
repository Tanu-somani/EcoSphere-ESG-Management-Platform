# -*- coding: utf-8 -*-
import os
import json
import logging

_logger = logging.getLogger(__name__)

class PromptBuilder:
    """
    Standalone PromptBuilder loading filesystem templates.
    Converts user questions into structured tasks with strict execution rules
    for the Data Analyst LLM.
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

    def _compile_task(self, task_description: str, context: dict, question: str) -> tuple:
        system_prompt = self._read_template("system_prompt.txt")
        context_str = json.dumps(context, indent=2)
        
        user_prompt = f"""Task:
{task_description}
User Query: "{question}"

Context JSON Data:
{context_str}

Strict Rules:
1. Use ONLY the provided context values. Do not invent departments, scores, or carbon metrics.
2. Do NOT assume or claim causal relationships between values unless explicitly stated in the context.
3. Use cautious language (e.g. "suggests", "indicates", "does not explicitly establish").
4. If context is missing for any part of the query, list it under "Missing Information".
"""
        return system_prompt, user_prompt

    def build_dashboard_prompt(self, context: dict, question: str) -> tuple:
        task = "Analyze the organizational dashboard summary metrics. Identify key indicators, values, and status flags."
        return self._compile_task(task, context, question)

    def build_department_prompt(self, context: dict, question: str) -> tuple:
        task = "Compare performance scores across departments. Highlight scoring disparities in Environmental, Social, and Governance pillars."
        return self._compile_task(task, context, question)

    def build_recommendation_prompt(self, context: dict, question: str) -> tuple:
        task = "Formulate priority actions. Recommend specific actions linked strictly to evidence of delay, overdues, or low scores in the data."
        return self._compile_task(task, context, question)

    def build_audit_prompt(self, context: dict, question: str) -> tuple:
        task = "Summarize recent audit scores, findings, and list active compliance issues sorted by severity (High, Medium, Low)."
        return self._compile_task(task, context, question)
