package com.dhb.springapp.repository;

import com.dhb.springapp.models.Thuoc;
import java.math.BigDecimal;
import java.util.List;

public interface IThuocRepository extends IGenericRepository<Thuoc> {
    List<Thuoc> getThuocTheoTen(String ten);

    List<Thuoc> getThuocTheoDonGia(BigDecimal from, BigDecimal to);

    List<Thuoc> getThuocTheoMoTa(String description);

    List<Thuoc> getThuocTheoDonVi(String unit);
}
