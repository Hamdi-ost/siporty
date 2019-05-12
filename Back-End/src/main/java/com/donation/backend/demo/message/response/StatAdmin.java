package com.donation.backend.demo.message.response;

import com.donation.backend.demo.message.request.DonationMessage;

import java.util.List;

public class StatAdmin {

    private float incomeThisWeek;
    private float incomeThisMonth;

    private float amountStillNotPayed;

    private long totalUsers;
    private long totalDonors;

    private List<DonationMessage> topTenDonorsMonth;
    private List<DonationMessage> topTenDonorsWeek;

    public StatAdmin() {
        this.amountStillNotPayed = 0;
    }

    public float getAmountStillNotPayed() {
        return amountStillNotPayed;
    }

    public void setAmountStillNotPayed(float amountStillNotPayed) {
        this.amountStillNotPayed = amountStillNotPayed;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public float getIncomeThisWeek() {
        return incomeThisWeek;
    }

    public void setIncomeThisWeek(float incomeThisWeek) {
        this.incomeThisWeek = incomeThisWeek;
    }

    public float getIncomeThisMonth() {
        return incomeThisMonth;
    }

    public void setIncomeThisMonth(float incomeThisMonth) {
        this.incomeThisMonth = incomeThisMonth;
    }

    public long getTotalDonors() {
        return totalDonors;
    }

    public void setTotalDonors(long totalDonors) {
        this.totalDonors = totalDonors;
    }

    public List<DonationMessage> getTopTenDonorsMonth() {
        return topTenDonorsMonth;
    }

    public void setTopTenDonorsMonth(List<DonationMessage> topTenDonorsMonth) {
        this.topTenDonorsMonth = topTenDonorsMonth;
    }

    public List<DonationMessage> getTopTenDonorsWeek() {
        return topTenDonorsWeek;
    }

    public void setTopTenDonorsWeek(List<DonationMessage> topTenDonorsWeek) {
        this.topTenDonorsWeek = topTenDonorsWeek;
    }
}
