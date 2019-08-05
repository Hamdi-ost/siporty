package com.donation.backend.demo.security.services;

import com.donation.backend.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstName;

    private String lastName;

    private String banque;
    private String agence;
    private String ccb;
    private String accountName;
    private String image;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String firstName, String lastName,
                         String banque, String agence, String ccb, String accountName,
                         String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.banque = banque;
        this.agence = agence;
        this.ccb = ccb;
        this.accountName = accountName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.image = image;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBanque(),
                user.getAgence(),
                user.getCcb(),
                user.getAccountName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getPhone()
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBanque() {
        return banque;
    }

    public String getAgence() {
        return agence;
    }

    public String getCcb() {
        return ccb;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
