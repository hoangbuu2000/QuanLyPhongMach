package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.LoaiBenh;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;

import java.util.List;


public class LoaiBenhRepository extends GenericRepository<LoaiBenh> {
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
