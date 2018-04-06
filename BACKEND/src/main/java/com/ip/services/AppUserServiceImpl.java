package com.ip.services;

import com.ip.data.AppUserRepository;
import com.ip.data.RoleRepository;
import com.ip.domain.AppUser;
import com.ip.domain.Role;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ip.security.util.SecurityConstants.ADMIN;
import static com.ip.security.util.SecurityConstants.USER;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Set<AppUser> getAllUsers() {

        return Sets.newLinkedHashSet(userRepository.findAll());
    }

    @Override
    public Optional<AppUser> getUser(long id) {

        return userRepository.findById(id);
    }

    @Override
    public void addUser(AppUser user) throws ValidationException {

        var errors = new LinkedHashMap<String, String>();

        if (userRepository.existsByUsername(user.getUsername()))
            errors.put("username", "User with given username already exists");

        if (userRepository.existsByEmail(user.getEmail()))
            errors.put("email", "Email address already in use");

        if (!errors.isEmpty())
            throw new ValidationException(errors);

        user.addRoles(resolveRoles(user));
        userRepository.save(user);
    }

    private List<Role> resolveRoles(AppUser user) {

        return user.isAdminAccount() ?
                List.of(roleRepository.getByName(USER), roleRepository.getByName(ADMIN)) :
                List.of(roleRepository.getByName(USER));
    }

    @Override
    public void updateUser(long id, AppUser user) throws ValidationException {

        AppUser result = userRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("id", "User with given id doesn't exist"));

        if (userRepository.existsByUsername(user.getName()))
            throw new ValidationException("name", "User with given name already exists");

        result.update(user);
    }

    public void deleteUser(long id) throws ValidationException {

        if (!userRepository.existsById(id))
            throw new ValidationException("id", "Item with given id doesn't exist");

        userRepository.deleteById(id);
    }
}
