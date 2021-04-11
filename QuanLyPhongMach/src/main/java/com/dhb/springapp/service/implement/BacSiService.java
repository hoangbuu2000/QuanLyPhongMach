package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.repository.IBacSiRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.implement.BacSiRepository;
import com.dhb.springapp.service.IBacSiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@Service
public class BacSiService extends GenericService<BacSi> implements IBacSiService {
    private IBacSiRepository bacSiRepository;

    @Autowired
    public BacSiService(@Qualifier("bacSiRepository")IGenericRepository<BacSi> iGenericRepository) {
        super(iGenericRepository);
        this.bacSiRepository = (IBacSiRepository) iGenericRepository;
    }

    @Override
    public List<BacSi> getBacSiTheoTen(String ten) {
        return bacSiRepository.getBacSiTheoTen(ten);
    }

    @Override
    public List<BacSi> getTopBacSiTheoTen(int limit, String name) {
        return bacSiRepository.getTopBacSiTheoTen(limit, name);
    }

    @Override
    public Set<ToaThuoc> getToaThuocTheoBacSi(BacSi bacSi) {
        return bacSiRepository.getToaThuocTheoBacSi(bacSi);
    }

    @Override
    public Set<BenhNhan> getBenhNhanTheoBacSi(BacSi bacSi) {
        return bacSiRepository.getBenhNhanTheoBacSi(bacSi);
    }

    @Override
    public AddDoctor castDoctorToModelView(BacSi bacSi, TaiKhoan taiKhoan, SimpleDateFormat format) {
        AddDoctor doctor = new AddDoctor();
        doctor.setTen(bacSi.getTen());
        doctor.setHo(bacSi.getHo());
        doctor.setEmail(bacSi.getEmail());
        doctor.setUsername(taiKhoan.getUsername());
        doctor.setPassword(taiKhoan.getPassword());
        doctor.setConfirmPassword(taiKhoan.getPassword());
        doctor.setDienThoai(bacSi.getDienThoai());
        doctor.setGioiTinh(bacSi.getGioiTinh());
        doctor.setNgaySinh(format.format(bacSi.getNgaySinh()));
        doctor.setQueQuan(bacSi.getQueQuan());
        doctor.setUrlImg(bacSi.getImage());
        doctor.setActive(taiKhoan.isActive());

        return doctor;
    }
}
