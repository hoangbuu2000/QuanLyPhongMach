package com.dhb.springapp.service;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.modelview.AddPatient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface IBenhNhanService extends IGenericService<BenhNhan> {
    List<BenhNhan> getBenhNhanTheoTen(String ten);
    List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name);
    List<BenhNhan> getTopNewBenhNhan(int limit);
    int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan);
    void themBenhNhanVaPhieuKhamBenh(AddPatient addPatient, SimpleDateFormat format) throws Exception;
    List<Object[]> getBenhAnTheoBenhNhan(String id);
    List<BenhNhan> getBenhNhanCoLichKhamTheoThoiGianChoTruoc(Date date);
    void themBenhNhanTaiKham(AddPatient addPatient, SimpleDateFormat format) throws Exception;
}
