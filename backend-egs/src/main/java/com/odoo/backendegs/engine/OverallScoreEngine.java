package com.odoo.backendegs.engine;

import org.springframework.stereotype.Component;

@Component
public class OverallScoreEngine {

    public double calculateOverallScore(double environmental,
                                        double social,
                                        double governance) {

        return (environmental + social + governance) / 3.0;
    }
}
