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

# 🤖 AI Copilot

EcoSphere includes an AI Copilot to help management understand ESG data.

The AI does **not** perform calculations.

Instead, it analyzes processed backend data and provides natural language explanations.

### Example Questions

- Why did Manufacturing's ESG score decrease?
- Give me today's ESG Summary.
- How can we improve our ESG Score?
- Which department requires immediate attention?

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
- AI Copilot
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

## Frontend

Responsible for:

- Dashboard
- User Interface
- Forms
- Charts
- Reports
- User Experience

---

## AI

Responsible for:

- ESG Copilot
- ESG Summary
- Recommendations
- Dashboard Insights

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