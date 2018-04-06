package com.ip.domain;

import java.util.List;

public class Developer {

    private String name;
    private String surname;
    private String email;
    private String position;
    private String website;
    private List<String> languages;

    public Developer(String name, String surname, String email, String position, String website, List<String> languages) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
        this.website = website;
        this.languages = languages;
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

    public String getPosition() {
        return position;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getLanguages() {
        return languages;
    }
}


