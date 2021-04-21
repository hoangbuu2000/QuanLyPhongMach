package com.dhb.springapp.repository;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;

import java.util.Date;
import java.util.List;

public interface ICaKhamBenhRepository extends IGenericRepository<CaKhamBenh> {
    List<BacSi> layBacSiTheoCaKham(int id, Date ngayKhamBenh);
    List<CaKhamBenh> layCaKhamTheoNgayKham(Date ngayKhamBenh);
    List<CaKhamBenh> layCaKhamConTrong(Date ngayKhamBenh);
}
