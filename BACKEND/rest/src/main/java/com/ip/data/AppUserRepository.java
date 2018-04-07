package com.ip.data;


import com.ip.domain.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByCompanyName(String companyName);

    boolean existsByCompanyName(String companyName);

    boolean existsByEmail(String email);
}
