package com.dhb.springapp.service;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddEmployee;

import java.text.SimpleDateFormat;

public interface IAdminService extends IGenericService<Admin> {
    AddEmployee castAdminToModelView(Admin admin, TaiKhoan taiKhoan, SimpleDateFormat format);
}
