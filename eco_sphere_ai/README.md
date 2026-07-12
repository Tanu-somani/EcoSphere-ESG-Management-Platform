# EcoSphere ESG AI Copilot - Standalone Python Service

This project implements a framework-independent, standalone Python AI Copilot service for the **EcoSphere - ESG Management Platform** hackathon MVP. It is designed to be easily embeddable into any Python backend (like Odoo, Flask, or FastAPI) without changes to the core AI logic.

---

## 1. Directory Structure

```text
eco_sphere_ai/
├── app.py                # Command Line Interface (CLI) test runner
├── ai_service.py         # Main orchestrator service (router, loader, generation)
├── gemini_client.py      # Standalone API client for Gemini 3.5 Flash
├── context_router.py     # Classifies query intent and filters JSON blocks
├── context_loader.py     # Parses mock/ai_context.json for static context
├── prompt_builder.py     # Compiles prompts by merging context and templates
├── prompts/              # System instruction and prompt text templates
│   ├── system_prompt.txt
│   ├── dashboard_summary.txt
│   ├── department_analysis.txt
│   ├── recommendation.txt
│   └── audit_summary.txt
├── mock/
│   └── ai_context.json   # Mock JSON containing mock ESG platform data
├── requirements.txt      # Python dependencies list
└── .env                  # Environment configuration (ignored by Git)
```

---

## 2. Request & Execution Lifecycle

When you query the Copilot (e.g. `python3 app.py "What is our overall ESG score?"`):

```
User Question
     │
     ▼
[1] app.py (Entry)
     │
     ▼
[2] ai_service.py (Orchestrator)
     │
     ├──► [3] context_loader.py  ──► Loads full mock/ai_context.json
     │
     ├──► [4] context_router.py  ──► Filters JSON context based on intent
     │                                 (Dashboard / Carbon / Department / Audits)
     │
     ├──► [5] prompt_builder.py
     │         - Reads prompts/system_prompt.txt
     │         - Reads specific prompts/*.txt
     │         - Merges FILTERED JSON & question into template
     │
     └──► [6] gemini_client.py
               - Imports `google-genai` client
               - Reads API key from `.env`
               - Invokes Gemini 3.5 Flash via live API
                     │
                     ▼
             Returned Markdown Response
```

---

## 3. Executive Response Schema

Every answer returned by the AI Copilot strictly conforms to the following executive presentation format:

1. **📊 Executive Summary**: A high-impact 3-5 bullet point overview summarizing key metrics and risks.
2. **Evidence & Analysis**: Direct comparisons utilizing exact numbers from the data payload (rendered in Markdown tables for multi-dimensional data).
3. **Cross-reference Context**: Logical connections connecting emissions, scores, goals, and compliance issues.
4. **✅ Recommendations**: Actionable suggestions referencing specific metrics, goal IDs, and target dates.
5. **⚠️ Missing Information**: Transparency disclosure of any metrics or calculations not provided in the dataset to prevent hallucination.
6. **Confidence Level**: A rating of **High | Medium | Low** with a checklist detailing exactly which context files were consumed to build the response.

---

## 4. Setup and Installation

### Step 1: Install Dependencies
Run the following command in your terminal to install the official Google GenAI SDK and environment loader:
```bash
pip install -r requirements.txt
```

### Step 2: Configure Gemini API Key
Create a `.env` file inside the `eco_sphere_ai` folder:
```env
GEMINI_API_KEY=AIzaSy...your-gemini-api-key
```
*(This file is added to `.gitignore` and will never be committed to GitHub).*

---

## 5. How to Test the Copilot

Run the CLI application by passing your question as an argument:

### Test Case A: Overall Dashboard Summary
```bash
python3 app.py "What is our overall ESG score?"
```

### Test Case B: Department Performance Analysis
```bash
python3 app.py "How is the Manufacturing department doing?"
```

### Test Case C: Actionable Recommendations
```bash
python3 app.py "What improvements do you suggest to boost our rating?"
```

### Test Case D: Audits & Compliance
```bash
python3 app.py "Are there any overdue compliance violations?"
```
