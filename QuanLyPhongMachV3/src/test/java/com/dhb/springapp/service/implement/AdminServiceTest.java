package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.service.IAdminService;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.IBenhNhanService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AdminServiceTest {
    @Autowired
    IAdminService adminService;
    @Autowired
    IBacSiService bacSiService;
    @Autowired
    IBenhNhanService benhNhanService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
    }

    @Test
    void getById() {
        benhNhanService.getById(BenhNhan.class,"1851050168").getDsPhieuKhamBenh().forEach(i-> System.out.println(i.getId()));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllOrderBy() {
    }

    @Test
    void getTop() {
    }

    @Test
    void getTopOrderBy() {
    }

    @Test
    void castAdminToModelView() {
    }

    @Test
    void getAdminTheoTen() {
    }

    @Test
    void getAdminTheoTenVaID() {
    }

    @Test
    void castPersistenceToTransient() {
    }

    @Test
    void testCastPersistenceToTransient() {
    }
}