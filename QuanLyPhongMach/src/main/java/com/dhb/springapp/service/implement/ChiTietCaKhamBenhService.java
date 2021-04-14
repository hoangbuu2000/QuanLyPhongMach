package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.repository.IChiTietCaKhamBenhRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IChiTietCaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChiTietCaKhamBenhService extends GenericService<ChiTietCaKhamBenh> implements IChiTietCaKhamBenhService {
    private IChiTietCaKhamBenhRepository chiTietCaKhamBenhRepository;

    @Autowired
    public ChiTietCaKhamBenhService(@Qualifier("chiTietCaKhamBenhRepository")IGenericRepository<ChiTietCaKhamBenh> genericRepository) {
        super(genericRepository);
        this.chiTietCaKhamBenhRepository = (IChiTietCaKhamBenhRepository) genericRepository;
    }
}
