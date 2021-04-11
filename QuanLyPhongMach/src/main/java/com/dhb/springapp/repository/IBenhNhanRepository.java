package com.dhb.springapp.repository;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.PhieuKhamBenh;

import java.util.List;

public interface IBenhNhanRepository extends IGenericRepository<BenhNhan> {
    List<BenhNhan> getBenhNhanTheoTen(String ten);
    List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name);
    List<BenhNhan> getTopNewBenhNhan(int limit);
    int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan);
    boolean themBenhNhanVaPhieuKhamBenh(BenhNhan benhNhan, PhieuKhamBenh phieuKhamBenh);
}
