package com.donation.backend.demo.message.response;

import com.donation.backend.demo.message.request.DonationMessage;
import com.donation.backend.demo.message.request.UserInfo;

import java.util.List;

public class StatAdmin {

    private List<DonationMessage> donations;

    private String visitPerDay;
    private String visitPerWeek;
    private String visitPerMonth;

    private float totalMoney;

    private long totalUsers;

    private List<DonationMessage> topTenDonors;

    public StatAdmin() {
        this.totalMoney = 0;
    }

    public List<DonationMessage> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationMessage> donations) {
        this.donations = donations;
    }

    public String getVisitPerDay() {
        return visitPerDay;
    }

    public void setVisitPerDay(String visitPerDay) {
        this.visitPerDay = visitPerDay;
    }

    public String getVisitPerWeek() {
        return visitPerWeek;
    }

    public void setVisitPerWeek(String visitPerWeek) {
        this.visitPerWeek = visitPerWeek;
    }

    public String getVisitPerMonth() {
        return visitPerMonth;
    }

    public void setVisitPerMonth(String visitPerMonth) {
        this.visitPerMonth = visitPerMonth;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public List<DonationMessage> getTopTenDonors() {
        return topTenDonors;
    }

    public void setTopTenDonors(List<DonationMessage> topTenDonors) {
        this.topTenDonors = topTenDonors;
    }
}
