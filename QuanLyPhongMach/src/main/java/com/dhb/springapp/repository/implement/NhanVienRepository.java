package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.NhanVien;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;

import java.util.List;


public class NhanVienRepository extends GenericRepository<NhanVien> {
    public List<NhanVien> getNhanVienTheoTen(String ten) {
        try {
            Query q = currentSession().createQuery("From NhanVien where ten like concat('%', :ten, '%')")
                    .setParameter("ten", ten);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }
}
