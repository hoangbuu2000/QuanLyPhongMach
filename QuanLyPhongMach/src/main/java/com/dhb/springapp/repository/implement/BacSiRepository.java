package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.repository.IBacSiRepository;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BacSiRepository extends GenericRepository<BacSi> implements IBacSiRepository {
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<BacSi> getBacSiTheoTen(String ten) {
        try {
            Query q = currentSession().createQuery("From BacSi where ten like concat('%', :t, '%')");
            q.setParameter("t", ten);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<BacSi> getTopBacSiTheoTen(int limit, String name) {
        try {
            Query q = currentSession().createQuery("From BacSi where ten like concat('%', :name, '%') ")
                    .setMaxResults(limit).setParameter("name", name);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<ToaThuoc> getToaThuocTheoBacSi(BacSi bacSi) {
        try {
            return bacSi.getDsToaThuoc();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<BenhNhan> getBenhNhanTheoBacSi(BacSi bacSi) {
        try {
            Set<ToaThuoc> dsToaThuoc = bacSi.getDsToaThuoc();
            Set<BenhNhan> dsBenhNhan = new HashSet<>();

            dsToaThuoc.forEach(t -> {
                dsBenhNhan.add(t.getBenhNhan());
            });
            return dsBenhNhan;
        }
        catch (HibernateError e) {
            return null;
        }
    }
}
