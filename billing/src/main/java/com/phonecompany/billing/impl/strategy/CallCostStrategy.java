package com.phonecompany.billing.impl.strategy;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public interface CallCostStrategy {

    BigDecimal calculateCost(DateTime startTime, DateTime endTime);
}
