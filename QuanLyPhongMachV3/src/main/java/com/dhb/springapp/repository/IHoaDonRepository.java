package com.dhb.springapp.repository;

import com.dhb.springapp.models.HoaDon;

import java.text.ParseException;
import java.util.List;

public interface IHoaDonRepository extends IGenericRepository<HoaDon> {
    boolean addOrUpdate(String id, HoaDon hoaDon);
    HoaDon getHoaDonTheoToaThuoc(String maToaThuoc);
    List<HoaDon> getHoaDonFromTo(String from, String to) throws ParseException;
}
