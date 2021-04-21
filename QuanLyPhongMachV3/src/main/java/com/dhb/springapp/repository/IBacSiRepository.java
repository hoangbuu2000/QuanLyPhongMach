package com.dhb.springapp.repository;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.ToaThuoc;

import java.util.List;
import java.util.Set;

public interface IBacSiRepository extends IGenericRepository<BacSi> {
    List<BacSi> getBacSiTheoTen(String ten);
    List<BacSi> getTopBacSiTheoTen(int limit, String name);
    Set<ToaThuoc> getToaThuocTheoBacSi(BacSi bacSi);
    Set<BenhNhan> getBenhNhanTheoBacSi(BacSi bacSi);
}
