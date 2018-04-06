package com.ip.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles")
    private Set<AppUser> users = new HashSet<>();

    public Role(){}

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o){

        return id.equals(((Role) o).getId());
    }
}
