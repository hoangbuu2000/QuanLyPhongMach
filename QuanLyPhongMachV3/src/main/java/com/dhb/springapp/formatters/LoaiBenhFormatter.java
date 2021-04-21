package com.dhb.springapp.formatters;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.service.ILoaiBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class LoaiBenhFormatter implements Formatter<LoaiBenh> {
    @Autowired
    ILoaiBenhService iLoaiBenhService;

    @Override
    public LoaiBenh parse(String text, Locale locale) throws ParseException {
        LoaiBenh loaiBenh = new LoaiBenh();
        loaiBenh.setId(Integer.parseInt(text));
        return loaiBenh;
    }

    @Override
    public String print(LoaiBenh object, Locale locale) {
        return object.getTenBenh();
    }
}
