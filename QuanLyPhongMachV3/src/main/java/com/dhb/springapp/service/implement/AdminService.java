package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.repository.IAdminRepository;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends GenericService<Admin> implements IAdminService {
    private IAdminRepository adminRepository;

    @Autowired
    public AdminService(@Qualifier("adminRepository")IGenericRepository<Admin> genericRepository) {
        super(genericRepository);
        this.adminRepository = (IAdminRepository) genericRepository;
    }
}
