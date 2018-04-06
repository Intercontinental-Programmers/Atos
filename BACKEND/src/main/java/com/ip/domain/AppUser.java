package com.ip.domain;

import com.ip.domain.validators.Password;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 50, message = "First name has to be between 2 and 50 characters long")
    @NotNull(message = "First name cannot be null")
    private String name;

    @Size(min = 2, max = 50, message = "Surname has to be between 2 and 50 characters long")
    @NotNull(message = "Surname cannot be null")
    private String surname;

    @Email(message = "Invalid email address format")
    @NotNull(message = "Email address cannot be null")
    private String email;

    @Size(min = 4, max = 50, message = "Username has to be between 4 and 50 characters long")
    @NotNull(message = "Username cannot be null")
    private String username;

    @Password(message = "Password must contain at least one number and one capital letter")
    @Size(min = 6, max = 256, message = "Password has to be between 6 and 50 characters long")
    @NotNull(message = "Password cannot be null")
    private String password;

    @JsonIgnore
    private boolean adminAccount = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();

    protected AppUser() {
    }

    public AppUser(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean isAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount() {
        adminAccount = true;
    }

    public void update(AppUser user) {
        name = user.name;
        surname = user.surname;
        email = user.email;
        username = user.username;
        password = user.password;
    }

    public void encodePassword(BCryptPasswordEncoder encoder) {
        password = encoder.encode(password);
    }

    public void addRoles(Collection<Role> roles) {

        this.roles.addAll(roles);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}