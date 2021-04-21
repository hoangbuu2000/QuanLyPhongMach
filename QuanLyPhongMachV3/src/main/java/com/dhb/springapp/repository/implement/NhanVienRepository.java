package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.repository.INhanVienRepository;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NhanVienRepository extends GenericRepository<NhanVien> implements INhanVienRepository {
    @Override
    @Transactional(readOnly = true)
    public List<NhanVien> getNhanVienTheoTen(String ten) {
        try {
            Query q = currentSession().createQuery("From NhanVien where ten like concat('%', :ten, '%')")
                    .setParameter("ten", ten);
            return q.getResultList();
        }
        catch (HibernateException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<NhanVien> getNhanVienTheoTenVaID(String id, String ten) {
        try {
            Query q = currentSession().createQuery("From NhanVien where id = :i " +
                    "and ten like concat('%', :ten, '%')").setParameter("i", id).setParameter("ten", ten);
            return q.getResultList();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
