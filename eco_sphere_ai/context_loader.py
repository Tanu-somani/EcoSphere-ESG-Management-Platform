# -*- coding: utf-8 -*-
import json
import os
import logging

_logger = logging.getLogger(__name__)

class ContextLoader:
    """
    Loads ESG context JSON file from mock/ai_context.json.
    """
    def __init__(self, mock_path=None):
        current_dir = os.path.dirname(os.path.abspath(__file__))
        self.mock_path = mock_path or os.path.join(current_dir, 'mock/ai_context.json')

    def load_context(self) -> dict:
        """
        Reads and returns the mock/ai_context.json.
        """
        if not os.path.exists(self.mock_path):
            _logger.error("Mock context path not found: %s", self.mock_path)
            return {}

        try:
            with open(self.mock_path, 'r', encoding='utf-8') as f:
                return json.load(f)
        except Exception as e:
            _logger.error("Error reading context JSON: %s", str(e))
            return {}
