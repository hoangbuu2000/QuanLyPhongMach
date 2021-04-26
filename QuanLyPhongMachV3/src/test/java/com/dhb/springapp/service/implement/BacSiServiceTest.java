package com.dhb.springapp.service.implement;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.service.IBacSiService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BacSiServiceTest {

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
    }

    @Test
    void update() {

    }

    @Test
    void getByIdValid(){
        Assertions.assertEquals(BacSi.class,iBacSiService.getById(BacSi.class,"1851050169").getClass());
    }

    @Test
    void  getByIdInValid(){
        Assertions.assertEquals(null, iBacSiService.getById(BacSi.class,"1") );

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
    void getTopValid(){
        Assertions.assertEquals(3, iBacSiService.getTop(BacSi.class, 3).size());

    }

    @Test
    void getTopInValid(){

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
    void getBacSiTheoTenValid (){
        Assertions.assertTrue(iBacSiService.getBacSiTheoTen("Ca Na").size() > 0);
    }

//    @Test
//    void getBacSiTheoTenInValid (){
//        Assertions.assertTrue();
//    }

}