package com.dhb.springapp.repository;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;

import java.util.Date;

public interface IChiTietCaKhamBenhRepository extends IGenericRepository<ChiTietCaKhamBenh> {
    void updateSchedule(ChiTietCaKhamBenh oldScchedule, ChiTietCaKhamBenh newSchedule) throws Exception;
}
