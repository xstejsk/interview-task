package com.phonecompany.util;

import com.opencsv.bean.AbstractBeanField;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeConverter extends AbstractBeanField<DateTime, String> {

    @Override
    protected DateTime convert(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

        return formatter.parseDateTime(value);
    }
}
