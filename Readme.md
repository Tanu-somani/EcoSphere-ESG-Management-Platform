# EcoSphere AI Copilot

EcoSphere AI Copilot is an intelligent ESG (Environmental, Social, and Governance) assistant developed for the Odoo Hackathon. Powered by Python and Google's Gemini API, the Copilot assists corporate executives in analyzing sustainability data directly from their dashboard interface. 

To maintain strict data integrity, the AI Copilot does **not** perform calculations or access database models directly. All carbon footprints, compliance classifications, and scores are processed in advance by the backend. The AI acts exclusively as a reasoning layer, consuming structured context payloads to provide data-driven summaries, performance audits, and actionable recommendations.

---

## Features

* **AI-Powered ESG Assistant**: Natural language query interface tailored for executive decision support.
* **Strict Context Grounding**: Reasoning is constrained strictly to the provided data scope to eliminate hallucinations.
* **Comprehensive ESG Summarization**: High-level corporate ESG status reports generated on demand.
* **Carbon footprint Analysis**: Translates raw emissions transactions into actionable carbon assessments.
* **Department Performance Audits**: Evaluates performance, headcounts, and risk rankings across operational departments.
* **Goal Progress Tracking**: Monitored tracking of Environmental Goals (e.g. status delays, percentage completions).
* **Compliance & Risk Monitoring**: Tracks active compliance issues, overdue dates, and severity flags.
* **Actionable Recommendation Engine**: Directs users toward timed operations tasks (Immediate, This Quarter, Long Term) using context data.
* **Task-Based Prompt Design**: Maps user queries into structured analyst directives (e.g. comparative ranking, audit profiling).

---

## Architecture

The AI Copilot operates as a clean, decoupled service:

```text
User Question
      │
      ▼
   app.py (CLI Entry Point)
      │
      ▼
Context Loader ◄───[Reads]─── Mock JSON Context (mock/ai_context.json)
      │
      ▼
Prompt Builder ◄───[Reads]─── System Prompt Instructions
      │
      ▼
 Gemini Client
      │
      ▼
  Gemini API (Gemini 3.5 Flash Model)
      │
      ▼
   Response
```

### Component Breakdown
* **`app.py`**: The entry point managing the command line user loop and environment initialization.
* **`context_loader.py`**: Responsible for loading the active structured data state.
* **`prompt_builder.py`**: Formulates questions into explicit tasks, merging the system instructions and data context.
* **`gemini_client.py`**: API Gateway wrapping the official Google GenAI Python SDK.

---

## Project Structure

```text
eco_sphere_ai/
├── app.py                  # CLI executable script and user entry point
├── ai_service.py           # Core orchestrator service managing pipeline execution
├── context_router.py       # Intent classifier that prunes context payloads to minimize token size
├── context_loader.py       # Reads and loads the structured ESG context
├── prompt_builder.py       # Compiles context data and questions into structured Tasks
├── gemini_client.py        # Communicates with Google's Gemini API (Gemini 3.5 Flash)
├── prompts/
│   └── system_prompt.txt   # Strict Data Analyst system instructions and guardrails
├── mock/
│   └── ai_context.json     # Decoupled static JSON contract for offline development
├── requirements.txt        # Package dependencies (google-genai, python-dotenv)
└── .env                    # Secure local environment credentials (ignored by Git)
```

---

## How It Works

1. **User Query**: The user asks a question about ESG data.
2. **Context Resolution**: The `ContextLoader` reads the structured dataset. The AI does not query databases directly, preventing SQL injection or unauthorized access.
3. **Task-Based Prompt Compilation**: The `PromptBuilder` takes the user question, matches it to an intent, compiles it into a structured task with strict rules, and injects the context JSON.
4. **API Call**: The `GeminiClient` forwards the compiled prompt and system guidelines to Gemini 3.5 Flash.
5. **Formated Response**: The generated markdown response is returned to the user interface.

---

## Development Mode

During development, the Copilot operates in **Development Mode**, loading the static database contract [mock/ai_context.json](file:///Users/yashvikaushik/Documents/Documents/eco_sphere_ai/EcoSphere-ESG-Management-Platform/eco_sphere_ai/mock/ai_context.json). This allows the frontend and AI teams to iterate on prompts, workflows, and response layouts concurrently without waiting for backend development to complete.

---

## Backend Integration

In production, `context_loader.py` is configured to call `GET /api/ai/context` from the backend service instead of reading the mock JSON. Because the API returns the exact same JSON schema as the mock file, no prompt engineering or AI orchestration logic needs to change.

```text
Backend REST API
       │
       ▼
GET /api/ai/context
       │
       ▼
Context Loader
       │
       ▼
Prompt Builder
       │
       ▼
  Gemini API
```

---

## API Contract

The REST endpoint `GET /api/ai/context` must return a JSON object structured exactly like the development mock contract:

| Section Name | JSON Key | Data Type | Description |
| :--- | :--- | :---: | :--- |
| **Organization Summary** | `organization_summary` | Object | Standard company name, industry, and scale details. |
| **Dashboard Summary** | `dashboard_summary` | Object | Overall ESG rating, score, and core corporate metrics. |
| **Departments** | `departments` | Array | Department metadata (names, heads, and staff counts). |
| **Department Scores** | `department_scores` | Array | Dimensional scores (E, S, G) mapped to each department. |
| **Carbon Transactions** | `carbon_transactions` | Array | Individual Scope 1/2/3 transaction records. |
| **Environmental Goals** | `environmental_goals` | Array | Targets, target dates, progress ratios, and status values. |
| **CSR Activities** | `csr_activities` | Array | Budget allocations and impact statistics. |
| **Employee Participation**| `employee_participation`| Object | Volunteering statistics and active ratios. |
| **Challenges** | `challenges` | Array | Sustainability challenges, dates, and active flags. |
| **Challenge Participation**| `challenge_participation`| Object | Completed counts and estimated carbon savings. |
| **Audits** | `audits` | Array | Auditor names, audit dates, scores, and findings counts. |
| **Compliance Issues** | `compliance_issues` | Array | Severity level, descriptions, status, and due dates. |
| **Policies** | `policies` | Array | Policy titles, versions, and revision dates. |

---

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/Tanu-somani/EcoSphere-ESG-Management-Platform.git
cd EcoSphere-ESG-Management-Platform/eco_sphere_ai
```

### 2. Create Virtual Environment & Install Dependencies
```bash
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt
```

---

## Environment Variables

Create a `.env` file in the `eco_sphere_ai` directory to manage your Gemini API credentials safely:

```env
GEMINI_API_KEY=your_actual_gemini_api_key
```

---

## Running the Copilot

Run queries directly from your CLI terminal:

```bash
python3 app.py "What is our overall ESG score?"
```

---

## Example Questions

### Dashboard summaries
1. *What is our overall ESG score?*
2. *Show our current ESG rating.*
3. *Provide an executive summary of our dashboard.*

### Departmental Performance
4. *Explain Manufacturing department performance.*
5. *Compare Manufacturing with Logistics.*
6. *Which department has the lowest Environmental score?*
7. *Who leads the Corporate Administration department and how are they performing?*

### Carbon footprint & Goals
8. *Summarize our carbon footprint.*
9. *What is our total CO2 emissions count?*
10. *Show all delayed environmental goals.*
11. *What is our progress on GOAL-003?*
12. *Detail our Scope 1 vs. Scope 3 emissions.*

### Audits & Compliance
13. *Which compliance issue is most critical?*
14. *Are there any overdue compliance violations?*
15. *Summarize our Q1 environmental audit findings.*
16. *Who audited our compliance status on 2026-03-22?*

### Strategic Actions & CSR
17. *What should management prioritize immediately?*
18. *Recommend ESG improvements based on our scores.*
19. *What is the budget allocation for CSR reforestation?*
20. *How much carbon did we save from challenges?*

---

## AI Design Principles

* **Grounded AI**: The model acts strictly on the provided context, preventing hallucinations.
* **Decoupled Architecture**: Calculations remain on the Odoo backend; the AI functions only as a translator and analyst.
* **No Direct DB Access**: Keeps database transactions isolated and secure.
* **Task-Based Restructuring**: All inputs are wrapped in structured commands to ensure programmatic consistency in responses.
* **Qualitative Analysis**: Uses neutral qualifiers ("suggests", "indicates") to maintain analysis standards.

---

## Current Limitations

* **Mock Payload Dependency**: Currently relies on filesystem JSON for testing until the backend endpoint goes live.
* **Stateless Conversations**: Does not maintain conversation memory threads between independent executions.
* **No Response Streaming**: Returns the complete response block at once without character streaming.
* **No Built-in Authentication**: Relies on host environment authorization.

---

## Future Improvements

* **Production Endpoint Switch**: Connect to live Odoo backend APIs.
* **Conversation History**: Integrate in-memory or database thread logging.
* **Streaming Responses**: Enable token streaming for UI chat widgets.
* **RAG (Retrieval-Augmented Generation)**: Allow searching policy documents dynamically.
* **Voice Assistant**: Integrate speech-to-text input pipelines.

---

## Tech Stack

* **Language**: Python 3.12+
* **AI Model**: Google Gemini 3.5 Flash
* **SDK**: Official Google GenAI Python SDK
* **Data Format**: Structured JSON

---

## Authors

* **AI Lead & Solutions Architect**: *[Your Name / Team Placeholders]*