package com.dhb.springapp.formatters;

import com.dhb.springapp.models.NhanVien;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class NhanVienFormatter implements Formatter<NhanVien> {
    @Override
    public NhanVien parse(String s, Locale locale) throws ParseException {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(s);
        return nhanVien;
    }

    @Override
    public String print(NhanVien nhanVien, Locale locale) {
        return nhanVien.toString();
    }
}
