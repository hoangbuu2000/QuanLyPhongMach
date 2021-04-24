package com.dhb.springapp.service;

import com.dhb.springapp.models.HoaDon;
import com.dhb.springapp.modelview.AddInvoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public interface IHoaDonService extends IGenericService<HoaDon> {
    AddInvoice castToModelView(HoaDon hoaDon, SimpleDateFormat format);
    void addOrUpdate(String id, AddInvoice addInvoice, SimpleDateFormat format) throws Exception;
    HoaDon getHoaDonTheoToaThuoc(String maToaThuoc);
    List<HoaDon> getHoaDonFromTo(String from, String to) throws ParseException;
}
