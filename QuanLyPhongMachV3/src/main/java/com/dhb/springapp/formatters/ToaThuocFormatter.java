package com.dhb.springapp.formatters;

import com.dhb.springapp.models.ToaThuoc;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ToaThuocFormatter implements Formatter<ToaThuoc> {
    @Override
    public ToaThuoc parse(String s, Locale locale) throws ParseException {
        ToaThuoc toaThuoc = new ToaThuoc();
        toaThuoc.setId(s);
        return toaThuoc;
    }

    @Override
    public String print(ToaThuoc toaThuoc, Locale locale) {
        return toaThuoc.toString();
    }
}
