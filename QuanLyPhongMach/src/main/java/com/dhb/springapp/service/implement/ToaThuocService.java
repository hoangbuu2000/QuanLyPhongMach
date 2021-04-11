package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.IToaThuocRepository;
import com.dhb.springapp.service.IToaThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ToaThuocService extends GenericService<ToaThuoc> implements IToaThuocService {
    private IToaThuocRepository toaThuocRepository;

    @Autowired
    public ToaThuocService(@Qualifier("toaThuocRepository")IGenericRepository<ToaThuoc> iGenericRepository) {
        super(iGenericRepository);
        this.toaThuocRepository = (IToaThuocRepository) iGenericRepository;
    }
}
