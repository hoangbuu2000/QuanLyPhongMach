package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.repository.IBenhNhanRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IBenhNhanService;
import com.dhb.springapp.service.ICaKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BenhNhanService extends GenericService<BenhNhan> implements IBenhNhanService {
    private IBenhNhanRepository benhNhanRepository;

    @Autowired
    private ICaKhamBenhService iCaKhamBenhService;

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

        if (addPatient.getBacSi() == null) {
            List<BacSi> bacSiList = iCaKhamBenhService.layBacSiTheoCaKham(addPatient.getCaKham().getId(),
                    format.parse(addPatient.getNgayKham()));
            int idx = (int) (Math.random() * bacSiList.size());
            addPatient.setBacSi(bacSiList.get(idx));
        }

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

    @Override
    public void themBenhNhanTaiKham(AddPatient addPatient, SimpleDateFormat format) throws Exception {
        BenhNhan patient = getById(BenhNhan.class, addPatient.getId());
        if (patient != null) {
            if (addPatient.getBacSi() == null) {
                List<BacSi> bacSiList = iCaKhamBenhService.layBacSiTheoCaKham(addPatient.getCaKham().getId(),
                        format.parse(addPatient.getNgayKham()));
                int idx = (int) (Math.random() * bacSiList.size());
                addPatient.setBacSi(bacSiList.get(idx));
                System.out.println("idx: " + idx);
            }

            PhieuKhamBenh phieuKhamBenh = new PhieuKhamBenh();
            phieuKhamBenh.setBenhNhan(patient);
            phieuKhamBenh.setNgayKham(format.parse(addPatient.getNgayKham()));
            phieuKhamBenh.setDiaChi("371 Nguyen Kiem");
            phieuKhamBenh.setThanhToan(addPatient.isThanhToan());
            phieuKhamBenh.setDsLoaiBenh(addPatient.getLoaiBenhList());
            phieuKhamBenh.setCaKhamBenh(addPatient.getCaKham());
            phieuKhamBenh.setBacSi(addPatient.getBacSi());

            if (!benhNhanRepository.themBenhNhanVaPhieuKhamBenh(patient, phieuKhamBenh))
                throw new Exception("Giao tác thêm thất bại");
        }
        else {
            // Them thong tin benh nhan
            BenhNhan benhNhan = new BenhNhan();
            benhNhan.setId(addPatient.getId());
            benhNhan.setTen(addPatient.getTen());
            benhNhan.setHo(addPatient.getHo());
            benhNhan.setGioiTinh(addPatient.getGioiTinh());
            benhNhan.setEmail(addPatient.getEmail());
            benhNhan.setDienThoai(addPatient.getDienThoai());
            benhNhan.setNgaySinh(format.parse(addPatient.getNgaySinh()));
            benhNhan.setTuoi(addPatient.getTuoi());

            if (addPatient.getBacSi() == null) {
                List<BacSi> bacSiList = iCaKhamBenhService.layBacSiTheoCaKham(addPatient.getCaKham().getId(),
                        format.parse(addPatient.getNgayKham()));
                int idx = (int) (Math.random() * bacSiList.size());
                addPatient.setBacSi(bacSiList.get(idx));
            }

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

    @Override
    public List<Object[]> getBenhAnTheoBenhNhan(String id) {
        return benhNhanRepository.getBenhAnTheoBenhNhan(id);
    }

    @Override
    public List<BenhNhan> getBenhNhanCoLichKhamTheoThoiGianChoTruoc(Date date) {
        int ca = 0;
        if (date.getHours() >= 7 && date.getHours() <= 9) {
            if (date.getHours() == 9 && date.getMinutes() == 0 || date.getHours() != 9) {
                ca = 1;
            }
        }
        else if (date.getHours() >= 9 && date.getHours() <= 11) {
            if (date.getHours() == 9 && date.getMinutes() > 0 || date.getHours() == 11 && date.getMinutes() == 0
                    || date.getHours() == 10) {
                ca = 2;
            }
        }
        else if (date.getHours() >= 13 && date.getHours() <= 15) {
            if (date.getHours() == 15 && date.getMinutes() == 0
                    || date.getHours() != 15) {
                ca = 3;
            }
        }
        else if (date.getHours() >= 15 && date.getHours() <= 17) {
            if (date.getHours() == 15 && date.getMinutes() > 0 || date.getHours() == 17 && date.getMinutes() == 0
                    || date.getHours() == 16) {
                ca = 4;
            }
        }
        else if (date.getHours() >= 17 && date.getHours() <= 19) {
            if (date.getHours() == 17 && date.getMinutes() > 0 || date.getHours() == 19 && date.getMinutes() == 0
                    || date.getHours() == 18) {
                ca = 5;
            }
        }
        return benhNhanRepository.getBenhNhanCoLichKhamTheoThoiGianChoTruoc(date, ca);
    }
}
