# -*- coding: utf-8 -*-
import logging
from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

# Load environment variables (.env)
load_dotenv()

# Import the existing AIService
from ai_service import AIService

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
_logger = logging.getLogger(__name__)

# Initialize FastAPI App with Swagger configurations enabled by default
app = FastAPI(
    title="EcoSphere ESG AI Copilot API",
    description="REST API service exposing the grounded ESG Copilot reasoning system.",
    version="1.0.0",
    docs_url="/docs",
    redoc_url="/redoc"
)

# Enable Cross-Origin Resource Sharing (CORS) for frontend integration
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=False,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Request schema
class ChatRequest(BaseModel):
    question: str = Field(
        ..., 
        description="The ESG or sustainability-related question to ask the Copilot.",
        examples=["Explain Manufacturing department performance"]
    )

# Response schema
class ChatResponse(BaseModel):
    answer: str = Field(..., description="The grounded analysis response returned from the Copilot.")

@app.get("/health", status_code=status.HTTP_200_OK, summary="Check API Service Health")
async def health_check():
    """
    Returns the current operational status of the Copilot service.
    """
    return {
        "status": "running",
        "service": "EcoSphere AI Copilot"
    }

@app.post("/api/ai/chat", response_model=ChatResponse, summary="Submit a query to the ESG Copilot")
async def ask_copilot(request: ChatRequest):
    """
    Exposes the existing grounded ESG Copilot reasoning pipeline.
    Reuses AIService directly without duplicating prompt compiling or client invocation logic.
    """
    question = request.question.strip()
    if not question:
        _logger.warning("Received empty question payload")
        return JSONResponse(
            status_code=status.HTTP_400_BAD_REQUEST,
            content={"success": False, "message": "Question parameter cannot be empty"}
        )
    
    try:
        _logger.info("Processing Copilot question: %s", question)
        
        # Instantiate and invoke the existing orchestrator
        ai_service = AIService()
        answer = ai_service.get_response(question)
        
        return ChatResponse(answer=answer)
        
    except Exception as e:
        _logger.exception("Exception occurred while processing Copilot query")
        # Return error structure as requested
        return JSONResponse(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            content={"success": False, "message": f"AI service error: {str(e)}"}
        )
