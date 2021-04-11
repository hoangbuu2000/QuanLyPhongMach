package com.dhb.springapp.repository;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.repository.implement.BenhNhanRepository;
import com.dhb.springapp.enums.Order;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class BenhNhanTester {
    private BenhNhan benhNhan;

    @Test
    public void testInsertBenhNhan() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");

        benhNhan = new BenhNhan();
        benhNhan.setHo("Vu");
        benhNhan.setTen("Dat");
        benhNhan.setGioiTinh("Nam");
        benhNhan.setNgaySinh(format.parse("28/07/2000"));
        benhNhan.setDienThoai("0126540451");
        benhNhan.setId(UUID.randomUUID().toString());

        Assertions.assertEquals(BenhNhan.class, new BenhNhanRepository().insert(benhNhan).getClass());
    }

    @Test
    public void testUpdateBenhNhan() {
        BenhNhan benhNhan = new BenhNhanRepository().getById(BenhNhan.class,"64cf6fb0-2049-4e89-9421-7752b651160c");
        benhNhan.setDienThoai("113");
        Assertions.assertEquals(BenhNhan.class, new BenhNhanRepository().update(benhNhan).getClass());
    }

    @Test
    public void testDeleteBenhNhan() {
        BenhNhan benhNhan = new BenhNhanRepository().getById(BenhNhan.class,"64cf6fb0-2049-4e89-9421-7752b651160c");
        Assertions.assertEquals(1, new BenhNhanRepository().delete(benhNhan));
    }

    @Test
    public void testGetAllBenhNhan() {
        Assertions.assertEquals(2, new BenhNhanRepository().getAll(BenhNhan.class).size());
    }

    @Test
    public void testGetBenhNhanTheoId() {
        Assertions.assertEquals(BenhNhan.class, new BenhNhanRepository().getById(BenhNhan.class, "64cf6fb0-2049-4e89-9421-7752b651160c").getClass());
    }

    @Test
    public void testGelAllASC() {
        Assertions.assertEquals(2, new BenhNhanRepository().getAllOrderBy(BenhNhan.class, "ten", Order.asc).size());
    }

    @Test
    public void testGetAllDESC() {
        Assertions.assertEquals(2, new BenhNhanRepository().getAllOrderBy(BenhNhan.class, "ten", Order.desc).size());
    }

    @Test
    public void testGetTop() {
        Assertions.assertEquals(2, new BenhNhanRepository().getTop(BenhNhan.class, 2).size());
    }

    @Test
    public void testGetTopASC() {
        Assertions.assertEquals(2, new BenhNhanRepository()
                .getTopOrderBy(BenhNhan.class, 2, "ten", Order.asc).size());
    }

    @Test
    public void testGetTopDESC() {
        Assertions.assertEquals(2, new BenhNhanRepository()
                .getTopOrderBy(BenhNhan.class, 2, "ten", Order.desc).size());
    }

    @Test
    public void testGetBenhNhanTheoTen() {
        Assertions.assertEquals(1, new BenhNhanRepository().getBenhNhanTheoTen("Dat").size());
    }

    @Test
    public void testGetTopBenhNhanTheoTen() {
        Assertions.assertEquals(1, new BenhNhanRepository().getTopBenhNhanTheoTen(2, "Dat").size());
    }
}
