package com.phonecompany.billing.model;


import com.phonecompany.billing.impl.promo.PromoEvent;

import java.math.BigDecimal;
import java.util.List;

public class PhoneLog {
    private PromoEvent promoEvent;
    private final List<PhoneCall> phoneCalls;

    public PhoneLog(List<PhoneCall> phoneCalls) {
        this.phoneCalls = phoneCalls;
    }

    public void setPromoEvent(PromoEvent promoEvent) {
        this.promoEvent = promoEvent;
    }

    public BigDecimal getTotalCost() {
        return phoneCalls.stream().map(phoneCall -> {
            if (promoEvent != null) {
                return promoEvent.applyPromo(phoneCall);
            } else {
                return phoneCall.getCost();
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<PhoneCall> getPhoneCalls() {
        return phoneCalls;
    }

}