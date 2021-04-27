package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.repository.ITaiKhoanRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaiKhoanRepository extends GenericRepository<TaiKhoan> implements ITaiKhoanRepository {
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public TaiKhoan getTaiKhoanByUsername(String username) {
        Query q = currentSession().createQuery("From TaiKhoan where username=:u");
        q.setParameter("u", username);

        return q.getResultList().isEmpty() ? null : (TaiKhoan) q.getResultList().get(0);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaiKhoan> getTaiKhoanTheoChucVu(String kw) {
        Query q = currentSession().createQuery("From TaiKhoan where role.ten=:c");
        q.setParameter("c", kw);
        return q.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themTaiKhoanVaBacSi(TaiKhoan taiKhoan, BacSi bacSi) {
        TaiKhoan t = insert(taiKhoan);
        currentSession().save(bacSi);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suaTaiKhoanVaBacSi(TaiKhoan taiKhoan, BacSi bacSi) {
        TaiKhoan t = update(taiKhoan);
//        currentSession().evict(bacSi);
        currentSession().saveOrUpdate(bacSi);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themTaiKhoanVaNhanVien(TaiKhoan taiKhoan, NhanVien nhanVien) {
        TaiKhoan t = insert(taiKhoan);
        currentSession().save(nhanVien);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suaTaiKhoanVaNhanVien(TaiKhoan taiKhoan, NhanVien nhanVien) {
        TaiKhoan t = update(taiKhoan);
        currentSession().saveOrUpdate(nhanVien);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themTaiKhoanVaAdmin(TaiKhoan taiKhoan, Admin admin) {
        TaiKhoan t = insert(taiKhoan);
        currentSession().save(admin);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suaTaiKhoanVaAdmin(TaiKhoan taiKhoan, Admin admin) {
        TaiKhoan t = update(taiKhoan);
        currentSession().saveOrUpdate(admin);

        return t != null;
    }
}
