package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.service.IBenhNhanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class BenhNhanTest {



    @Autowired
    IBenhNhanService iBenhNhanService;

    @Test
    void insert (){
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setDienThoai("0838238428");
        benhNhan.setEmail("thaykhung@gmail.com");
        benhNhan.setHo("Nguyễn");
        benhNhan.setTen("Văn Khùng");
        benhNhan.setGioiTinh("Nam");
        benhNhan.setNgaySinh(new Date("01/01/2000"));
        benhNhan.setTuoi(20);
        benhNhan.setId(UUID.randomUUID().toString());

        Assertions.assertEquals(iBenhNhanService.insert(benhNhan).getId(),benhNhan.getId());

    }

    @Test
    void update(){

        BenhNhan benhNhan = iBenhNhanService.getById(BenhNhan.class, "1851050168");
        benhNhan.setTen("Quỳnh Khùng");
        Assertions.assertTrue(iBenhNhanService.update(benhNhan) != null);
    }

    @Test
    void getById(){
        Assertions.assertEquals(BacSi.class,iBenhNhanService.getById(BenhNhan.class,"1851050169").getClass());
        Assertions.assertEquals(null, iBenhNhanService.getById(BenhNhan.class,"1") );
    }

}
