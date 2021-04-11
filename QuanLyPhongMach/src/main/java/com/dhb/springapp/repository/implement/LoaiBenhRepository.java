package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.repository.ILoaiBenhRepository;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LoaiBenhRepository extends GenericRepository<LoaiBenh> implements ILoaiBenhRepository {
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<LoaiBenh> getLoaiBenhTheoTen(String ten) {
        try {
            Query q = currentSession().createQuery("From LoaiBenh where tenBenh like concat('%', :ten, '%')")
                    .setParameter("ten", ten);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<LoaiBenh> getLoaiBenhTheoMoTa(String moTa) {
        try {
            Query q = currentSession().createQuery("From LoaiBenh where moTa like concat('%', :des, '%')")
                    .setParameter("des", moTa);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }
}
