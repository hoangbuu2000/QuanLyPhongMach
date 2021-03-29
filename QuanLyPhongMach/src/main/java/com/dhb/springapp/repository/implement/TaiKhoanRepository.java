package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.TaiKhoan;
import org.hibernate.query.Query;

import java.util.List;


public class TaiKhoanRepository extends GenericRepository<TaiKhoan> {
    public List<TaiKhoan> getTaiKhoanByUsername(String username) {
        Query q = currentSession().createQuery("From TaiKhoan where username=:u");
        q.setParameter("u", username);
        return q.getResultList();
    }

    public List<TaiKhoan> getTaiKhoanTheoChucVu(String kw) {
        Query q = currentSession().createQuery("From TaiKhoan where chucVu=:c");
        q.setParameter("c", kw);
        return q.getResultList();
    }
}
