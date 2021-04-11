package com.dhb.springapp.service;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.AddPatient;

import java.text.SimpleDateFormat;
import java.util.List;

public interface IBenhNhanService extends IGenericService<BenhNhan> {
    List<BenhNhan> getBenhNhanTheoTen(String ten);
    List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name);
    List<BenhNhan> getTopNewBenhNhan(int limit);
    int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan);
    void themBenhNhanVaPhieuKhamBenh(AddPatient addPatient, SimpleDateFormat format) throws Exception;
}
