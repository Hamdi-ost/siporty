package com.donation.backend.demo.message.request;

import java.util.List;

public class DonationInfoMessageUserId {

    private long id;
    private String title;
    private String message;
    private float solde;
    private String image;
    private String url;
    private UserInfo userInfo;
    private List<DonationMessage> donationMessages;

    public DonationInfoMessageUserId() {
    }

    public DonationInfoMessageUserId(long id, String title, String message, float solde, String image, String url,
                                     UserInfo userInfo, List<DonationMessage> donationMessages) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.solde = solde;
        this.image = image;
        this.url = url;
        this.userInfo = userInfo;
        this.donationMessages = donationMessages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<DonationMessage> getDonationMessages() {
        return donationMessages;
    }

    public void setDonationMessages(List<DonationMessage> donationMessages) {
        this.donationMessages = donationMessages;
    }
}
