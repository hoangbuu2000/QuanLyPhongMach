package com.dhb.springapp.service;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.PhieuKhamBenh;

import java.util.List;

public interface IBenhNhanService extends IGenericService<BenhNhan> {
    List<BenhNhan> getBenhNhanTheoTen(String ten);
    List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name);
    List<BenhNhan> getNewBenhNhan();
    int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan);
}
