package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.Role;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.service.IGenericService;
import com.dhb.springapp.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role> implements IRoleService {
    private IGenericRepository<Role> roleRepository;

    @Autowired
    public RoleService(@Qualifier("roleRepository")IGenericRepository<Role> iGenericRepository) {
        super(iGenericRepository);
        this.roleRepository = iGenericRepository;
    }
}
