package com.ip.data.util;

import com.ip.data.AppUserRepository;
import com.ip.data.RoleRepository;
import com.ip.domain.AppUser;
import com.ip.domain.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.ip.security.util.SecurityConstants.ADMIN;
import static com.ip.security.util.SecurityConstants.USER;

@Component
public class DBInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(DBInitializer.class);


    private AppUserRepository appUserRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public DBInitializer(AppUserRepository appUserRepository,
                         RoleRepository roleRepository,
                         BCryptPasswordEncoder encoder) {


        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    //FIXME: temporary
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));

        AppUser admin = new AppUser("admin", "admin", "admin@admin.com", "root", "Root123");
        admin.addRole(roleRepository.getByName(ADMIN));
        admin.encodePassword(encoder);
        appUserRepository.save(admin);

        AppUser user = new AppUser("user", "user", "user@user.com", "user", "User123");
        user.addRole(roleRepository.getByName(USER));
        user.encodePassword(encoder);
        appUserRepository.save(user);
        logger.info("Generated some sample data");
    }
}
