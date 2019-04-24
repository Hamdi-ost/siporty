package com.donation.backend.demo.message.request;

public class PasswordChangeForm {

    private Long id;
    private String password;

    public PasswordChangeForm() {
    }

    public PasswordChangeForm(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}