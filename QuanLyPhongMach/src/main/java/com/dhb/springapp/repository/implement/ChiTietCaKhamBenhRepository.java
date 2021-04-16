package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.repository.IChiTietCaKhamBenhRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class ChiTietCaKhamBenhRepository extends GenericRepository<ChiTietCaKhamBenh> implements IChiTietCaKhamBenhRepository {
}
