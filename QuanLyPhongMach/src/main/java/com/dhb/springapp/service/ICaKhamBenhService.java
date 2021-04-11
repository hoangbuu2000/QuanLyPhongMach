package com.dhb.springapp.service;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;

import java.util.Date;
import java.util.List;

public interface ICaKhamBenhService extends IGenericService<CaKhamBenh> {
    List<BacSi> layBacSiTheoCaKham(int id, Date ngayKhamBenh);
    List<CaKhamBenh> layCaKhamTheoNgayKham(Date ngayKhamBenh);
}
