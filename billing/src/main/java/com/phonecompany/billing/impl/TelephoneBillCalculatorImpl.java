package com.phonecompany.billing.impl;

import com.phonecompany.billing.TelephoneBillCalculator;
import com.phonecompany.billing.impl.promo.MostFrequentFreePromoEvent;
import com.phonecompany.billing.impl.strategy.WorkHoursCostStrategy;
import com.phonecompany.billing.model.PhoneLog;
import com.phonecompany.util.PhoneLogUtil;

import java.math.BigDecimal;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {

    @Override
    public BigDecimal calculate(String phoneLog) {
        return createPhoneLog(phoneLog).getTotalCost();
    }

    private PhoneLog createPhoneLog(String phoneLog) {
        PhoneLog log = new PhoneLog(new PhoneLogParser().parse(phoneLog));
        log.getPhoneCalls().forEach(phoneCall -> phoneCall.setCostCalculator(CostCalculatorImpl.getInstance(new WorkHoursCostStrategy())));
        log.setPromoEvent(new MostFrequentFreePromoEvent(PhoneLogUtil.getMostFrequentLargestPhoneNumber(log)));
        return log;
    }
}
