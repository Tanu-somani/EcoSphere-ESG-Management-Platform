# -*- coding: utf-8 -*-

class ContextRouter:
    """
    Intelligent context router that classifies a user's question into an intent
    and filters the raw context JSON to include only the relevant data blocks.
    This reduces token payload size, execution latency, and LLM hallucinations.
    """
    INTENT_MAPPING = {
        'dashboard': ['organization_summary', 'dashboard_summary'],
        'department': ['departments', 'department_scores'],
        'carbon': ['carbon_transactions', 'environmental_goals'],
        'goals': ['environmental_goals'],
        'csr': ['csr_activities', 'employee_participation'],
        'challenges': ['challenges', 'challenge_participation'],
        'audit': ['audits', 'compliance_issues'],
        'governance': ['policies', 'compliance_issues'],
        'recommendation': ['organization_summary', 'dashboard_summary', 'department_scores', 'environmental_goals', 'compliance_issues']
    }

    def route_context(self, question: str, full_context: dict) -> dict:
        """
        Classifies the question intent and filters the context dictionary.
        
        :param question: The user's query
        :param full_context: The full ESG context dictionary
        :return: A filtered context dictionary matching the identified intent
        """
        intent = self._classify_intent(question)
        target_keys = self.INTENT_MAPPING.get(intent, ['organization_summary', 'dashboard_summary'])
        
        # Build filtered context payload containing only relevant keys
        filtered_context = {}
        for key in target_keys:
            if key in full_context:
                filtered_context[key] = full_context[key]
                
        return filtered_context

    def _classify_intent(self, question: str) -> str:
        """
        Classifies the query string into one of the known target intents based on keyword rules.
        """
        q = question.lower()
        
        # 1. Recommendation
        if any(w in q for w in ['recommend', 'improve', 'suggest', 'action', 'remedy', 'solve']):
            return 'recommendation'
            
        # 2. Department
        if any(w in q for w in ['department', 'manufacturing', 'logistics', 'admin', 'sarah', 'marcus', 'elena']):
            return 'department'
            
        # 3. Carbon
        if any(w in q for w in ['carbon', 'emission', 'co2', 'scope', 'txn', 'transaction']):
            return 'carbon'
            
        # 4. Goals
        if any(w in q for w in ['goal', 'target', 'progress', 'on track', 'delayed']):
            return 'goals'
            
        # 5. CSR
        if any(w in q for w in ['csr', 'social', 'volunteer', 'community', 'reforestation', 'stem']):
            return 'csr'
            
        # 6. Challenges
        if any(w in q for w in ['challenge', 'paperless', 'commute', 'xp', 'participation']):
            return 'challenges'
            
        # 7. Audit
        if any(w in q for w in ['audit', 'ecoveritas', 'finding']):
            return 'audit'
            
        # 8. Governance / Compliance
        if any(w in q for w in ['governance', 'policy', 'policies', 'compliance', 'violation', 'issue']):
            return 'governance'
            
        # Default fallback
        return 'dashboard'
