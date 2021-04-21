//package com.dhb.springapp.repository;
//
//import com.dhb.springapp.models.BacSi;
//import com.dhb.springapp.models.TaiKhoan;
//import com.dhb.springapp.repository.implement.BacSiRepository;
//import com.dhb.springapp.enums.Order;
//import com.dhb.springapp.service.IBacSiService;
//import com.dhb.springapp.service.ITaiKhoanService;
//import com.dhb.springapp.service.implement.BacSiService;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//
//import static org.mockito.Mockito.doReturn;
//
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.util.List;
//
//@SpringBootTest
//public class BacSiTester {
//
////    @TestConfiguration
////    static class BacSiTesterContextConfiguration {
////        @Bean
////        public IBacSiService iBacSiService() {
////            return new BacSiService();
////        }
////    }
//
//    private BacSi bacSi;
//    @Autowired
//    IBacSiService iBacSiService;
//    @MockBean
//    IBacSiRepository bacSiRepository;
//    @Autowired
//    ITaiKhoanService iTaiKhoanService;
//
//    @Test
//    public void testThemBacSi() throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//        bacSi = new BacSi();
//        bacSi.setHo("Nguyen");
//        bacSi.setTen("Binh");
//        bacSi.setGioiTinh("Nam");
//        bacSi.setDienThoai("0768107113");
//        bacSi.setImage("/resources/image/bacsi.png");
//        bacSi.setNgaySinh(format.parse("04/02/2000"));
//        bacSi.setTaiKhoan(iTaiKhoanService.getById(TaiKhoan.class, "7b8cdfad-7538-471e-84ea-8241939d9076"));
//
//        Assertions.assertEquals(BacSi.class, iBacSiService.insert(bacSi).getClass());
//    }
//
//    @Test
//    public void testUpdateBacSi() {
//        BacSi bacSi = new BacSiRepository().getById(BacSi.class,"68ac59ab-cc4a-4455-93ac-52db43391ac3");
//        bacSi.setDienThoai("114");
//        Assertions.assertEquals(BacSi.class, iBacSiService.update(bacSi).getClass());
//    }
//
//    @Test
//    public void testDeleteBacSi() {
//        BacSi bacSi = new BacSiRepository().getById(BacSi.class,"68ac59ab-cc4a-4455-93ac-52db43391ac3");
//        Assertions.assertEquals(1, iBacSiService.delete(bacSi));
//    }
//
//    @Test
//    public void testGetBacSiTheoId() {
//        Assertions.assertEquals(BacSi.class, iBacSiService.getById(BacSi.class, "68ac59ab-cc4a-4455-93ac-52db43391ac3").getClass());
//    }
//
//    @Test
//    public void testGetBacSiTheoTen() {
//        Assertions.assertEquals(1, iBacSiService.getBacSiTheoTen("Huy").size());
//    }
//
//    @Test
//    public void testGetTopBacSi() {
//        Assertions.assertEquals(1, iBacSiService.getTop(BacSi.class, 1).size());
//    }
//
//    @Test
//    public void testGetTopBacSiTheoTen() {
//        Assertions.assertEquals(1, iBacSiService.getTopBacSiTheoTen(1, "Binh").size());
//    }
//
//    @Test
//    public void testGetTopBacSiASC() {
//        Assertions.assertEquals(2, iBacSiService
//                .getTopOrderBy(BacSi.class,2, "ten", Order.asc).size());
//    }
//
//    @Test
//    public void testGetTopBacSiDESC() {
//        Assertions.assertEquals(2, iBacSiService
//                .getTopOrderBy(BacSi.class,2, "ten", Order.desc).size());
//    }
//
//    @Test
//    public void testGetAll() {
//        doReturn(2).when(bacSiRepository).getAll(BacSi.class);
//
//        List<BacSi> bacSiList = iBacSiService.getAll(BacSi.class);
//        Assertions.assertEquals(2, bacSiList.size());
//    }
//
//    @Test
//    public void testGelAllASC() {
//        Assertions.assertEquals(2, iBacSiService.getAllOrderBy(BacSi.class, "ten", Order.asc).size());
//    }
//
//    @Test
//    public void testGetAllDESC() {
//        Assertions.assertEquals(2, iBacSiService.getAllOrderBy(BacSi.class, "ten", Order.desc).size());
//    }
//
//    @Test
//    public void testGetDanhSachToaThuoc() {
//        BacSi bacSi = iBacSiService.getById(BacSi.class, "1851050169");
//
//        Assertions.assertEquals(1, iBacSiService.getToaThuocTheoBacSi(bacSi).size());
//    }
//
//    @Test
//    public void testGetDanhSachBenhNhan() {
//        BacSi bacSi = iBacSiService.getById(BacSi.class, "1851050169");
//
//        Assertions.assertEquals(1, iBacSiService.getBenhNhanTheoBacSi(bacSi).size());
//    }
//}
