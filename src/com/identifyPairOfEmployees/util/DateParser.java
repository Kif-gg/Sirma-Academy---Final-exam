package com.identifyPairOfEmployees.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private final String dateFormat;

    public DateParser(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public boolean validateFormat(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(input);
            String formattedInput = sdf.format(parsedDate);
            return input.equals(formattedInput);
        } catch (Exception e) {
            return false;
        }
    }
}
