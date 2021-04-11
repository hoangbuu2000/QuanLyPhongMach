package com.dhb.springapp.repository;

import com.dhb.springapp.models.Role;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.enums.Order;
import com.dhb.springapp.service.ITaiKhoanService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.UUID;

public class TaiKhoanTester {
    @Autowired
    private ITaiKhoanService iTaiKhoanService;
    private TaiKhoan taiKhoan = new TaiKhoan();

    @Test
    public void testInsertTaiKhoan() {
        taiKhoan.setId(UUID.randomUUID().toString());
        taiKhoan.setUsername("toilacongan113");
        taiKhoan.setPassword("123456");
        taiKhoan.setRole(new Role());
        Assertions.assertEquals(TaiKhoan.class, iTaiKhoanService.insert(taiKhoan).getClass());
    }

    @Test
    public void testUpdateTaiKhoan() {
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, "68ac59ab-cc4a-4455-93ac-52db43391ac3");
        taiKhoan.setUsername("toilacongan113");

        Assertions.assertEquals(TaiKhoan.class, iTaiKhoanService.update(taiKhoan).getClass());
    }

    @Test
    public void testDeleteTaiKhoan() {
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, "68ac59ab-cc4a-4455-93ac-52db43391ac3");

        Assertions.assertEquals(1, iTaiKhoanService.delete(taiKhoan));
    }

    @Test
    public void testGetTaiKhoanByUnknownUsername() {
        Assertions.assertEquals(TaiKhoan.class, iTaiKhoanService.getTaiKhoanByUsername("hahaha").getClass());
    }

    @ParameterizedTest
    @CsvSource({"tester1", "tester2", "tester3"})
    public void testGetTaiKhoanByUsername(String username) {
        Assertions.assertEquals(TaiKhoan.class, iTaiKhoanService.getTaiKhoanByUsername(username).getClass());
    }

    @Test
    public void testGetTaiKhoanTheoChucVu() {
        Assertions.assertEquals(0, iTaiKhoanService.getTaiKhoanTheoChucVu("haha").size());
    }

    @Test
    public void testGetAll() {
        Assertions.assertEquals(6, iTaiKhoanService.getAll(TaiKhoan.class).size());
    }

    @Test
    public void testGelAllASC() {
        Assertions.assertEquals(6, iTaiKhoanService.getAllOrderBy(TaiKhoan.class, "username", Order.asc).size());
    }

    @Test
    public void testGetAllDESC() {
        Assertions.assertEquals(6, iTaiKhoanService.getAllOrderBy(TaiKhoan.class, "username", Order.desc).size());
    }
}
