package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.*;
import com.donation.backend.demo.message.response.ResponseMessage;
import com.donation.backend.demo.model.Donation;
import com.donation.backend.demo.model.DonationInfo;
import com.donation.backend.demo.model.User;
import com.donation.backend.demo.repository.DonationInfoRepository;
import com.donation.backend.demo.repository.DonationRepository;
import com.donation.backend.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonationInfoRepository donationInfoRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DonationMessage>> allDonations() {

        try {
            List<Donation> donations = new ArrayList<>(donationRepository.findAll());
            if(donations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<DonationMessage> donationMessages = new ArrayList<>();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });

            return new ResponseEntity<>(donationMessages, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DonationMessage> getDonationById(@PathVariable("id") long id) {

        Optional<Donation> _donation = donationRepository.findById(id);
        if(_donation.isPresent()) {
            Donation donation = _donation.get();

            DonationMessage dm = new DonationMessage();
            dm.setMontant(donation.getMontant());
            dm.setDate(donation.getDate());

            return new ResponseEntity<>(dm, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> newMessage(@RequestBody DonationMessageUserId donationMessage) {

        Donation donation = new Donation();
        donation.setMontant(donationMessage.getMontant());
        donation.setDate(donationMessage.getDate());
        donation.setEnabled(true);

        Optional<User> _user = userRepository.findById(donationMessage.getId());
        if(_user.isPresent())
        {
            User user = _user.get();
            Optional<DonationInfo> _donationInfo = donationInfoRepository.findDonationInfoByUser(user);
            if(_donationInfo.isPresent())
            {
                DonationInfo donationInfo = _donationInfo.get();
                donation.setDonationInfo(donationInfo);
                donationRepository.save(donation);
                return new ResponseEntity<>(new ResponseMessage("Donation created successfully!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Donation info not found!"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("User profile not found!"), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") Long id) {

        Optional<Donation> donationOptional = donationRepository.findById(id);
        if(donationOptional.isPresent()) {
            Donation donation = donationOptional.get();
            donation.setEnabled(false);
            donationRepository.save(donation);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
