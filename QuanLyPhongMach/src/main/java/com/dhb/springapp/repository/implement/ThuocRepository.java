package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.repository.IThuocRepository;
import org.hibernate.HibernateError;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ThuocRepository extends GenericRepository<Thuoc> implements IThuocRepository {
    @Override
    public List<Thuoc> getThuocTheoTen(String ten) {
        try {
            Query q = currentSession().createQuery("From Thuoc where tenThuoc like concat('%', :t, '%') ").setParameter("t", ten);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Override
    public List<Thuoc> getThuocTheoDonGia(BigDecimal from, BigDecimal to) {
        try {
            CriteriaBuilder builder = currentSession().getCriteriaBuilder();
            CriteriaQuery<Thuoc> query = builder.createQuery(Thuoc.class);
            Root<Thuoc> root = query.from(Thuoc.class);
            query.select(root);

            query.where(builder.between(root.get("donGia").as(BigDecimal.class), from, to));

            Query q = currentSession().createQuery(query);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Override
    public List<Thuoc> getThuocTheoMoTa(String description) {
        try {
            Query q = currentSession().createQuery("From Thuoc where moTa like concat('%', :des, '%')")
                    .setParameter("des", description);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Override
    public List<Thuoc> getThuocTheoDonVi(String unit) {
        try {
            Query q = currentSession().createQuery("From Thuoc where donVi like concat('%', :unit, '%')")
                    .setParameter("unit", unit);
            q.getResultList().forEach(System.out::println);
            return q.getResultList();
        }
        catch (HibernateError e) {
            return null;
        }
    }
}
