package com.dhb.springapp.service;

import com.dhb.springapp.models.NhanVien;

import java.util.List;

public interface INhanVienService extends IGenericService<NhanVien> {
    List<NhanVien> getNhanVienTheoTen(String name);
    List<NhanVien> getNhanVienTheoTenVaID(String id, String ten);
    List<NhanVien> castPersistenceToTransient(List<NhanVien> nhanVienList);
    NhanVien castPersistenceToTransient(NhanVien nhanVien);
}
