package com.odoo.backendegs.engine;

import org.springframework.stereotype.Component;

@Component
public class CarbonCalculationEngine {

    public double calculateCarbonEmission(double quantity,
                                          double emissionFactor) {

        return quantity * emissionFactor;
    }

}
