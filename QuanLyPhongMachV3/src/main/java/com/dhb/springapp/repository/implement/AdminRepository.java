package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.Admin;
import com.dhb.springapp.repository.IAdminRepository;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AdminRepository extends GenericRepository<Admin> implements IAdminRepository {
    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAdminTheoTen(String ten) {
        try {
            Query q = currentSession().createQuery("From Admin where ten like concat('%', :ten, '%')")
                    .setParameter("ten", ten);
            return q.getResultList();
        }
        catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAdminTheoTenVaID(String id, String ten) {
        try {
            Query q = currentSession().createQuery("From Admin where id = :i " +
                    "and ten like concat('%', :ten, '%')").setParameter("i", id).setParameter("ten", ten);
            return q.getResultList();
        }
        catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
