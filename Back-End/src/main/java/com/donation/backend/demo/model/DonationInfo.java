package com.donation.backend.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DonationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private float solde;
    private String image;
    private String url;
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Donation.class)
    private List<Donation> donations;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User user;

    public DonationInfo() {
    }

    public DonationInfo(String title, String message, float solde, String image, String url, boolean enabled,
                        List<Donation> donations, User user) {
        this.title = title;
        this.message = message;
        this.solde = solde;
        this.image = image;
        this.url = url;
        this.enabled = enabled;
        this.donations = donations;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
