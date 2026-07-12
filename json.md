# EcoSphere - ESG Management Platform

EcoSphere is an ESG (Environmental, Social, Governance) Management Platform built using **Spring Boot** as the backend. It helps organizations measure, monitor, and improve their ESG performance by converting business events into meaningful ESG metrics.

## Features

### 🌱 Environmental

- Resource Management
- Emission Factor Management
- Carbon Transaction Tracking
- Automatic Carbon Calculation
- Department Environmental Score

### 👥 Social

- CSR Activities
- Employee Participation
- XP & Gamification
- Badge System
- Department Social Score

### 🏛 Governance

- Policies
- Audits
- Compliance Issues
- Governance Score

### 📊 ESG Dashboard

- Environmental Score
- Social Score
- Governance Score
- Overall ESG Score
- Department Statistics

---

# Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- ModelMapper
- Lombok
- Swagger/OpenAPI
- Maven

---

# Project Structure

```
controller
│
├── department
├── environmental
├── social
├── governance
└── dashboard

service
│
├── department
├── environmental
├── social
├── governance
├── dashboard
└── esg

entity
│
├── department
├── environmental
├── social
├── governance
└── esg

repo

dto
│
├── request
└── response

engine

exception

config
```

---

# ESG Flow

```
Business Activity

↓

Environmental / Social / Governance

↓

Department Score

↓

Overall ESG Score

↓

Dashboard
```

---

# APIs

## Department APIs

### Create Department

```
POST /api/departments
```

### Get Department

```
GET /api/departments/{id}
```

### Get All Departments

```
GET /api/departments
```

---

## Resource APIs

### Create Resource

```
POST /api/resources
```

### Get Resource

```
GET /api/resources/{id}
```

### Get All Resources

```
GET /api/resources
```

---

## Carbon Transaction APIs

### Create Carbon Transaction

```
POST /api/carbon
```

### Get Transaction

```
GET /api/carbon/{id}
```

### Get All Transactions

```
GET /api/carbon
```

### Get Department Transactions

```
GET /api/carbon/department/{departmentId}
```

### Get Total Department Carbon

```
GET /api/carbon/department/{departmentId}/total
```

---

## CSR APIs

### Create CSR Activity

```
POST /api/csr
```

### Get All CSR Activities

```
GET /api/csr
```

### Join CSR Activity

```
POST /api/csr/join
```

### Approve Participation

```
PUT /api/csr/approve/{participationId}
```

### Reject Participation

```
PUT /api/csr/reject/{participationId}
```

---

## Audit APIs

### Create Audit

```
POST /api/audit
```

### Create Compliance Issue

```
POST /api/audit/issue
```

### Resolve Compliance Issue

```
PUT /api/audit/issue/{issueId}/resolve
```

### Get All Audits

```
GET /api/audit
```

---

## Dashboard API

### Department Dashboard

```
GET /api/dashboard/{departmentId}
```

---

# Sample JSON Objects

---

## Create Department

```json
{
  "name": "Manufacturing",
  "description": "Handles production operations."
}
```

---

## Create Resource

```json
{
  "name": "Diesel",
  "category": "FUEL",
  "unit": "LITRE",
  "description": "Diesel used by manufacturing units.",
  "emissionFactor": 2.68
}
```

---

## Create Carbon Transaction

```json
{
  "departmentId": 1,
  "resourceId": 1,
  "quantity": 100,
  "transactionDate": "2026-07-12"
}
```

---

## Create CSR Activity

```json
{
  "title": "Tree Plantation Drive",
  "description": "Plant trees in nearby communities.",
  "xpReward": 100,
  "startDate": "2026-07-15",
  "endDate": "2026-07-20",
  "status": "UPCOMING"
}
```

---

## Join CSR Activity

```json
{
  "employeeName": "John Doe",
  "departmentId": 1,
  "csrActivityId": 1,
  "proofUrl": "https://example.com/tree.jpg",
  "remarks": "Participated with the manufacturing team."
}
```

---

## Create Audit

```json
{
  "title": "Quarterly Environmental Audit",
  "description": "Review environmental compliance.",
  "departmentId": 1,
  "auditDate": "2026-07-20"
}
```

---

## Create Compliance Issue

```json
{
  "auditId": 1,
  "departmentId": 1,
  "title": "Diesel Storage Violation",
  "description": "Improper storage of diesel barrels.",
  "severity": "HIGH"
}
```

---

# ESG Score Calculation

## Environmental Score

Calculated based on total carbon emissions.

```
Score = max(0, 100 - (TotalCarbon / 10))
```

---

## Social Score

Calculated based on department XP.

```
Score = min(100, TotalXP / 10)
```

---

## Governance Score

Calculated based on unresolved compliance issues.

```
Score = max(0, 100 - (OpenIssues × 10))
```

---

## Overall ESG Score

```
(Environmental Score + Social Score + Governance Score) / 3
```

---

# Business Flow

## Environmental

```
Resource

↓

Carbon Transaction

↓

Carbon Engine

↓

Environmental Score

↓

Department Score
```

---

## Social

```
CSR Activity

↓

Participation

↓

XP

↓

Badge

↓

Social Score
```

---

## Governance

```
Audit

↓

Compliance Issue

↓

Governance Score
```

---

# Future Enhancements

- AI ESG Copilot
- Blockchain Audit Verification
- ESG Report Generation (PDF/Excel)
- Notifications
- Analytics Dashboard
- Carbon Reduction Goals
- ESG Trends & Insights

---

# Team

**EcoSphere** was developed as an ESG Management Platform for the Odoo Hackathon, focusing on transforming everyday business operations into measurable ESG insights through a clean backend architecture and modular design.