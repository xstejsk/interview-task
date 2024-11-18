package com.phonecompany.billing.model;

import com.phonecompany.billing.CostCalculator;
import com.phonecompany.billing.impl.promo.PromoEvent;
import com.phonecompany.billing.impl.CostCalculatorImpl;
import com.phonecompany.billing.impl.strategy.WorkHoursCostStrategy;
import com.phonecompany.billing.impl.promo.MostFrequentFreePromoEvent;
import com.phonecompany.util.PhoneLogUtil;
import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.util.List;

public class PhoneLogTest extends TestCase {

    WorkHoursCostStrategy workHoursCostStrategy = new WorkHoursCostStrategy();
    CostCalculator costCalculator = CostCalculatorImpl.getInstance(workHoursCostStrategy);

    public void testGetTotalCost_isFree() {
        List<PhoneCall> phoneCalls = List.of(
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator)
        );
        PhoneLog log = new PhoneLog(phoneCalls);
        log.getPhoneCalls().forEach(phoneCall -> phoneCall.setCostCalculator(CostCalculatorImpl.getInstance(new WorkHoursCostStrategy())));
        PromoEvent promoEvent = new MostFrequentFreePromoEvent(PhoneLogUtil.getMostFrequentLargestPhoneNumber(log));
        log.setPromoEvent(promoEvent);
        assertEquals(0.0, log.getTotalCost().doubleValue());
    }

    public void testGetTotalCost_oneMinuteWorkHours() {
        List<PhoneCall> phoneCalls = List.of(
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("523456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator)
        );
        PhoneLog log = new PhoneLog(phoneCalls);
        log.getPhoneCalls().forEach(phoneCall -> phoneCall.setCostCalculator(CostCalculatorImpl.getInstance(new WorkHoursCostStrategy())));
        PromoEvent promoEvent = new MostFrequentFreePromoEvent(PhoneLogUtil.getMostFrequentLargestPhoneNumber(log));
        log.setPromoEvent(promoEvent);
        assertEquals(1.0, log.getTotalCost().doubleValue());
    }

    public void testGetTotalCost_ignoreBiggerNumber() {
        List<PhoneCall> phoneCalls = List.of(
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:04:00.000Z"), DateTime.parse("2018-01-01T09:07:00.000Z"), costCalculator),
                new PhoneCall("523456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("523456789", DateTime.parse("2018-01-01T09:05:00.000Z"), DateTime.parse("2018-01-01T09:07:00.000Z"), costCalculator)
        );
        PhoneLog log = new PhoneLog(phoneCalls);
        log.getPhoneCalls().forEach(phoneCall -> phoneCall.setCostCalculator(CostCalculatorImpl.getInstance(new WorkHoursCostStrategy())));
        PromoEvent promoEvent = new MostFrequentFreePromoEvent(PhoneLogUtil.getMostFrequentLargestPhoneNumber(log));
        log.setPromoEvent(promoEvent);
        assertEquals(4.0, log.getTotalCost().doubleValue());
    }

    public void testGetTotalCost_oneMinuteOutsideWorkHours() {
        List<PhoneCall> phoneCalls = List.of(
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator),
                new PhoneCall("523456789", DateTime.parse("2018-01-01T17:00:00.000Z"), DateTime.parse("2018-01-01T17:01:00.000Z"), costCalculator)
        );
        PhoneLog log = new PhoneLog(phoneCalls);
        log.getPhoneCalls().forEach(phoneCall -> phoneCall.setCostCalculator(CostCalculatorImpl.getInstance(new WorkHoursCostStrategy())));
        PromoEvent promoEvent = new MostFrequentFreePromoEvent(PhoneLogUtil.getMostFrequentLargestPhoneNumber(log));
        log.setPromoEvent(promoEvent);
        assertEquals(0.5, log.getTotalCost().doubleValue());
    }
}