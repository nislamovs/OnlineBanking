package com.example.banking.repository;

import com.example.banking.model.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
