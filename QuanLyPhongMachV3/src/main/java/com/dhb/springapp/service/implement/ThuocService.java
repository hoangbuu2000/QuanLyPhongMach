package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.IThuocRepository;
import com.dhb.springapp.service.IThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ThuocService extends GenericService<Thuoc> implements IThuocService {
    private IThuocRepository thuocRepository;

    @Autowired
    public ThuocService(@Qualifier("thuocRepository")IGenericRepository<Thuoc> genericRepository) {
        super(genericRepository);
        this.thuocRepository = (IThuocRepository) genericRepository;
    }
}
