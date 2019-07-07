package com.donation.backend.demo.message.request;

public class PasswordChangeForm {

    private Long id;
    private String password;
    private String socialLink;
    private String image;

    public PasswordChangeForm() {
    }

    public PasswordChangeForm(Long id, String password, String socialLink) {
        this.id = id;
        this.password = password;
        this.socialLink = socialLink;
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

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
