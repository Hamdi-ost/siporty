package com.donation.backend.demo.message.request;

public class DonationMessageUserId {

    private long id;
    private float montant;
    private String date;

    public DonationMessageUserId() {
    }

    public DonationMessageUserId(long id, float montant, String date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
