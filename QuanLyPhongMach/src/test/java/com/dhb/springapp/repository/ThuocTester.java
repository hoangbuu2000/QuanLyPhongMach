package com.dhb.springapp.repository;

import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.enums.Order;
import com.dhb.springapp.repository.implement.ThuocRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class ThuocTester {
    @Test
    public void testInsertThuoc() {
        Thuoc thuoc = new Thuoc();
        thuoc.setId(3);
        thuoc.setTenThuoc("Oxy già");
        thuoc.setMoTa("Thuốc sát trùng vết thương");
        thuoc.setDonGia(new BigDecimal(30000));
        thuoc.setDonVi("Chai");

        Assertions.assertEquals(Thuoc.class, new ThuocRepository().insert(thuoc).getClass());
    }

    @Test
    public void testUpdateThuoc() {
        Thuoc thuoc = new ThuocRepository().getById(Thuoc.class, 1);
        thuoc.setMoTa("Thuốc nhức đầu");
        thuoc.setDonVi("Vỉ");

        Assertions.assertEquals(Thuoc.class, new ThuocRepository().update(thuoc).getClass());
    }

    @Test
    public void testDeleteThuoc() {
        Thuoc thuoc = new ThuocRepository().getById(Thuoc.class, 4);

        Assertions.assertEquals(1, new ThuocRepository().delete(thuoc));
    }

    @Test
    public void testGetAll() {
        Assertions.assertEquals(3, new ThuocRepository().getAll(Thuoc.class).size());
    }

    @Test
    public void testGetAllASC() {
        Assertions.assertEquals(3, new ThuocRepository().getAllOrderBy(Thuoc.class, "id", Order.asc).size());
    }

    @Test
    public void testGetAllDESC() {
        Assertions.assertEquals(3, new ThuocRepository().getAllOrderBy(Thuoc.class, "id", Order.desc).size());
    }

    @Test
    public void testGetTop() {
        Assertions.assertEquals(1, new ThuocRepository().getTop(Thuoc.class, 1).size());
    }

    @Test
    public void testGetTopASC() {
        Assertions.assertEquals(2, new ThuocRepository().getTopOrderBy(Thuoc.class, 2, "id", Order.asc).size());
    }

    @Test
    public void testGetTopDESC() {
        Assertions.assertEquals(2, new ThuocRepository().getTopOrderBy(Thuoc.class, 2, "id", Order.desc).size());
    }

    @Test
    public void testGetThuocTheoId() {
        Assertions.assertEquals(Thuoc.class, new ThuocRepository().getById(Thuoc.class, 3).getClass());
    }

    @Test
    public void testGetThuocTheoTen() {
        Assertions.assertEquals(1, new ThuocRepository().getThuocTheoTen("dol").size());
    }

    @Test
    public void testGetThuocTheoDonGia() {
        Assertions.assertEquals(1, new ThuocRepository()
                .getThuocTheoDonGia(new BigDecimal(30000), new BigDecimal(40000)).size());
    }
    
    @Test
    public void testGetThuocTheoMoTa() {
        Assertions.assertEquals(1, new ThuocRepository().getThuocTheoMoTa("nhuc dau").size());
    }

    @Test
    public void testGetThuocTheoDonVi() {
        Assertions.assertEquals(1, new ThuocRepository().getThuocTheoDonVi("vien").size());
    }
}
