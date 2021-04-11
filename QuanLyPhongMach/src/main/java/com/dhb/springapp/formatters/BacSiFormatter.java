package com.dhb.springapp.formatters;

import com.dhb.springapp.models.BacSi;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class BacSiFormatter implements Formatter<BacSi> {
    @Override
    public BacSi parse(String text, Locale locale) throws ParseException {
        BacSi bacSi = new BacSi();
        bacSi.setId(text);
        return bacSi;
    }

    @Override
    public String print(BacSi object, Locale locale) {
        return object.getTen();
    }
}
