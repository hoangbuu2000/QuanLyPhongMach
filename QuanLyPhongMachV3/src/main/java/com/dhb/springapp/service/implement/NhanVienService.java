package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.modelview.AddEmployee;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.INhanVienRepository;
import com.dhb.springapp.service.INhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class NhanVienService extends GenericService<NhanVien> implements INhanVienService {
    private INhanVienRepository nhanVienRepository;

    @Autowired
    public NhanVienService(@Qualifier("nhanVienRepository")IGenericRepository<NhanVien> genericRepository) {
        super(genericRepository);
        this.nhanVienRepository = (INhanVienRepository) genericRepository;
    }

    @Override
    public List<NhanVien> getNhanVienTheoTen(String name) {
        return nhanVienRepository.getNhanVienTheoTen(name);
    }

    @Override
    public List<NhanVien> getNhanVienTheoTenVaID(String id, String ten) {
        return nhanVienRepository.getNhanVienTheoTenVaID(id, ten);
    }

    @Override
    public List<NhanVien> castPersistenceToTransient(List<NhanVien> nhanVienList) {
        List<NhanVien> result = new ArrayList<>();
        if (nhanVienList != null) {
            nhanVienList.forEach(nv -> {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(nv.getId());
                nhanVien.setTen(nv.getTen());
                nhanVien.setHo(nv.getHo());
                nhanVien.setEmail(nv.getEmail());
                nhanVien.setDienThoai(nv.getDienThoai());
                nhanVien.setQueQuan(nv.getQueQuan());
                nhanVien.setImage(nv.getImage());
                nhanVien.setNgayVaoLam(nv.getNgayVaoLam());
                nhanVien.setGioiTinh(nv.getGioiTinh());

                result.add(nhanVien);
            });
        }
        return result;
    }

    @Override
    public NhanVien castPersistenceToTransient(NhanVien nv) {
        NhanVien result = new NhanVien();
        if (nv != null) {
            result.setId(nv.getId());
            result.setTen(nv.getTen());
            result.setHo(nv.getHo());
            result.setEmail(nv.getEmail());
            result.setDienThoai(nv.getDienThoai());
            result.setQueQuan(nv.getQueQuan());
            result.setImage(nv.getImage());
            result.setNgayVaoLam(nv.getNgayVaoLam());
            result.setGioiTinh(nv.getGioiTinh());
        }
        return result;
    }

    @Override
    public AddEmployee castEmployeeToModelView(NhanVien nhanVien, TaiKhoan taiKhoan, SimpleDateFormat format) {
        AddEmployee employee = new AddEmployee();
        employee.setTen(nhanVien.getTen());
        employee.setHo(nhanVien.getHo());
        employee.setEmail(nhanVien.getEmail());
        employee.setUsername(taiKhoan.getUsername());
        employee.setPassword(taiKhoan.getPassword());
        employee.setConfirmPassword(taiKhoan.getPassword());
        employee.setDienThoai(nhanVien.getDienThoai());
        employee.setGioiTinh(nhanVien.getGioiTinh());
        employee.setNgaySinh(format.format(nhanVien.getNgaySinh()));
        employee.setQueQuan(nhanVien.getQueQuan());
        employee.setUrlImg(nhanVien.getImage());
        employee.setActive(taiKhoan.isActive());
        employee.setNgayVaoLam(format.format(nhanVien.getNgayVaoLam()));

        return employee;
    }
}
