package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.*;
import com.dhb.springapp.repository.IBenhNhanRepository;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BenhNhanRepository extends GenericRepository<BenhNhan> implements IBenhNhanRepository {
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<BenhNhan> getBenhNhanTheoTen(String ten) {
        try {
            currentSession().beginTransaction();
            Query q = currentSession().createQuery("From BenhNhan where ten like concat('%', :t, '%')");
            q.setParameter("t", ten);
            currentSession().getTransaction().commit();
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name) {
        try {
            currentSession().beginTransaction();
            Query q = currentSession().createQuery("From BenhNhan where ten like concat('%', :name, '%') ")
                    .setMaxResults(limit).setParameter("name", name);
            currentSession().getTransaction().commit();
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan) {
        try {
            Query q = currentSession().createQuery("From PhieuKhamBenh where benhNhan.id=:maBenhNhan")
                    .setParameter("maBenhNhan", benhNhan.getId());
            return q.getResultList().size();
        }
        catch (HibernateError e) {
            return 0;
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<BenhNhan> getTopNewBenhNhan(int limit) {
        try {
            List<BenhNhan> ds = new ArrayList<>();

            Query q = currentSession().createSQLQuery("CALL getTopNewPatients(:limit)")
                    .addEntity(BenhNhan.class).setParameter("limit", limit);
            q.getResultList().forEach(b -> {
                if (getSoLuongPhieuKhamBenhCuaBenhNhan((BenhNhan) b) == 1)
                    ds.add((BenhNhan) b);
            });
            return ds;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themBenhNhanVaPhieuKhamBenh(BenhNhan benhNhan, PhieuKhamBenh phieuKhamBenh) {
        BenhNhan b = update(benhNhan);
        currentSession().save(phieuKhamBenh);

        if (b != null)
            return true;
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Object[]> getBenhAnTheoBenhNhan(String id) {
        // Can nhan xai builder
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        Root<ChiTietToaThuoc> chiTietToaThuoc = q.from(ChiTietToaThuoc.class);
        Join<ChiTietToaThuoc, ToaThuoc> toaThuoc = chiTietToaThuoc.join("toaThuoc");
        Join<ToaThuoc, BenhNhan> benhNhan = toaThuoc.join("benhNhan");
        Join<ToaThuoc, BacSi> bacSi = toaThuoc.join("bacSi");
        Join<ToaThuoc, LoaiBenh> loaiBenh = toaThuoc.join("loaiBenh");

        q.multiselect(toaThuoc.get("id"), bacSi.get("ten"), toaThuoc.get("ngayKeToa"),
                loaiBenh.get("tenBenh"), loaiBenh.get("moTa"), loaiBenh.get("donGia"));
        q.where(builder.equal(benhNhan.get("id").as(String.class), id));
        q.groupBy(toaThuoc.get("id"));
        List<Object[]> result = currentSession().createQuery(q).getResultList();

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BenhNhan> getBenhNhanCoLichKhamTheoThoiGianChoTruoc(Date date, int maCa) {
        List<BenhNhan> result = currentSession().createSQLQuery(
                "CALL getBenhNhanCoLichKhamTheoThoiGianChoTruoc(:d, :c)")
                .addEntity(BenhNhan.class).setParameter("d", date).setParameter("c", maCa)
                .getResultList();
        return result;
    }
}
