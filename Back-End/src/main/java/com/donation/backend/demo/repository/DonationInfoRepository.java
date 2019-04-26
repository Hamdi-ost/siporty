package com.donation.backend.demo.repository;

import com.donation.backend.demo.model.DonationInfo;
import com.donation.backend.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationInfoRepository extends JpaRepository<DonationInfo, Long> {
    Optional<DonationInfo> findDonationInfoByUser(User user);
    Optional<DonationInfo> findDonationInfoByUrl(String url);
    Optional<DonationInfo> findByUserId(long id);
}
