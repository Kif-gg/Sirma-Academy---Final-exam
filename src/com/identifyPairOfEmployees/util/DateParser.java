package com.identifyPairOfEmployees.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateParser {
    private final String dateFormat;

    public DateParser(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public boolean validateFormat(String input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH);
        try {
            LocalDate parsedDate = LocalDate.parse(input, dtf);
            String formattedInput = dtf.format(parsedDate);
            return input.equals(formattedInput);
        } catch (Exception e) {
            return false;
        }
    }
}
