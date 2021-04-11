package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.repository.IBenhNhanRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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
    public List<BenhNhan> getTopNewBenhNhan(int limit) {
        return benhNhanRepository.getTopNewBenhNhan(limit);
    }

    @Override
    public void themBenhNhanVaPhieuKhamBenh(AddPatient addPatient, SimpleDateFormat format) throws Exception {
        format = new SimpleDateFormat("dd/MM/yyyy");
        // Them thong tin benh nhan
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setId(UUID.randomUUID().toString());
        benhNhan.setTen(addPatient.getTen());
        benhNhan.setHo(addPatient.getHo());
        benhNhan.setGioiTinh(addPatient.getGioiTinh());
        benhNhan.setEmail(addPatient.getEmail());
        benhNhan.setDienThoai(addPatient.getDienThoai());
        benhNhan.setNgaySinh(format.parse(addPatient.getNgaySinh()));
        benhNhan.setTuoi(addPatient.getTuoi());

        // Them thong tin phieu kham benh cua benh nhan va loai benh
        PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
        phieuKhamBenh.setBenhNhan(benhNhan);
        phieuKhamBenh.setNgayKham(format.parse(addPatient.getNgayKham()));
        phieuKhamBenh.setDiaChi("371 Nguyen Kiem");
        phieuKhamBenh.setThanhToan(addPatient.isThanhToan());
        phieuKhamBenh.setDsLoaiBenh(addPatient.getLoaiBenhList());
        phieuKhamBenh.setCaKhamBenh(addPatient.getCaKham());
        phieuKhamBenh.setBacSi(addPatient.getBacSi());

        if (!benhNhanRepository.themBenhNhanVaPhieuKhamBenh(benhNhan, phieuKhamBenh))
            throw new Exception("Giao tác thêm thất bại");
    }
}
