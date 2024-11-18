package com.phonecompany.billing.impl.promo;

import com.phonecompany.billing.model.PhoneCall;

import java.math.BigDecimal;

public interface PromoEvent {

    BigDecimal applyPromo(PhoneCall phoneCall);
}
