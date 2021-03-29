package com.dhb.springapp.repository;

import com.dhb.springapp.models.BenhNhan;

import java.util.List;

public interface IBenhNhanRepository extends IGenericRepository<BenhNhan> {
    List<BenhNhan> getBenhNhanTheoTen(String ten);
    List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name);
    List<BenhNhan> getNewBenhNhan();
    int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan);
}
