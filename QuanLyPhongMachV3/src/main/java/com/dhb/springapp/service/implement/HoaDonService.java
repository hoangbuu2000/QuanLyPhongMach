package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.HoaDon;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.IHoaDonRepository;
import com.dhb.springapp.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService extends GenericService<HoaDon> implements IHoaDonService {
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    public HoaDonService(@Qualifier("hoaDonRepository")IGenericRepository<HoaDon> iGenericRepository) {
        super(iGenericRepository);
        this.hoaDonRepository = (IHoaDonRepository) iGenericRepository;
    }
}
