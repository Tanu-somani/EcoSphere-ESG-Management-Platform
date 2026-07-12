package com.odoo.backendegs.engine;

import org.springframework.stereotype.Component;

@Component
public class GovernanceScoreEngine {


    public double calculateScore(Integer unresolvedIssues) {

        if (unresolvedIssues == null) {
            return 100;
        }

        double score = 100 - (unresolvedIssues * 10);

        return Math.max(score, 0);
    }
}
