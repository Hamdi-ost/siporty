package com.donation.backend.demo.message.request;

public class DonationMessage {

    private float montant;
    private String date;

    public DonationMessage() {
    }

    public DonationMessage(float montant, String date) {
        this.montant = montant;
        this.date = date;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
