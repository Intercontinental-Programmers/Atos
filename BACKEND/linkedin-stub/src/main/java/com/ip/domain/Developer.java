package com.ip.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String name;
    private String surname;
    private String email;
    private String level;
    private String website;
    private String mainLanguage;
    private String city;
    private boolean student;
    private String bio;

    public Developer(String name, String surname, String email, String level,
                     String website, String mainLanguage, String city, String bio, boolean student) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.level = level;
        this.website = website;
        this.mainLanguage = mainLanguage;
        this.city = city;
        this.student = student;
        this.bio = bio;
    }

    public Developer() {
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

    public String getBio() {
        return bio;
    }
}


