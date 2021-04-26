package com.dhb.springapp.service;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddEmployee;

import java.text.SimpleDateFormat;
import java.util.List;

public interface IAdminService extends IGenericService<Admin> {
    AddEmployee castAdminToModelView(Admin admin, TaiKhoan taiKhoan, SimpleDateFormat format);
    List<Admin> getAdminTheoTen(String ten);
    List<Admin> getAdminTheoTenVaID(String id, String ten);
    List<Admin> castPersistenceToTransient(List<Admin> adminList);
    Admin castPersistenceToTransient(Admin admin);
}
