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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public int[] getTotalPrescriptionOfDoctor(String filter) {
        List<BacSi> doctors = getAll(BacSi.class);
        int[] result = new int[doctors.size()];

        if (filter != null && !filter.isEmpty()) {
            String temp = filter.substring(filter.indexOf("-") + 1);
            String[] temp1 = temp.split("_");

            if (filter.contains("year")) {
                List<Integer> years = new ArrayList<>();
                for(String s : temp1) {
                    String[] temp2 = s.split(":");
                    years.add(Integer.parseInt(temp2[1]));
                }
                int i = 0;
                for(BacSi b : doctors) {
                    int size = b.getDsToaThuoc().stream()
                            .filter(t -> t.getNgayKeToa().getYear() + 1900
                                    >= years.get(0) && t.getNgayKeToa().getYear() + 1900
                                    < years.get(1))
                            .collect(Collectors.toList()).size();
                    result[i] = size;
                    i++;
                }
            }
            else if (filter.contains("month")) {
                List<Integer> months = new ArrayList<>();
                for(String s : temp1) {
                    String[] temp2 = s.split(":");
                    months.add(Integer.parseInt(temp2[1]));
                }
                int i = 0;
                for(BacSi b : doctors) {
                    int size = b.getDsToaThuoc().stream()
                            .filter(t -> t.getNgayKeToa().getMonth() + 1
                                    >= months.get(0) && t.getNgayKeToa().getMonth() + 1
                                    < months.get(1) && t.getNgayKeToa().getYear() == new Date().getYear())
                            .collect(Collectors.toList()).size();
                    result[i] = size;
                    i++;
                }
            }
        }

        else {
            int i = 0;
            for(BacSi b : doctors) {
                int size = b.getDsToaThuoc().size();
                result[i] = size;
                i++;
            }
        }
        return result;
    }
}
