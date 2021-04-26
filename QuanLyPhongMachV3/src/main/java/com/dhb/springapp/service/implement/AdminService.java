package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddEmployee;
import com.dhb.springapp.repository.IAdminRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService extends GenericService<Admin> implements IAdminService {
    private IAdminRepository adminRepository;

    @Autowired
    public AdminService(@Qualifier("adminRepository")IGenericRepository<Admin> genericRepository) {
        super(genericRepository);
        this.adminRepository = (IAdminRepository) genericRepository;
    }

    @Override
    public AddEmployee castAdminToModelView(Admin admin, TaiKhoan taiKhoan, SimpleDateFormat format) {
        AddEmployee employee = new AddEmployee();
        employee.setTen(admin.getTen());
        employee.setHo(admin.getHo());
        employee.setEmail(admin.getEmail());
        employee.setUsername(taiKhoan.getUsername());
        employee.setPassword(taiKhoan.getPassword());
        employee.setConfirmPassword(taiKhoan.getPassword());
        employee.setDienThoai(admin.getDienThoai());
        employee.setGioiTinh(admin.getGioiTinh());
        employee.setNgaySinh(format.format(admin.getNgaySinh()));
        employee.setQueQuan(admin.getQueQuan());
        employee.setUrlImg(admin.getImage());
        employee.setActive(taiKhoan.isActive());
        employee.setNgayVaoLam(format.format(admin.getNgayVaoLam()));

        return employee;
    }

    @Override
    public List<Admin> getAdminTheoTen(String ten) {
        return adminRepository.getAdminTheoTen(ten);
    }

    @Override
    public List<Admin> getAdminTheoTenVaID(String id, String ten) {
        return adminRepository.getAdminTheoTenVaID(id, ten);
    }

    @Override
    public List<Admin> castPersistenceToTransient(List<Admin> adminList) {
        List<Admin> result = new ArrayList<>();
        if (adminList != null) {
            adminList.forEach(ad -> {
                Admin admin = new Admin();
                admin.setId(ad.getId());
                admin.setTen(ad.getTen());
                admin.setHo(ad.getHo());
                admin.setEmail(ad.getEmail());
                admin.setDienThoai(ad.getDienThoai());
                admin.setQueQuan(ad.getQueQuan());
                admin.setImage(ad.getImage());
                admin.setNgayVaoLam(ad.getNgayVaoLam());
                admin.setGioiTinh(ad.getGioiTinh());

                result.add(admin);
            });
        }
        return result;
    }

    @Override
    public Admin castPersistenceToTransient(Admin ad) {
        Admin result = new Admin();
        if (ad != null) {
            result.setId(ad.getId());
            result.setTen(ad.getTen());
            result.setHo(ad.getHo());
            result.setEmail(ad.getEmail());
            result.setDienThoai(ad.getDienThoai());
            result.setQueQuan(ad.getQueQuan());
            result.setImage(ad.getImage());
            result.setNgayVaoLam(ad.getNgayVaoLam());
            result.setGioiTinh(ad.getGioiTinh());
        }
        return result;
    }
}
