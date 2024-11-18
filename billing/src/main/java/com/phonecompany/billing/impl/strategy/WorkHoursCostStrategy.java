package com.phonecompany.billing.impl.strategy;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.math.BigDecimal;

public class WorkHoursCostStrategy implements CallCostStrategy {

    @Override
    public BigDecimal calculateCost(DateTime startTime, DateTime endTime) {

        if (startTime.isAfter(endTime)) {
            return BigDecimal.ZERO;
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        DateTime currentTime = startTime;
        int startedMinute = 1;
        while (currentTime.isBefore(endTime)) {
            if (startedMinute > 5) {
                totalCost = totalCost.add(BigDecimal.valueOf(0.20));
            } else {
                if (isInWorkHours(currentTime)) {
                    totalCost = totalCost.add(BigDecimal.valueOf(1.00));
                } else {
                    totalCost = totalCost.add(BigDecimal.valueOf(0.50));
                }
            }
            currentTime = currentTime.plusMinutes(1);
            startedMinute++;
        }

        return totalCost;
    }

    private boolean isInWorkHours(DateTime time) {
        LocalTime startWork = LocalTime.parse("08:00:00"); // 08:00:00 inclusive
        LocalTime endWork = LocalTime.parse("16:00:00");  // 16:00:00 exclusive
        LocalTime currentTime = time.toLocalTime();
        return !currentTime.isBefore(startWork) && currentTime.isBefore(endWork);
    }
}