package com.dhb.springapp.repository;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.repository.implement.BacSiRepository;
import com.dhb.springapp.repository.implement.Order;
import com.dhb.springapp.repository.implement.TaiKhoanRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.UUID;

public class TaiKhoanTester {
    private TaiKhoan taiKhoan = new TaiKhoan();

    @Test
    public void testInsertTaiKhoan() {
        taiKhoan.setId(UUID.randomUUID().toString());
        taiKhoan.setUsername("toilacongan113");
        taiKhoan.setPassword("123456");
        taiKhoan.setChucVu("admin");
        Assertions.assertEquals(TaiKhoan.class, new TaiKhoanRepository().insert(taiKhoan).getClass());
    }

    @Test
    public void testUpdateTaiKhoan() {
        TaiKhoan taiKhoan = new TaiKhoanRepository().getById(TaiKhoan.class, "68ac59ab-cc4a-4455-93ac-52db43391ac3");
        taiKhoan.setUsername("toilacongan113");

        Assertions.assertEquals(TaiKhoan.class, new TaiKhoanRepository().update(taiKhoan).getClass());
    }

    @Test
    public void testDeleteTaiKhoan() {
        TaiKhoan taiKhoan = new TaiKhoanRepository().getById(TaiKhoan.class, "68ac59ab-cc4a-4455-93ac-52db43391ac3");

        Assertions.assertEquals(1, new TaiKhoanRepository().delete(taiKhoan));
    }

    @Test
    public void testGetTaiKhoanByUnknownUsername() {
        Assertions.assertEquals(0, new TaiKhoanRepository().getTaiKhoanByUsername("hahaha").size());
    }

    @ParameterizedTest
    @CsvSource({"tester1", "tester2", "tester3"})
    public void testGetTaiKhoanByUsername(String username) {
        Assertions.assertEquals(1, new TaiKhoanRepository().getTaiKhoanByUsername(username).size());
    }

    @Test
    public void testGetTaiKhoanTheoChucVu() {
        Assertions.assertEquals(0, new TaiKhoanRepository().getTaiKhoanTheoChucVu("haha").size());
    }

    @Test
    public void testGetImage() {
        Assertions.assertEquals("/resources/image/admin.png", new BacSiRepository().getById(BacSi.class, "1851050169").getImage());
    }

    @Test
    public void testGetAll() {
        Assertions.assertEquals(6, new TaiKhoanRepository().getAll(TaiKhoan.class).size());
    }

    @Test
    public void testGelAllASC() {
        Assertions.assertEquals(6, new TaiKhoanRepository().getAllOrderBy(TaiKhoan.class, "username", Order.asc).size());
    }

    @Test
    public void testGetAllDESC() {
        Assertions.assertEquals(6, new TaiKhoanRepository().getAllOrderBy(TaiKhoan.class, "username", Order.desc).size());
    }
}
