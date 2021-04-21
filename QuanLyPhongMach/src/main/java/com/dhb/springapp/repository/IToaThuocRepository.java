package com.dhb.springapp.repository;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.ToaThuoc;

import java.util.List;

public interface IToaThuocRepository extends IGenericRepository<ToaThuoc> {
    boolean addOrUpdate(String id, ToaThuoc toaThuoc, List<ChiTietToaThuoc> chiTietToaThuocList);
}
