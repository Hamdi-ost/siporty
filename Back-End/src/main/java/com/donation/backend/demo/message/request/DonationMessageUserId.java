package com.donation.backend.demo.message.request;

public class DonationMessageUserId {

    private long id;
    private float montant;
    private String name;
    private String message;

    public DonationMessageUserId() {
    }

    public DonationMessageUserId(long id, float montant, String name, String message) {
        this.id = id;
        this.montant = montant;
        this.name = name;
        this.message = message;
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
