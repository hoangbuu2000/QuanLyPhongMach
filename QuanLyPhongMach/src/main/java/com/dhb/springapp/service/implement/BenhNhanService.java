package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.repository.IBenhNhanRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenhNhanService extends GenericService<BenhNhan> implements IBenhNhanService {
    private IBenhNhanRepository benhNhanRepository;

    @Autowired
    public BenhNhanService(@Qualifier("benhNhanRepository")IGenericRepository<BenhNhan> iGenericRepository) {
        super(iGenericRepository);
        this.benhNhanRepository = (IBenhNhanRepository) iGenericRepository;
    }

    @Override
    public List<BenhNhan> getBenhNhanTheoTen(String ten) {
        return benhNhanRepository.getBenhNhanTheoTen(ten);
    }

    @Override
    public List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name) {
        return benhNhanRepository.getTopBenhNhanTheoTen(limit, name);
    }

    @Override
    public int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan) {
        return benhNhanRepository.getSoLuongPhieuKhamBenhCuaBenhNhan(benhNhan);
    }

    @Override
    public List<BenhNhan> getNewBenhNhan() {
        return benhNhanRepository.getNewBenhNhan();
    }
}
