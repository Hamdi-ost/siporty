package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.*;
import com.donation.backend.demo.message.response.ResponseMessage;
import com.donation.backend.demo.model.Donation;
import com.donation.backend.demo.model.DonationInfo;
import com.donation.backend.demo.model.Role;
import com.donation.backend.demo.model.User;
import com.donation.backend.demo.repository.DonationInfoRepository;
import com.donation.backend.demo.repository.DonationRepository;
import com.donation.backend.demo.repository.UserRepository;
import com.donation.backend.demo.security.services.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/donation-details")
public class DonationInfoController {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonationInfoRepository donationInfoRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DonationInfoMessage>> allDonationInfos() {

        try {
            List<DonationInfo> donationInfos = new ArrayList<>(donationInfoRepository.findAll());
            if(donationInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<DonationInfoMessage> donationInfoMessages = new ArrayList<>();
            donationInfos.forEach(donationInfo -> {

                DonationInfoMessage dim = new DonationInfoMessage();

                dim.setTitle(donationInfo.getTitle());
                dim.setMessage(donationInfo.getMessage());
                dim.setUrl(donationInfo.getUrl());
                dim.setImage(donationInfo.getImage());
                dim.setSolde(donationInfo.getSolde());

                List<DonationMessage> donationMessages = new ArrayList<>();
                List<Donation> donations = donationInfo.getDonations();
                donations.forEach(donation -> {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setDate(donation.getDate());
                    donationMessages.add(dm);
                });
                dim.setDonationMessages(donationMessages);

                User user = donationInfo.getUser();
                UserInfo _user = new UserInfo();
                _user.setId(user.getId());
                _user.setFirstname(user.getFirstName());
                _user.setLastname(user.getLastName());
                _user.setUsername(user.getUsername());
                _user.setBanque(user.getBanque());
                _user.setAgence(user.getAgence());
                _user.setCcb(user.getCcb());
                _user.setEmail(user.getEmail());
                _user.setEnabled(user.isEnabled());

                List<String> _roles = new ArrayList<>();
                Set<Role> roles = user.getRoles();
                roles.forEach(role -> {
                    _roles.add(role.getName().name());
                });
                _user.setRoles(_roles);

                dim.setUserInfo(_user);

                donationInfoMessages.add(dim);
            });

            return new ResponseEntity<>(donationInfoMessages, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<DonationInfoMessage> getDonationById(@PathVariable("id") long id) {

        Optional<DonationInfo> _donationInfo = donationInfoRepository.findById(id);
        if(_donationInfo.isPresent()) {
            DonationInfo donationInfo = _donationInfo.get();

            DonationInfoMessage dim = new DonationInfoMessage();

            dim.setTitle(donationInfo.getTitle());
            dim.setMessage(donationInfo.getMessage());
            dim.setUrl(donationInfo.getUrl());
            dim.setImage(donationInfo.getImage());
            dim.setSolde(donationInfo.getSolde());

            List<DonationMessage> donationMessages = new ArrayList<>();
            List<Donation> donations = donationInfo.getDonations();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });
            dim.setDonationMessages(donationMessages);

            User user = donationInfo.getUser();
            UserInfo _user = new UserInfo();
            _user.setId(user.getId());
            _user.setFirstname(user.getFirstName());
            _user.setLastname(user.getLastName());
            _user.setUsername(user.getUsername());
            _user.setBanque(user.getBanque());
            _user.setAgence(user.getAgence());
            _user.setCcb(user.getCcb());
            _user.setEmail(user.getEmail());
            _user.setEnabled(user.isEnabled());

            List<String> _roles = new ArrayList<>();
            Set<Role> roles = user.getRoles();
            roles.forEach(role -> {
                _roles.add(role.getName().name());
            });
            _user.setRoles(_roles);

            dim.setUserInfo(_user);

            return new ResponseEntity<>(dim, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> newMessage(@RequestBody DonationInfoMessageUserId donationInfoMessage) {

        DonationInfo donationInfo = new DonationInfo();

        donationInfo.setTitle(donationInfoMessage.getTitle());
        donationInfo.setMessage(donationInfoMessage.getMessage());
        donationInfo.setImage(donationInfoMessage.getImage());
        donationInfo.setDonations(new ArrayList<>());
        donationInfo.setEnabled(true);

        Optional<User> _user = userRepository.findById(donationInfoMessage.getId());
        if(_user.isPresent())
        {
            User user = _user.get();
            donationInfo.setUser(user);

            RandomString randomString = new RandomString();
            String url = randomString.nextString();
            Optional<DonationInfo> di = donationInfoRepository.findDonationInfoByUrl(url);
            if (di.isPresent()) {
                return new ResponseEntity<>(new ResponseMessage("Fail -> Donation info url is already taken!"),
                        HttpStatus.BAD_REQUEST);
            }
            donationInfo.setUrl(url);

            donationInfoRepository.save(donationInfo);
            return new ResponseEntity<>(new ResponseMessage("DonationInfo created successfully!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("User profile not found!"), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") Long id) {

        Optional<DonationInfo> donationInfoOptional = donationInfoRepository.findById(id);
        if(donationInfoOptional.isPresent()) {
            DonationInfo donationInfo = donationInfoOptional.get();
            donationInfo.setEnabled(false);
            donationInfoRepository.save(donationInfo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
