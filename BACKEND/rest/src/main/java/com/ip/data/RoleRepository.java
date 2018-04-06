package com.ip.data;

import com.ip.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role getByName(String name);
}
