package com.donation.backend.demo.repository;

import com.donation.backend.demo.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT * FROM donation WHERE SUBSTR(donation.date, 4, 2) = ?1 AND SUBSTR(donation.date, 7, 4) = ?2", nativeQuery = true)
    List<Donation> getDonationsPerMonth(int m, int y);

    @Query(value = "SELECT * FROM donation LEFT JOIN donation_info ON donation.donation_info_id = donation_info.id ORDER BY donation.montant DESC LIMIT 10", nativeQuery = true)
    List<Donation> getTopDonors();
}
