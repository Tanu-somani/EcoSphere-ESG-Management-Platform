# -*- coding: utf-8 -*-
import os
import logging
from google import genai
from google.genai import errors

_logger = logging.getLogger(__name__)

class GeminiClient:
    """
    A standalone reusable client wrapper for Gemini 2.5 Flash using the official Google GenAI SDK.
    """
    def __init__(self, api_key=None):
        """
        Initialize the Gemini Client.
        :param api_key: Explicit API key to override environment settings.
        """
        self.api_key = api_key or os.getenv("GEMINI_API_KEY")
        if not self.api_key:
            raise ValueError("Gemini API key is missing. Please set the GEMINI_API_KEY environment variable.")

        try:
            # Initialize the official Google GenAI Client
            self.client = genai.Client(api_key=self.api_key)
        except Exception as e:
            _logger.error("Failed to initialize Google GenAI Client: %s", str(e))
            raise RuntimeError(f"Failed to initialize GenAI Client: {str(e)}")

    def generate_response(self, prompt: str, system_instruction: str = None) -> str:
        """
        Generates a text response for the given prompt using Gemini 3.5 Flash.
        
        :param prompt: The raw prompt text containing context and query
        :param system_instruction: Optional system level guidelines
        :return: Clean markdown text response from the model
        """
        if not prompt:
            return "Error: Prompt cannot be empty."

        config = {}
        if system_instruction:
            config["system_instruction"] = system_instruction

        try:
            # Generate content using Gemini 3.5 Flash
            response = self.client.models.generate_content(
                model='gemini-3.5-flash',
                contents=prompt,
                config=config if config else None
            )
            
            if not response or not response.text:
                return "Error: Gemini returned an empty response."
                
            return response.text

        except errors.APIError as e:
            error_msg = f"Gemini API Error: {e.message} (Code: {e.code})"
            _logger.error(error_msg)
            return f"Error: {error_msg}"
        except Exception as e:
            error_msg = f"Unexpected error during generation: {str(e)}"
            _logger.error(error_msg)
            return f"Error: {error_msg}"
