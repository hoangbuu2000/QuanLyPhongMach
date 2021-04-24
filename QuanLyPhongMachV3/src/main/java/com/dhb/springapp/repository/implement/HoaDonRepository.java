package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.HoaDon;
import com.dhb.springapp.repository.IHoaDonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class HoaDonRepository extends GenericRepository<HoaDon> implements IHoaDonRepository {
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean addOrUpdate(String id, HoaDon hoaDon) {
        HoaDon result;
        if (id != null && !id.isEmpty()) {
            result = update(hoaDon);
        }
        else {
            result = insert(hoaDon);
        }
        return result != null;
    }

    @Override
    @Transactional(readOnly = true)
    public HoaDon getHoaDonTheoToaThuoc(String maToaThuoc) {
        HoaDon result = (HoaDon) currentSession().createQuery("FROM HoaDon where toaThuoc.id = :id")
                .setParameter("id", maToaThuoc).getResultList().get(0);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HoaDon> getHoaDonFromTo(String from, String to) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<HoaDon> q = builder.createQuery(HoaDon.class);
        Root<HoaDon> root = q.from(HoaDon.class);
        q.select(root);

        if (from != null && !from.isEmpty() && to != null && !to.isEmpty())
            q.where(builder.between(root.get("ngayXuat").as(Date.class), format.parse(from),
                    format.parse(to)));
        else if ((from != null && !from.isEmpty()) && (to == null || to.isEmpty()))
            q.where(builder.greaterThan(root.get("ngayXuat").as(Date.class), format.parse(from)));
        else if ((from == null || from.isEmpty()) && (to != null && !to.isEmpty()))
            q.where(builder.lessThan(root.get("ngayXuat").as(Date.class), format.parse(to)));
        return currentSession().createQuery(q).getResultList();
    }
}
