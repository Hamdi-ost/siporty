package com.donation.backend.demo.message.request;

import java.util.List;

public class DonationInfoMessageUserId {

    private long id;
    private String solde;
    private String url;
    private String socialLink;
    private UserInfo userInfo;
    private List<DonationMessage> donationMessages;

    public DonationInfoMessageUserId() {
    }

    public DonationInfoMessageUserId(long id, String solde, String url, String socialLink,
                                     UserInfo userInfo, List<DonationMessage> donationMessages) {
        this.id = id;
        this.solde = solde;
        this.url = url;
        this.socialLink = socialLink;
        this.userInfo = userInfo;
        this.donationMessages = donationMessages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
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
