package com.dhb.springapp.repository;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.repository.implement.LoaiBenhRepository;
import com.dhb.springapp.enums.Order;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class LoaiBenhTester {
    private LoaiBenh loaiBenh;
    @Test
    public void testGetById() {
        Assertions.assertEquals(LoaiBenh.class, new LoaiBenhRepository().getById(LoaiBenh.class, 1).getClass());
    }

    @Test
    public void testGetByName() {
        Assertions.assertEquals(1, new LoaiBenhRepository().getLoaiBenhTheoTen("thoai vi dia dem").size());
    }

    @Test
    public void testGetByDescription() {
        Assertions.assertEquals(1, new LoaiBenhRepository().getLoaiBenhTheoMoTa("dau lung").size());
    }

    @Test
    public void testGetAll() {
        Assertions.assertEquals(1, new LoaiBenhRepository().getAll(LoaiBenh.class).size());
    }

    @Test
    public void testGetAllASC() {
        Assertions.assertEquals(1, new LoaiBenhRepository()
                .getAllOrderBy(LoaiBenh.class, "tenBenh", Order.asc).size());
    }

    @Test
    public void testGetAllDESC() {
        Assertions.assertEquals(1, new LoaiBenhRepository()
                .getAllOrderBy(LoaiBenh.class, "tenBenh", Order.desc).size());
    }

    @Test
    public void testGetTop() {
        Assertions.assertEquals(1, new LoaiBenhRepository().getTop(LoaiBenh.class, 1).size());
    }

    @Test
    public void testGetTopASC() {
        Assertions.assertEquals(1, new LoaiBenhRepository()
                .getTopOrderBy(LoaiBenh.class, 1, "tenBenh", Order.asc).size());
    }

    @Test
    public void testGetTopDESC() {
        Assertions.assertEquals(1, new LoaiBenhRepository()
                .getTopOrderBy(LoaiBenh.class, 1, "tenBenh", Order.desc).size());
    }

    @Test
    public void testInsert() {
        loaiBenh = new LoaiBenh();
        loaiBenh.setId(2);
        loaiBenh.setTenBenh("Cảm");
        loaiBenh.setMoTa("Ho, sổ mũi, đau họng");
        loaiBenh.setDonGia(new BigDecimal(50000));

        Assertions.assertEquals(LoaiBenh.class, new LoaiBenhRepository().insert(loaiBenh).getClass());
    }

    @Test
    public void testUpdate() {
        LoaiBenh loaiBenh = new LoaiBenhRepository().getById(LoaiBenh.class, 2);
        loaiBenh.setDonGia(new BigDecimal(70000));

        Assertions.assertEquals(LoaiBenh.class, new LoaiBenhRepository().update(loaiBenh).getClass());
    }

    @Test
    public void testDelete() {
        LoaiBenh loaiBenh = new LoaiBenhRepository().getById(LoaiBenh.class, 2);

        Assertions.assertEquals(1, new LoaiBenhRepository().delete(loaiBenh));
    }
}
