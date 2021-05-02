package com.dhb.springapp.service;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;

import java.util.Date;

public interface IChiTietCaKhamBenhService extends IGenericService<ChiTietCaKhamBenh> {
    boolean checkExistedSchedule(BacSi bacSi, CaKhamBenh caKhamBenh, Date ngayKhamBenh);
    ChiTietCaKhamBenh getExistedSchedule(BacSi bacSi, CaKhamBenh caKhamBenh, Date ngayKhamBenh);
    void updateSchedule(ChiTietCaKhamBenh oldSchedule, ChiTietCaKhamBenh newSchedule) throws Exception;
}
