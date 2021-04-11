package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.Role;
import com.dhb.springapp.repository.IRoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository extends GenericRepository<Role> implements IRoleRepository {
}
