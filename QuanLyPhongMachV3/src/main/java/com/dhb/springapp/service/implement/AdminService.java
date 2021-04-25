package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddEmployee;
import com.dhb.springapp.repository.IAdminRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

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
}
