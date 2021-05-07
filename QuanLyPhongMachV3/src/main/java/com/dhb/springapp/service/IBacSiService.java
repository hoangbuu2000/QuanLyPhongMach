package com.dhb.springapp.service;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddDoctor;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

public interface IBacSiService extends IGenericService<BacSi> {
    List<BacSi> getBacSiTheoTen(String ten);
    List<BacSi> getTopBacSiTheoTen(int limit, String name);
    Set<ToaThuoc> getToaThuocTheoBacSi(BacSi bacSi);
    Set<BenhNhan> getBenhNhanTheoBacSi(BacSi bacSi);
    AddDoctor castDoctorToModelView(BacSi bacSi, TaiKhoan taiKhoan, SimpleDateFormat format);
    int[] getTotalPrescriptionOfDoctor(String filter);
}
