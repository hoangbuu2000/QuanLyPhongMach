package com.dhb.springapp.service;

import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.modelview.AddEmployee;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITaiKhoanService extends IGenericService<TaiKhoan>, UserDetailsService {
    TaiKhoan getTaiKhoanByUsername(String username);
    List<TaiKhoan> getTaiKhoanTheoChucVu(String kw);
    void themTaiKhoanVaBacSi(String relativePath, AddDoctor addDoctor) throws Exception;
    void suaTaiKhoanVaBacSi(String id, String relativePath, AddDoctor editedDoctor) throws Exception;
    void themTaiKhoanVaNhanVien(String relativePath, AddEmployee addEmployee) throws Exception;
    void suaTaiKhoanVaNhanVien(String id, String relativePath, AddEmployee editedEmployee) throws Exception;
    void themTaiKhoanVaAdmin(String relativePath, AddEmployee addEmployee) throws  Exception;
    void suaTaiKhoanVaAdmin(String id, String relativePath, AddEmployee addEmployee) throws Exception;

    boolean checkPassword(AddDoctor addDoctor);
    boolean checkPassword(AddEmployee addEmployee);
    boolean checkExistedUsername(AddDoctor addDoctor);
    boolean checkExistedUsername(AddEmployee addEmployee);
    boolean checkNoChangeUsername(String id, AddDoctor addDoctor);
    boolean checkNoChangeUsername(String id, AddEmployee addEmployee);
}
