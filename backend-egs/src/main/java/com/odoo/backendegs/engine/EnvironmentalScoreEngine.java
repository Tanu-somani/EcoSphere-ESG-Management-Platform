package com.odoo.backendegs.engine;

import org.springframework.stereotype.Component;

@Component
public class EnvironmentalScoreEngine {

    public double calculateScore(double totalCarbonEmission) {

        if (totalCarbonEmission <= 100) {
            return 100.0;
        }

        double score = 100 - (totalCarbonEmission / 10.0);

        return Math.max(score, 0);
    }
}
