package com.ip.data;


import com.ip.domain.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
