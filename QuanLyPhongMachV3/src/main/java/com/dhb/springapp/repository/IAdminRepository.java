package com.dhb.springapp.repository;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.NhanVien;

import java.util.List;

public interface IAdminRepository extends IGenericRepository<Admin> {
    List<Admin> getAdminTheoTen(String ten);
    List<Admin> getAdminTheoTenVaID(String id, String ten);
}
