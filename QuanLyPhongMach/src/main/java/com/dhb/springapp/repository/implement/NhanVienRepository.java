package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.repository.INhanVienRepository;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NhanVienRepository extends GenericRepository<NhanVien> implements INhanVienRepository {
    @Override
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
