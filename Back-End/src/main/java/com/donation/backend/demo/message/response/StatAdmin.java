package com.donation.backend.demo.message.response;

public class StatAdmin {

    private String donationsPerDay;
    private String donationsPerWeek;
    private String donationsPerMonth;

    private String visitPerDay;
    private String visitPerWeek;
    private String visitPerMonth;

    private String totalFlous;

    private String totalUsers;

    private String topTenDonors;

    public StatAdmin() {
    }

    public StatAdmin(String donationsPerDay, String donationsPerWeek, String donationsPerMonth, String visitPerDay,
                     String visitPerWeek, String visitPerMonth, String totalFlous, String totalUsers, String topTenDonors) {
        this.donationsPerDay = donationsPerDay;
        this.donationsPerWeek = donationsPerWeek;
        this.donationsPerMonth = donationsPerMonth;
        this.visitPerDay = visitPerDay;
        this.visitPerWeek = visitPerWeek;
        this.visitPerMonth = visitPerMonth;
        this.totalFlous = totalFlous;
        this.totalUsers = totalUsers;
        this.topTenDonors = topTenDonors;
    }

    public String getDonationsPerDay() {
        return donationsPerDay;
    }

    public void setDonationsPerDay(String donationsPerDay) {
        this.donationsPerDay = donationsPerDay;
    }

    public String getDonationsPerWeek() {
        return donationsPerWeek;
    }

    public void setDonationsPerWeek(String donationsPerWeek) {
        this.donationsPerWeek = donationsPerWeek;
    }

    public String getDonationsPerMonth() {
        return donationsPerMonth;
    }

    public void setDonationsPerMonth(String donationsPerMonth) {
        this.donationsPerMonth = donationsPerMonth;
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

    public String getTotalFlous() {
        return totalFlous;
    }

    public void setTotalFlous(String totalFlous) {
        this.totalFlous = totalFlous;
    }

    public String getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(String totalUsers) {
        this.totalUsers = totalUsers;
    }

    public String getTopTenDonors() {
        return topTenDonors;
    }

    public void setTopTenDonors(String topTenDonors) {
        this.topTenDonors = topTenDonors;
    }
}
