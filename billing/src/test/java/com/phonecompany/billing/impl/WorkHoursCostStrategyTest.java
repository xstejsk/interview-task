package com.phonecompany.billing.impl;


import com.phonecompany.billing.CostCalculator;
import com.phonecompany.billing.impl.strategy.WorkHoursCostStrategy;
import com.phonecompany.billing.model.PhoneCall;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class WorkHoursCostStrategyTest {

    WorkHoursCostStrategy workHoursCostStrategy = new WorkHoursCostStrategy();
    CostCalculator costCalculator = CostCalculatorImpl.getInstance(workHoursCostStrategy);

    @Test
    public void calculateCost() {
        PhoneCall phoneCallOne = new PhoneCall("123456789", DateTime.parse("2018-01-01T09:00:00.000Z"), DateTime.parse("2018-01-01T09:01:00.000Z"), costCalculator);
        assertEquals(new BigDecimal("1.0"), phoneCallOne.getCost());

        PhoneCall phoneCallTwo = new PhoneCall("123456789", DateTime.parse("2018-01-01T08:00:00.000Z"), DateTime.parse("2018-01-01T08:05:00.000Z"), costCalculator);
        assertEquals(new BigDecimal("5.0"), phoneCallTwo.getCost());

        PhoneCall phoneCallThree = new PhoneCall("123456789", DateTime.parse("2018-01-01T08:00:00.000Z"), DateTime.parse("2018-01-01T08:06:00.000Z"), costCalculator);
        assertEquals(new BigDecimal("5.2"), phoneCallThree.getCost());

        PhoneCall phoneCallFour = new PhoneCall("123456789", DateTime.parse("2018-01-01T15:58:00.000Z"), DateTime.parse("2018-01-01T16:01:20.000Z"), costCalculator);
        assertEquals(new BigDecimal("3.0"), phoneCallFour.getCost());

    }

}