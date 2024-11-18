package com.phonecompany;

import com.phonecompany.billing.TelephoneBillCalculator;
import com.phonecompany.billing.impl.TelephoneBillCalculatorImpl;

// Delete this class and use it as a maven module
// Left here for easier testing
public class Main {
    public static void main(String[] args) {
        TelephoneBillCalculator telephoneBillCalculator = new TelephoneBillCalculatorImpl();
        String csvString = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00";
        System.out.println(
                telephoneBillCalculator.calculate(csvString)
        );
    }
}