package com.dhb.springapp.service;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ITaiKhoanService extends IGenericService<TaiKhoan> {
    TaiKhoan getTaiKhoanByUsername(String username);
    List<TaiKhoan> getTaiKhoanTheoChucVu(String kw);
    void themTaiKhoanVaBacSi(AddDoctor addDoctor, HttpServletRequest request) throws Exception;
    boolean checkPassword(AddDoctor addDoctor);
    boolean checkExistedUsername(AddDoctor addDoctor);
}
