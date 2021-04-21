package com.dhb.springapp.repository;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;

import java.util.List;

public interface ITaiKhoanRepository extends IGenericRepository<TaiKhoan> {
    TaiKhoan getTaiKhoanByUsername(String username);
    List<TaiKhoan> getTaiKhoanTheoChucVu(String kw);
    boolean themTaiKhoanVaBacSi(TaiKhoan taiKhoan, BacSi bacSi);
    boolean suaTaiKhoanVaBacSi(TaiKhoan taiKhoan, BacSi bacSi);
    boolean themTaiKhoanVaNhanVien(TaiKhoan taiKhoan, NhanVien nhanVien);
    boolean suaTaiKhoanVaNhanVien(TaiKhoan taiKhoan, NhanVien nhanVien);
}
