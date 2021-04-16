package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.repository.ICaKhamBenhRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CaKhamBenhRepository extends GenericRepository<CaKhamBenh> implements ICaKhamBenhRepository {
    @Override
    @Transactional(readOnly = true)
    public List<BacSi> layBacSiTheoCaKham(int id, Date ngayKhamBenh) {
        List<BacSi> result;
        result = currentSession().createSQLQuery("CALL getBacSiTheoCaKhamBenh(:c, :d)")
                .addEntity(BacSi.class).setParameter("c", id).setParameter("d", ngayKhamBenh).getResultList();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CaKhamBenh> layCaKhamTheoNgayKham(Date ngayKhamBenh) {
        List<CaKhamBenh> result;
        result = currentSession().createSQLQuery("CALL getCaKhamBenhTheoNgayKham(:d)")
                .addEntity(CaKhamBenh.class).setParameter("d", ngayKhamBenh).getResultList();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CaKhamBenh> layCaKhamConTrong(Date ngayKhamBenh) {
        List<CaKhamBenh> result;
        result = currentSession().createSQLQuery("CALL getCaKhamBenhConTrong(:d)")
                .addEntity(CaKhamBenh.class).setParameter("d", ngayKhamBenh).getResultList();
        return result;
    }
}
