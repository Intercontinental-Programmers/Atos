package com.ip.services;

import com.ip.domain.AppUser;

import java.util.Optional;
import java.util.Set;

public interface AppUserService {

    Set<AppUser> getAllUsers();

    Optional<AppUser> getUser(long id);

    void addUser(AppUser user) throws ValidationException;

    void updateUser(long id, AppUser user) throws ValidationException;

    void deleteUser(long id) throws ValidationException;
}
