package com.donation.backend.demo.message.response;

import com.donation.backend.demo.message.request.DonationMessage;

import java.util.List;

public class StatAdmin {

    private float incomeThisWeek;
    private float incomeThisMonth;

    private float amountStillNotPayed;

    private long totalUsers;
    private long totalDonors;

    private List<DonationMessage> topTenDonors;

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

    public List<DonationMessage> getTopTenDonors() {
        return topTenDonors;
    }

    public void setTopTenDonors(List<DonationMessage> topTenDonors) {
        this.topTenDonors = topTenDonors;
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
}
