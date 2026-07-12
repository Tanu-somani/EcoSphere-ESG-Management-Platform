# -*- coding: utf-8 -*-
import sys
from dotenv import load_dotenv
from ai_service import AIService

def main():
    load_dotenv()  # Load GEMINI_API_KEY from local .env file if available
    
    if len(sys.argv) < 2:
        print("Usage: python3 app.py \"Your ESG question here\"")
        print("Example: python3 app.py \"Explain our carbon emissions and goals\"")
        sys.exit(1)
        
    question = sys.argv[1]
    
    try:
        # Initialize orchestrating service (picks up API key from env automatically)
        service = AIService()
        
        # Query AI Copilot
        response = service.ask(question)
        
        print("\n=== ESG AI Copilot Response ===\n")
        print(response)
        print("\n================================\n")
        
    except Exception as e:
        print(f"Error running AI Copilot: {str(e)}", file=sys.stderr)
        sys.exit(1)

if __name__ == '__main__':
    main()
