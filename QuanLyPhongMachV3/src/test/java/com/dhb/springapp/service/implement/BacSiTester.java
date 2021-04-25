package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.Role;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.repository.IBacSiRepository;
import com.dhb.springapp.repository.ITaiKhoanRepository;
import com.dhb.springapp.repository.implement.BacSiRepository;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.ITaiKhoanService;
import com.dhb.springapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BacSiTester {
    @Mock
    IBacSiRepository iBacSiRepository;

    IBacSiService iBacSiService;

    @Mock
    ITaiKhoanRepository iTaiKhoanRepository;

    ITaiKhoanService iTaiKhoanService;

    List<BacSi> bacSiALL;
    BacSi bacSiAdd;
    TaiKhoan taiKhoanAdd;
    AddDoctor addDoctor;
    @Before
    public void setUp() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        iBacSiService = new BacSiService(iBacSiRepository);
        iTaiKhoanService = new TaiKhoanService(iTaiKhoanRepository);
        bacSiALL = session.createQuery("From BacSi")
                .getResultList();

//        taiKhoanAdd = new TaiKhoan();
//        taiKhoanAdd.setId(UUID.randomUUID().toString());
//        taiKhoanAdd.setUsername("bababa");
//        taiKhoanAdd.setPassword("123");
//        taiKhoanAdd.setActive(true);
//        taiKhoanAdd.setRole(session.get(Role.class, 1));
//        bacSiAdd = new BacSi();
//        bacSiAdd.setTaiKhoan(taiKhoanAdd);
//        bacSiAdd.setTen("Ba");
//        bacSiAdd.setHo("Ba");
//        bacSiAdd.setGioiTinh("Nam");
//        bacSiAdd.setNgaySinh();

        addDoctor = new AddDoctor();
        addDoctor.setUsername("bababa");
        addDoctor.setPassword("123");
        addDoctor.setConfirmPassword("123");
        addDoctor.setTen("Ba");
        addDoctor.setHo("Ba");
        addDoctor.setGioiTinh("Nam");
        addDoctor.setNgaySinh("13/03/2003");
        addDoctor.setActive(true);
        addDoctor.setEmail("bababa@gmail.com");
        addDoctor.setQueQuan("Tien Giang");
        addDoctor.setDienThoai("0768107704");


    }

    @Test
    public void testGetAll() {
        when(iBacSiService.getAll(BacSi.class)).thenReturn(bacSiALL);
        Assertions.assertEquals(iBacSiService.getAll(BacSi.class).size(), 3);
    }

    @Test
    public void testAddDoctor() throws Exception {
        doNothing().when(iTaiKhoanService).themTaiKhoanVaBacSi(isA(String.class), isA(AddDoctor.class));
        iTaiKhoanService.themTaiKhoanVaBacSi("/resources/images/bacsi/bababa.png", addDoctor);
        verify(iTaiKhoanService, times(1)).themTaiKhoanVaBacSi("/resources/images/bacsi/bababa.png", addDoctor);
    }

}
