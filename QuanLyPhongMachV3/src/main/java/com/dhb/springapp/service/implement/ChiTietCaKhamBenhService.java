package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.repository.IChiTietCaKhamBenhRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IChiTietCaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChiTietCaKhamBenhService extends GenericService<ChiTietCaKhamBenh> implements IChiTietCaKhamBenhService {
    private IChiTietCaKhamBenhRepository chiTietCaKhamBenhRepository;

    @Autowired
    public ChiTietCaKhamBenhService(@Qualifier("chiTietCaKhamBenhRepository")IGenericRepository<ChiTietCaKhamBenh> genericRepository) {
        super(genericRepository);
        this.chiTietCaKhamBenhRepository = (IChiTietCaKhamBenhRepository) genericRepository;
    }

    @Override
    public boolean checkExistedSchedule(BacSi bacSi, CaKhamBenh caKhamBenh, Date ngayKhamBenh) {
        return chiTietCaKhamBenhRepository.getAll(ChiTietCaKhamBenh.class).stream()
                .anyMatch(ct -> ct.getBacSi().getId().equals(bacSi.getId())
                        && ct.getCaKhamBenh().getId() == caKhamBenh.getId()
                        && ct.getNgayKhamBenh().compareTo(ngayKhamBenh) == 0);
    }

    @Override
    public ChiTietCaKhamBenh getExistedSchedule(BacSi bacSi, CaKhamBenh caKhamBenh, Date ngayKhamBenh) {
        return chiTietCaKhamBenhRepository.getAll(ChiTietCaKhamBenh.class).stream()
                .filter(ct -> ct.getBacSi().getId().equals(bacSi.getId())
                        && ct.getCaKhamBenh().getId() == caKhamBenh.getId()
                        && ct.getNgayKhamBenh().getYear() == ngayKhamBenh.getYear()
                        && ct.getNgayKhamBenh().getMonth() == ngayKhamBenh.getMonth()
                        && ct.getNgayKhamBenh().getDate() == ngayKhamBenh.getDate()
                ).findFirst().orElse(null);
    }

    @Override
    public void updateSchedule(ChiTietCaKhamBenh oldSchedule, ChiTietCaKhamBenh newSchedule) throws Exception {
        chiTietCaKhamBenhRepository.updateSchedule(oldSchedule, newSchedule);
    }
}
