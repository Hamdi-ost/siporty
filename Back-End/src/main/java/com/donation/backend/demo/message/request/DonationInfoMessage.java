package com.donation.backend.demo.message.request;

import java.util.List;

public class DonationInfoMessage {

    private float solde;
    private String url;
    private String socialLink;
    private UserInfo userInfo;
    private List<DonationMessage> donationMessages;

    public DonationInfoMessage() {
    }

    public DonationInfoMessage(float solde, String url, String socialLink, UserInfo userInfo, List<DonationMessage> donationMessages) {
        this.solde = solde;
        this.url = url;
        this.socialLink = socialLink;
        this.userInfo = userInfo;
        this.donationMessages = donationMessages;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
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

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }
}
