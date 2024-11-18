package com.phonecompany.billing.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.phonecompany.billing.CostCalculator;
import com.phonecompany.util.DateTimeConverter;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Objects;

public class PhoneCall {
    @CsvBindByPosition(position = 0)
    private String recipientNumber;

    @CsvCustomBindByPosition(position = 1, converter = DateTimeConverter.class)
    private DateTime startTime;

    @CsvCustomBindByPosition(position = 2, converter = DateTimeConverter.class)
    private DateTime endTime;

    private CostCalculator costCalculator;

    public PhoneCall() {
    }

    public PhoneCall(String recipientNumber, DateTime startTime, DateTime endTime, CostCalculator costCalculator) {
        this.recipientNumber = recipientNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.costCalculator = costCalculator;
    }

    public void setCostCalculator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    public BigDecimal getCost() {
        return costCalculator.calculateCost(this);
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneCall phoneCall = (PhoneCall) o;
        return Objects.equals(recipientNumber, phoneCall.recipientNumber) && Objects.equals(startTime, phoneCall.startTime) && Objects.equals(endTime, phoneCall.endTime) && Objects.equals(costCalculator, phoneCall.costCalculator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipientNumber, startTime, endTime, costCalculator);
    }
}
