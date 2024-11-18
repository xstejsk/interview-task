package com.phonecompany.billing;

import java.math.BigDecimal;

public interface TelephoneBillCalculator {

    BigDecimal calculate(String phoneLog);
}
