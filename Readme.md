# 🌱 EcoSphere - ESG Management Platform

EcoSphere is an ESG (Environmental, Social, and Governance) Management Platform built as an Odoo module for the Odoo Hackathon.

The platform helps organizations measure, monitor, and improve their ESG performance by integrating sustainability directly into daily business operations. Instead of manually entering ESG data, EcoSphere transforms business activities into meaningful ESG metrics and provides actionable insights through dashboards, reports, AI, and secure governance workflows.

---

# 📖 Problem Statement

Organizations already use ERP systems like Odoo for:

- Purchasing
- Manufacturing
- Fleet Management
- Human Resources
- Expenses
- Inventory

However, these systems do not provide a unified view of an organization's Environmental, Social, and Governance performance.

EcoSphere bridges this gap by collecting operational data, processing it into ESG metrics, and presenting management with real-time sustainability insights.

---

# 🎯 Project Goal

The primary objective of EcoSphere is to:

- Measure ESG performance
- Monitor sustainability goals
- Encourage employee participation
- Improve governance compliance
- Provide meaningful reports
- Help management make data-driven sustainability decisions

---

# 🏗️ Core Architecture

```
Daily Business Operations
        │
        ▼
 EcoSphere Backend Engine
        │
        ├──────── Environmental
        ├──────── Social
        ├──────── Governance
        │
        ▼
 ESG Score Calculation Engine
        │
        ▼
 Dashboard & Reports
        │
   ┌────┴────┐
   ▼         ▼
 AI Copilot Blockchain
```

---

# 🌍 Environmental Module

The Environmental module is responsible for tracking an organization's environmental impact.

### Features

- Emission Factors
- Carbon Transactions
- Environmental Goals
- Department Carbon Tracking

### Example Workflow

```
Purchase Diesel

↓

Emission Factor

↓

Carbon Calculation

↓

Carbon Transaction Created

↓

Department Environmental Score Updated
```

---

# 👥 Social Module

The Social module focuses on employee participation and sustainability initiatives.

### Features

- CSR Activities
- Employee Participation
- Sustainability Challenges
- XP System
- Badges
- Rewards
- Leaderboard

### Example Workflow

```
Employee joins CSR Activity

↓

Uploads Proof

↓

Manager Approves

↓

XP Awarded

↓

Social Score Updated

↓

Leaderboard Updated
```

---

# 🏛 Governance Module

The Governance module helps organizations monitor compliance and audits.

### Features

- ESG Policies
- Policy Acknowledgements
- Audits
- Compliance Issues

### Example Workflow

```
Audit Created

↓

Compliance Issues Found

↓

Issue Assigned

↓

Issue Resolved

↓

Governance Score Updated
```

---

# 📊 ESG Score Engine

EcoSphere revolves around the ESG Score Engine.

Every activity contributes to one of the three ESG pillars.

```
Environmental Score

+

Social Score

+

Governance Score

↓

Overall ESG Score
```

The ESG Score is displayed throughout the platform and forms the basis for reporting and analytics.

---

# 🤖 AI Copilot (REST API & CLI Service)

EcoSphere includes a standalone, decoupled AI Copilot built using Python and Google's Gemini 3.5 Flash API to help management analyze ESG metrics. The AI does **not** perform calculations; instead, it acts as a reasoning layer over structured backend data.

### Features
* **Interactive Chat Companion**: Pruned layouts optimized for live chat widgets.
* **Grounded Responses**: Restricts analysis strictly to the provided context to prevent hallucinations.
* **No Causal Assumptions**: Uses qualitative analyst language ("indicates", "suggests") to ensure reporting standards.
* **FastAPI Server**: REST API endpoints for easy integration with frontends and external services.

### API Architecture
```text
User Question
      │
      ▼
   FastAPI (main.py) / CLI (app.py)
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
```

### Installation & Run

Navigate to the `eco_sphere_ai` directory and configure the environment:
```bash
cd eco_sphere_ai
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt
```

Create a `.env` file inside `eco_sphere_ai/`:
```env
GEMINI_API_KEY=your_gemini_api_key
```

#### Run as a REST API Server (Uvicorn / FastAPI):
```bash
uvicorn main:app --port 8000 --reload
```
* **Swagger UI Docs**: Accessible at `http://localhost:8000/docs`.
* **Health Check**: `GET http://localhost:8000/health`
* **Chat Endpoint**: `POST http://localhost:8000/api/ai/chat` taking `{"question": "..."}`.

#### Run as a CLI Application:
```bash
python3 app.py "What is our overall ESG score?"
```

### Example Questions
* *What is our overall ESG score?*
* *Explain Manufacturing department performance.*
* *Compare Manufacturing with Logistics.*
* *Which compliance issue is most critical?*
* *Show delayed environmental goals.*

---

# ⛓️ Blockchain (MVP)

Blockchain is used only for Governance.

When an audit is completed:

```
Audit Completed

↓

Generate SHA-256 Hash

↓

Store Hash on Blockchain

↓

Receive Transaction Hash

↓

Save Transaction Hash
```

This provides immutable proof that completed audits have not been modified.

---

# 📈 Dashboard

The Dashboard provides a centralized view of the organization's ESG performance.

### Displays

- Overall ESG Score
- Environmental Score
- Social Score
- Governance Score
- Carbon Trends
- Department Rankings
- Recent Activities
- Active Challenges
- Pending Audits

---

# 📄 Reports

The platform supports generation of:

- Environmental Report
- Social Report
- Governance Report
- ESG Summary Report
- Custom Report Builder

Reports can be filtered by:

- Department
- Employee
- Date Range
- Challenge
- ESG Category

---

# ⚙️ Core Technologies

- Odoo
- Python
- PostgreSQL
- Blockchain (Audit Verification)
- AI Copilot (FastAPI / Gemini)
- JavaScript
- XML Views

---

# 👥 Team Responsibilities

## Backend

Responsible for:

- Domain Models
- Business Logic
- Carbon Calculation Engine
- ESG Score Engine
- Report Generation
- AI Context Preparation
- Blockchain Integration

---

## AI

Responsible for:

- ESG Copilot REST API
- Grounded prompt engineering
- Real-time department insights & recommendations
- Exposing Uvicorn HTTP endpoints for frontend consumption

---

## Frontend

Responsible for:

- Dashboard
- User Interface
- Forms
- Charts
- Reports
- User Experience

---

## Blockchain

Responsible for:

- Audit Hash Storage
- Audit Verification
- Smart Contract
- Transaction Hash Generation

---

# 🚀 Future Scope

Possible future enhancements include:

- Carbon Credit Marketplace
- ESG Prediction Engine
- IoT Sensor Integration
- Automated Sustainability Recommendations
- Mobile Application
- Multi-Organization Support

---

# 📌 Project Philosophy

EcoSphere is not a traditional CRUD application.

Instead, it is an event-driven ESG processing platform.

```
Business Activity

↓

ESG Processing

↓

Department Score

↓

Organization Score

↓

Dashboard

↓

Reports

↓

AI Insights
```

Every feature in the platform ultimately contributes toward helping organizations become more sustainable and make better ESG decisions.

---

# 👥 Authors

* **AI Lead & Solutions Architect**: *[Yashvi Kaushik]*