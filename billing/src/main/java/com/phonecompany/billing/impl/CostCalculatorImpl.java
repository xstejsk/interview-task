package com.phonecompany.billing.impl;

import com.phonecompany.billing.impl.strategy.CallCostStrategy;
import com.phonecompany.billing.CostCalculator;
import com.phonecompany.billing.model.PhoneCall;

import java.math.BigDecimal;

public class CostCalculatorImpl implements CostCalculator {

    private CallCostStrategy costStrategy;

    private static CostCalculatorImpl instance;

    public void setCostStrategy(CallCostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    private CostCalculatorImpl() {
        if (instance != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    public static CostCalculatorImpl getInstance(CallCostStrategy costStrategy) {
        if (instance == null) {
            instance = new CostCalculatorImpl();
        }
        instance.setCostStrategy(costStrategy);
        return instance;
    }

    @Override
    public BigDecimal calculateCost(PhoneCall phoneCall) {
        return costStrategy.calculateCost(phoneCall.getStartTime(), phoneCall.getEndTime());
    }
}
