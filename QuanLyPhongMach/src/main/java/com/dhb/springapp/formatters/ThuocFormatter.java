package com.dhb.springapp.formatters;

import com.dhb.springapp.models.Thuoc;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

public class ThuocFormatter implements Formatter<Map<Thuoc, Integer>> {
    @Override
    public Map<Thuoc, Integer> parse(String s, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Map<Thuoc, Integer> thuocIntegerMap, Locale locale) {
        return null;
    }
}
