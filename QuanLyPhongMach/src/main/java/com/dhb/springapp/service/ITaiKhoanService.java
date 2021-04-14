package com.dhb.springapp.service;

import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.modelview.AddEmployee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITaiKhoanService extends IGenericService<TaiKhoan> {
    TaiKhoan getTaiKhoanByUsername(String username);
    List<TaiKhoan> getTaiKhoanTheoChucVu(String kw);
    void themTaiKhoanVaBacSi(AddDoctor addDoctor, HttpServletRequest request) throws Exception;
    void suaTaiKhoanVaBacSi(String id, AddDoctor editedDoctor, HttpServletRequest request) throws Exception;
    void themTaiKhoanVaNhanVien(AddEmployee addEmployee, HttpServletRequest request) throws Exception;
    void suaTaiKhoanVaNhanVien(String id, AddEmployee editedEmployee, HttpServletRequest request) throws Exception;
    boolean checkPassword(AddDoctor addDoctor);
    boolean checkPassword(AddEmployee addEmployee);
    boolean checkExistedUsername(AddDoctor addDoctor);
    boolean checkExistedUsername(AddEmployee addEmployee);
    boolean checkNoChangeUsername(String id, AddDoctor addDoctor);
    boolean checkNoChangeUsername(String id, AddEmployee addEmployee);
}
