package com.phonecompany.billing.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.phonecompany.billing.CsvParser;
import com.phonecompany.billing.model.PhoneCall;

import java.io.StringReader;
import java.util.List;

public class PhoneLogParser implements CsvParser<PhoneCall> {

    @Override
    public List<PhoneCall> parse(String csv) {

        return new CsvToBeanBuilder<PhoneCall>(
                new StringReader(csv))
                .withType(PhoneCall.class)
                .build()
                .parse();
    }
}
