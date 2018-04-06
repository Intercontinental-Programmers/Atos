package com.ip.rest.util;

import com.ip.domain.AppUser;

public class UserResponse {

    private String name;
    private String surname;
    private String username;
    private String email;

    public UserResponse(AppUser user) {
        name = user.getName();
        surname = user.getSurname();
        email = user.getEmail();
        username = user.getUsername();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}

