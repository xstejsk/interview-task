package com.phonecompany.billing.impl.promo;

import com.phonecompany.billing.model.PhoneCall;

import java.math.BigDecimal;

public class MostFrequentFreePromoEvent implements PromoEvent {

    private final String mostFrequentLargestPhoneNumber;

    public MostFrequentFreePromoEvent(String mostFrequentLargestPhoneNumber) {
        this.mostFrequentLargestPhoneNumber = mostFrequentLargestPhoneNumber;
    }

    @Override
    public BigDecimal applyPromo(PhoneCall phoneCall) {
        if (mostFrequentLargestPhoneNumber != null && mostFrequentLargestPhoneNumber.equals(phoneCall.getRecipientNumber())) {
            return BigDecimal.ZERO;
        }
        return phoneCall.getCost();
    }
}
