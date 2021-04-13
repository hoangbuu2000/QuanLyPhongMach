package com.dhb.springapp.repository;

import com.dhb.springapp.models.NhanVien;

import java.util.List;

public interface INhanVienRepository extends IGenericRepository<NhanVien> {
    List<NhanVien> getNhanVienTheoTen(String ten);
    List<NhanVien> getNhanVienTheoTenVaID(String id, String ten);
}
