package com.odoo.backendegs.engine;

import org.springframework.stereotype.Component;

@Component
public class SocialScoreEngine {

    public double calculateScore(Integer totalXP) {

        if (totalXP == null) {
            return 0;
        }

        double score = totalXP / 10.0;

        return Math.min(score, 100);
    }


}
