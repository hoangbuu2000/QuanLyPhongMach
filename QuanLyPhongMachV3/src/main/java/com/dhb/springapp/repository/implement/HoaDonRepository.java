package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.HoaDon;
import com.dhb.springapp.repository.IHoaDonRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HoaDonRepository extends GenericRepository<HoaDon> implements IHoaDonRepository {
}
