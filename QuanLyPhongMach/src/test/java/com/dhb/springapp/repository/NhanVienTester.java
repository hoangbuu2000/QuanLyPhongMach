package com.dhb.springapp.repository;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.repository.implement.NhanVienRepository;
import com.dhb.springapp.repository.implement.Order;
import com.dhb.springapp.repository.implement.TaiKhoanRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NhanVienTester {
    private NhanVien nhanVien;

    @Test
    public void testGetById() {
        Assertions.assertEquals(NhanVien.class, new NhanVienRepository().getById(NhanVien.class, "1851050105").getClass());
    }

    @Test
    public void testGetByName() {
        Assertions.assertEquals(1, new NhanVienRepository().getNhanVienTheoTen("phuc").size());
    }

    @Test
    public void testGetAll() {
        Assertions.assertEquals(1, new NhanVienRepository().getAll(NhanVien.class).size());
    }

    @Test
    public void testGetAllASC() {
        Assertions.assertEquals(1, new NhanVienRepository().getAllOrderBy(NhanVien.class, "ten", Order.asc).size());
    }

    @Test
    public void testGetAllDESC() {
        Assertions.assertEquals(1, new NhanVienRepository().getAllOrderBy(NhanVien.class, "ten", Order.desc).size());
    }

    @Test
    public void testGetTop() {
        Assertions.assertEquals(1, new NhanVienRepository().getTop(NhanVien.class, 1).size());
    }

    @Test
    public void testGetTopASC() {
        Assertions.assertEquals(1, new NhanVienRepository()
                .getTopOrderBy(NhanVien.class, 1, "ten", Order.asc).size());
    }

    @Test
    public void testGetTopDESC() {
        Assertions.assertEquals(1, new NhanVienRepository()
                .getTopOrderBy(NhanVien.class, 1, "ten", Order.desc).size());
    }

    @Test
    public void testInsert() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        nhanVien = new NhanVien();
        nhanVien.setTaiKhoan(new TaiKhoanRepository().getById(TaiKhoan.class, "a831455e-c0a6-4f89-b7e0-46c58ac6812a"));
        nhanVien.setTen("vi du");
        nhanVien.setHo("test");
        nhanVien.setGioiTinh("Nam");
        nhanVien.setImage("/resources/images/nhanvien.png");
        nhanVien.setDienThoai("115");
        nhanVien.setNgaySinh(format.parse("01/01/2000"));

        Assertions.assertEquals(NhanVien.class, new NhanVienRepository().insert(nhanVien).getClass());
    }

    @Test
    public void testUpdate() {
        NhanVien nhanVien = new NhanVienRepository().getById(NhanVien.class, "a831455e-c0a6-4f89-b7e0-46c58ac6812a");
        nhanVien.setTen("test");

        Assertions.assertEquals(NhanVien.class, new NhanVienRepository().insert(nhanVien).getClass());
    }

    @Test
    public void testDelete() {
        NhanVien nhanVien = new NhanVienRepository().getById(NhanVien.class, "a831455e-c0a6-4f89-b7e0-46c58ac6812a");
        Assertions.assertEquals(1, new NhanVienRepository().delete(nhanVien));
    }
}
