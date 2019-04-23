package com.donation.backend.demo.message.request;

public class DonationMessage {

    private float montant;
    private String date;
    private String name;
    private String message;

    public DonationMessage() {
    }

    public DonationMessage(float montant, String date, String name, String message) {
        this.montant = montant;
        this.date = date;
        this.name = name;
        this.message = message;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
