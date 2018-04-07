package com.ip.domain;

import java.util.List;

public class Developer {

    private String name;
    private String surname;
    private String email;
    private String level;
    private String bio;
    private String website;
    private List<String> sideTechnologies;
    private String mainLanguage;
    private String city;
    private boolean student;

    public Developer(String name, String surname, String email, String level, String bio, String website,
                     List<String> sideTechnologies, String mainLanguage, String city, boolean student) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.level = level;
        this.bio = bio;
        this.website = website;
        this.mainLanguage = mainLanguage;
        this.sideTechnologies = sideTechnologies;
        this.city = city;
        this.student = student;
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

    public String getLevel() {
        return level;
    }

    public String getWebsite() {
        return website;
    }

    public String getCity() {
        return city;
    }

    public boolean isStudent() {
        return student;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public List<String> getSideTechnologies() {
        return sideTechnologies;
    }

    public String getBio() {
        return bio;
    }
}


