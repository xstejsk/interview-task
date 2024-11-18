package com.phonecompany.billing;

import java.util.List;

public interface CsvParser<T> {

    List<T> parse(String csv);
}
