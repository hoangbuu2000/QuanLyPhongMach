package com.dhb.springapp.formatters;

import com.dhb.springapp.models.Thuoc;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ThuocFormatter implements Formatter<Map<Thuoc, Integer>> {

    @Override
    public Map<Thuoc, Integer> parse(String s, Locale locale) throws ParseException {
        Map<Thuoc, Integer> result = new HashMap<>();
        String[] pairs = s.split(";");
        Arrays.stream(pairs).map(i -> i.split("-")).forEach(pair -> {
            Thuoc thuoc = new Thuoc();
            thuoc.setId(Integer.parseInt(pair[0]));
            result.put(thuoc, Integer.parseInt(pair[1]));
        });
        return result;
    }

    @Override
    public String print(Map<Thuoc, Integer> thuocIntegerMap, Locale locale) {
        return null;
    }
}
