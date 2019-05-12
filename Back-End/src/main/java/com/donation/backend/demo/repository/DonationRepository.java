package com.donation.backend.demo.repository;

import com.donation.backend.demo.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT * FROM donation WHERE SUBSTR(donation.date, 4, 2) = ?1 AND SUBSTR(donation.date, 7, 4) = ?2 AND donation.donation_info_id = ?3", nativeQuery = true)
    List<Donation> getDonationsPerMonthAndDonationInfoId(String m, String y, long donationInfoId);

    @Query(value = "SELECT * FROM donation WHERE STR_TO_DATE(date,'%d/%m/%Y') BETWEEN STR_TO_DATE(?1,'%d/%m/%Y') AND STR_TO_DATE(?2,'%d/%m/%Y') AND donation.donation_info_id = ?3", nativeQuery = true)
    List<Donation> getDonationsPerWeek(String d1, String d2, long donationInfoId);

    @Query(value = "SELECT * FROM donation WHERE SUBSTR(donation.date, 4, 2) = ?1 AND SUBSTR(donation.date, 7, 4) = ?2 AND donation.donation_info_id = ?3 ORDER BY donation.montant DESC LIMIT 10", nativeQuery = true)
    List<Donation> getTopDonorsPerMonth(String m, String y, long donationInfoId);

    @Query(value = "SELECT * FROM donation WHERE STR_TO_DATE(date,'%d/%m/%Y') BETWEEN STR_TO_DATE(?1,'%d/%m/%Y') AND STR_TO_DATE(?2,'%d/%m/%Y') AND donation.donation_info_id = ?3 ORDER BY donation.montant DESC LIMIT 10", nativeQuery = true)
    List<Donation> getTopDonorsPerWeek(String m, String y, long donationInfoId);

    Long countAllByDonationInfoId(long donatioInfoId);
}
