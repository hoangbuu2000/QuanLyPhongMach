package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.ChiTietCaKhamBenh;
import com.dhb.springapp.repository.IChiTietCaKhamBenhRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class ChiTietCaKhamBenhRepository extends GenericRepository<ChiTietCaKhamBenh> implements IChiTietCaKhamBenhRepository {
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSchedule(ChiTietCaKhamBenh oldScchedule, ChiTietCaKhamBenh newSchedule) throws Exception {
        int row = currentSession().createQuery("update ChiTietCaKhamBenh set ngayKhamBenh=:d, caKhamBenh=:c, " +
                "bacSi=:b where ngayKhamBenh=:od and caKhamBenh=:oc and bacSi=:ob")
                .setParameter("d", newSchedule.getNgayKhamBenh())
                .setParameter("c", newSchedule.getCaKhamBenh())
                .setParameter("b", newSchedule.getBacSi())
                .setParameter("od", oldScchedule.getNgayKhamBenh())
                .setParameter("oc", oldScchedule.getCaKhamBenh())
                .setParameter("ob", oldScchedule.getBacSi()).executeUpdate();
        if (row <= 0)
            throw new Exception("Giao tac sua that bai");
    }
}
