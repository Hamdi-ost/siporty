package com.donation.backend.demo.message.request;

import java.util.List;

public class UserInfo {

    private Long id;
    private String firstname;
    private String lastname;
    private String banque;
    private String agence;
    private String ccb;
    private String accountName;
    private String phone;
    private String socialLink;
    private String username;
    private String email;
    private List<String> roles;
    private boolean enabled;

    public UserInfo() {};

    public UserInfo(Long id, String firstname, String lastname, String banque, String agence,
                    String ccb, String accountName, String socialLink,
                    String username, String email, List<String> roles, boolean enabled, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.banque = banque;
        this.agence = agence;
        this.ccb = ccb;
        this.accountName = accountName;
        this.socialLink = socialLink;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
        this.phone = phone;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
