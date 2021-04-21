package com.dhb.springapp.service;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddEmployee;

import java.text.SimpleDateFormat;
import java.util.List;

public interface INhanVienService extends IGenericService<NhanVien> {
    List<NhanVien> getNhanVienTheoTen(String name);
    List<NhanVien> getNhanVienTheoTenVaID(String id, String ten);
    List<NhanVien> castPersistenceToTransient(List<NhanVien> nhanVienList);
    NhanVien castPersistenceToTransient(NhanVien nhanVien);
    AddEmployee castEmployeeToModelView(NhanVien nhanVien, TaiKhoan taiKhoan, SimpleDateFormat format);
}
