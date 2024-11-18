package com.phonecompany.billing;

import com.phonecompany.billing.model.PhoneCall;

import java.math.BigDecimal;

public interface CostCalculator {

    BigDecimal calculateCost(PhoneCall phoneCall);
}
