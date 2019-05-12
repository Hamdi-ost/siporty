package com.donation.backend.demo.message.response;

import com.donation.backend.demo.message.request.DonationMessage;

import java.util.List;

public class IncomeBetweenDates {

    private float income;
    List<DonationMessage> donationMessages;

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public List<DonationMessage> getDonationMessages() {
        return donationMessages;
    }

    public void setDonationMessages(List<DonationMessage> donationMessages) {
        this.donationMessages = donationMessages;
    }
}
