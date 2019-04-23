package com.donation.backend.demo.message.request;

import java.util.List;

public class UserInfo {

    private Long id;
    private String firstname;
    private String lastname;
    private String banque;
    private String agence;
    private String ccb;
    private String username;
    private String email;
    private List<String> roles;
    private boolean enabled;

    public UserInfo() {};

    public UserInfo(Long id, String firstname, String lastname, String banque, String agence,
                    String ccb, String username, String email, List<String> roles, boolean enabled) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.banque = banque;
        this.agence = agence;
        this.ccb = ccb;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getCcb() {
        return ccb;
    }

    public void setCcb(String ccb) {
        this.ccb = ccb;
    }
}
