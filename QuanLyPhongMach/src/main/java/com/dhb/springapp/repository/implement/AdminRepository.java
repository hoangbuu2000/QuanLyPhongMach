package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.repository.IAdminRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository extends GenericRepository<Admin> implements IAdminRepository {
}
