package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.repository.IChiTietToaThuocRepository;
import com.dhb.springapp.repository.IToaThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ToaThuocRepository extends GenericRepository<ToaThuoc> implements IToaThuocRepository {
    @Autowired
    IChiTietToaThuocRepository iChiTietToaThuocRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean addOrUpdate(String id, ToaThuoc toaThuoc,
                               List<ChiTietToaThuoc> chiTietToaThuocList) {
        ToaThuoc result;
        if (id != null && !id.isEmpty()) {
            result = update(toaThuoc);

            chiTietToaThuocList.forEach(t -> {
                iChiTietToaThuocRepository.update(t);
            });

        }
        else {
            result = insert(toaThuoc);

            chiTietToaThuocList.forEach(t -> {
                iChiTietToaThuocRepository.insert(t);
            });

        }
        return result != null;
    }
}
