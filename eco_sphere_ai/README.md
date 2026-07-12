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
├── prompt_builder.py     # Wraps queries into structured Tasks with execution rules
├── prompts/              # System instruction and prompt templates
│   └── system_prompt.txt # Strict Data Analyst instructions and qualifiers
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
     ├──► [3] context_loader.py  ──► Loads mock/ai_context.json
     │
     ├──► [4] context_router.py  ──► Filters JSON context based on intent
     │                                 (Dashboard / Carbon / Department / Audits)
     │
     ├──► [5] prompt_builder.py
     │         - Reads prompts/system_prompt.txt
     │         - Wraps question into a structured Task (with strict rules)
     │         - Merges FILTERED JSON context
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

## 3. Strict Data Analyst Persona & Rules

To ensure reliable, objective, and audit-safe analysis, the Copilot behaves as a **Data Analyst** operating under strict guidelines:

* **Strict Data Grounding**: Base all statements strictly on the JSON payload. If data is missing, the AI explicitly states it and refuses to speculate or generate metrics from memory.
* **No Causal Claims**: The AI is prohibited from asserting causal connections (e.g., avoiding words like "causes", "results in", "therefore", "because" unless explicitly supported by JSON data).
* **Defensive Reporting Language**: Prompts mandate qualifiers such as:
  * *"The dataset suggests..."*
  * *"The dataset indicates..."*
  * *"Based on available information..."*
* **Task-Based Prompting**: Rather than executing raw queries, the `PromptBuilder` translates every query into a structured instruction set (e.g. mapping, comparison, validation tasks) backed by strict data-grounding rules.

---

## 4. Concise Response Layout

All conversational answers strictly conform to the following short dashboard format (100–200 words):

1. **📊 Summary**: Exactly one sentence summarizing the core takeaway and decision value.
2. **📌 Key Metrics**: A short plain text key-value list of 3-5 KPI parameters relevant ONLY to the query.
3. **🔍 Key Insights**: Maximum 3 bullets, exactly one sentence each, using qualifiers.
4. **🎯 Recommended Actions**: Maximum 3 bullets, short, action-oriented, referencing exact data codes.
5. **💡 Suggested Questions**: Maximum 3 follow-up questions dynamically generated.
6. **✅ Confidence**: A single-line status rating (**High | Medium | Low**) referencing the JSON sections used.

---

## 5. Setup and Installation

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

## 6. How to Test the Copilot

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
