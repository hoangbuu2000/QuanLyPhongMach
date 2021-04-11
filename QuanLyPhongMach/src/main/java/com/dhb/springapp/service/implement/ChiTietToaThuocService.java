package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.repository.IChiTietToaThuocRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IChiTietToaThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChiTietToaThuocService extends GenericService<ChiTietToaThuoc> implements IChiTietToaThuocService {
    private IChiTietToaThuocRepository chiTietToaThuocRepository;

    @Autowired
    public ChiTietToaThuocService(@Qualifier("chiTietToaThuocRepository")IGenericRepository<ChiTietToaThuoc> genericRepository) {
        super(genericRepository);
        this.chiTietToaThuocRepository = (IChiTietToaThuocRepository) genericRepository;
    }
}
