package com.ip.rest.util;

import com.ip.domain.AppUser;

public class UserResponse {

    private String companyName;
    private String username;
    private String email;

    public UserResponse(AppUser user) {
        companyName = user.getCompanyName();
        email = user.getEmail();
        username = user.getUsername();
    }


    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getCompanyName() {
        return companyName;
    }
}

