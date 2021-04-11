package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.INhanVienRepository;
import com.dhb.springapp.service.INhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService extends GenericService<NhanVien> implements INhanVienService {
    private INhanVienRepository nhanVienRepository;

    @Autowired
    public NhanVienService(@Qualifier("nhanVienRepository")IGenericRepository<NhanVien> genericRepository) {
        super(genericRepository);
        this.nhanVienRepository = (INhanVienRepository) genericRepository;
    }
}
