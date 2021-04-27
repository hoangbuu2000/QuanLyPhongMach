package com.dhb.springapp.service.implement;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.Role;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.IRoleService;
import com.dhb.springapp.service.ITaiKhoanService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class BacSiServiceTest {

    @Autowired
    IRoleService iRoleService;

    @Autowired
    ITaiKhoanService iTaiKhoanService;

    @Autowired
    IBacSiService iBacSiService;
    @BeforeEach
    void setUp(){

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {


        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(UUID.randomUUID().toString());
        taiKhoan.setActive(true);
        taiKhoan.setPassword("1233333");
        taiKhoan.setRole(iRoleService.getById(Role.class, 2));
        taiKhoan.setUsername("abc");
        iTaiKhoanService.insert(taiKhoan);


        BacSi bacSi =  new BacSi();
        bacSi.setDienThoai("123567891");
        bacSi.setEmail("ndt@gmail.com");
        bacSi.setGioiTinh("nam");
        bacSi.setHo("Nguyen");
        bacSi.setTen("Tèo");
        bacSi.setImage("/resources/images/bacsi/dhb.jpg");
        bacSi.setNgaySinh(new Date("01/01/2000"));
        bacSi.setQueQuan("Bình Thuận");
        bacSi.setTaiKhoan(taiKhoan);
        Assertions.assertTrue(iBacSiService.insert(bacSi) != null);
    }

    @Test
    void update() {
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, "1851050169");

        taiKhoan.setUsername("huykhung");
        Assertions.assertTrue(iTaiKhoanService.update(taiKhoan) != null);
        BacSi bacSi = iBacSiService.getById(BacSi.class, "1851050169");
        bacSi.setTen("Huy Khùng");
        Assertions.assertTrue(iBacSiService.update(bacSi) != null);

    }

    @Test
    void getById(){
        Assertions.assertEquals(BacSi.class,iBacSiService.getById(BacSi.class,"1851050169").getClass());
        Assertions.assertEquals(null, iBacSiService.getById(BacSi.class,"1") );
    }

    @Test
    void delete(){
        BacSi bacSi = iBacSiService.getById(BacSi.class, "4fb72ecb-a5ed-4e98-a9b9-872bc4f4d943");
        Assertions.assertTrue(iBacSiService.delete(bacSi) == 1);
    }


    @Test
    void getAll() {
        Assertions.assertEquals(21, iBacSiService.getAll(BacSi.class).size());

    }

    @Test
    void getAllOrderByIdDesc() {
        List<BacSi> listBacSi = iBacSiService.getAllOrderBy(BacSi.class,"id", Order.desc);

        Assertions.assertEquals("f257ab84-7d86-431b-a3b6-5ae8c73018c6",
               listBacSi.get(0).getId());


        Assertions.assertEquals("0b645856-3f3b-461e-b5ac-ee7c91f4ffd3",
                listBacSi.get(listBacSi.size() - 1).getId());
    }

    @Test
    void getAllOrderByIdAsc() {
        List<BacSi> listBacSi = iBacSiService.getAllOrderBy(BacSi.class,"id", Order.asc);

        Assertions.assertEquals("0b645856-3f3b-461e-b5ac-ee7c91f4ffd3",
                listBacSi.get(0).getId());


        Assertions.assertEquals("f257ab84-7d86-431b-a3b6-5ae8c73018c6",
                listBacSi.get(listBacSi.size() - 1).getId());
    }

    @Test
    void getTop(){
        Assertions.assertEquals(3, iBacSiService.getTop(BacSi.class, 3).size());
        Assertions.assertEquals(0, iBacSiService.getTop(BacSi.class, 0).size());
    }



    @Test
    void getTopOrderByIdDesc(){
        List<BacSi> listBacSi = iBacSiService.getTopOrderBy(BacSi.class, 3, "id", Order.desc);
        Assertions.assertEquals(3,listBacSi.size());

        Assertions.assertEquals("f257ab84-7d86-431b-a3b6-5ae8c73018c6",
                listBacSi.get(0).getId());

        Assertions.assertEquals("bef47dae-4279-4433-bb22-47c89a41988d",
                listBacSi.get(listBacSi.size() - 1).getId());
    }

    @Test
    void getTopOrderByIdAsc(){
        List<BacSi> listBacSi = iBacSiService.getTopOrderBy(BacSi.class, 3, "id", Order.asc);
        Assertions.assertEquals(3,listBacSi.size());

        Assertions.assertEquals("0b645856-3f3b-461e-b5ac-ee7c91f4ffd3",
                listBacSi.get(0).getId());

        Assertions.assertEquals("1dd56d41-06b4-4de9-9aa1-8a522c156866",
                listBacSi.get(listBacSi.size() - 1).getId());
    }

    @Test
    void getBacSiTheoTen (){
        Assertions.assertTrue(iBacSiService.getBacSiTheoTen("mas").size() == 0);
        Assertions.assertTrue(iBacSiService.getBacSiTheoTen("ca na").size() > 0);

    }

    @Test
    void getTopBaSiTheoTen(){
        List<BacSi> bacSiList = iBacSiService.getTopBacSiTheoTen(3,"Long");
        Assertions.assertTrue(bacSiList.size() <= 3);
        bacSiList.forEach(t -> {
            Assertions.assertEquals(t.getTen(), "Long");
        });
//        Assertions.assertEquals(bacSiList.get(0).getTen(), "Long");
//        Assertions.assertEquals(bacSiList.get(1).getTen(),"Long");

    }

}