package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.repository.ICaKhamBenhRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.ICaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CaKhamBenhService extends GenericService<CaKhamBenh> implements ICaKhamBenhService {
    private ICaKhamBenhRepository caKhamBenhRepository;

    @Autowired
    public CaKhamBenhService(@Qualifier("caKhamBenhRepository") IGenericRepository<CaKhamBenh> genericRepository) {
        super(genericRepository);
        this.caKhamBenhRepository = (ICaKhamBenhRepository) genericRepository;
    }

    @Override
    public List<BacSi> layBacSiTheoCaKham(int id, Date ngayKhamBenh) {
        return caKhamBenhRepository.layBacSiTheoCaKham(id, ngayKhamBenh);
    }

    @Override
    public List<CaKhamBenh> layCaKhamTheoNgayKham(Date ngayKhamBenh) {
        return caKhamBenhRepository.layCaKhamTheoNgayKham(ngayKhamBenh);
    }

    @Override
    public List<CaKhamBenh> layCaKhamConTrong(Date ngayKhamBenh) {
        return caKhamBenhRepository.layCaKhamConTrong(ngayKhamBenh);
    }
}
