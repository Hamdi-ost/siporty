package com.donation.backend.demo.model;

import javax.persistence.*;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = DonationInfo.class)
    private DonationInfo donationInfo;

    private float montant;
    private String date;
    private boolean enabled;

    public Donation() {
    }

    public Donation(DonationInfo donationInfo, float montant, String date, boolean enabled) {
        this.donationInfo = donationInfo;
        this.montant = montant;
        this.date = date;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DonationInfo getDonationInfo() {
        return donationInfo;
    }

    public void setDonationInfo(DonationInfo donationInfo) {
        this.donationInfo = donationInfo;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
