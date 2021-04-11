package com.dhb.springapp.formatters;

import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.service.ICaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CaKhamFormatter implements Formatter<CaKhamBenh> {
    @Autowired
    private ICaKhamBenhService iCaKhamBenhService;

    @Override
    public CaKhamBenh parse(String text, Locale locale) throws ParseException {
        CaKhamBenh caKhamBenh = new CaKhamBenh();
        caKhamBenh.setId(Integer.parseInt(text));
        return caKhamBenh;
    }

    @Override
    public String print(CaKhamBenh object, Locale locale) {
        if (object == null)
            return "";
        return object.getTenCa();
    }
}
