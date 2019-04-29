package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.*;
import com.donation.backend.demo.message.response.ResponseMessage;
import com.donation.backend.demo.message.response.StatAdmin;
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

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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

                dim.setUrl(donationInfo.getUrl());
                dim.setSolde(donationInfo.getSolde());

                List<DonationMessage> donationMessages = new ArrayList<>();
                List<Donation> donations = donationInfo.getDonations();
                donations.forEach(donation -> {
                    if(donation.isEnabled()) {
                        DonationMessage dm = new DonationMessage();
                        dm.setMontant(donation.getMontant());
                        dm.setDate(donation.getDate());
                        dm.setName(donation.getName());
                        dm.setMessage(donation.getMessage());
                        donationMessages.add(dm);
                    }
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
                _user.setAccountName(user.getAccountName());
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
    public ResponseEntity<DonationInfoMessage> getDonationInfoById(@PathVariable("id") long id) {

        Optional<DonationInfo> _donationInfo = donationInfoRepository.findByUserId(id);
        if(_donationInfo.isPresent()) {
            DonationInfo donationInfo = _donationInfo.get();

            DonationInfoMessage dim = new DonationInfoMessage();

            dim.setUrl(donationInfo.getUrl());
            dim.setSolde(donationInfo.getSolde());

            List<DonationMessage> donationMessages = new ArrayList<>();
            List<Donation> donations = donationInfo.getDonations();
            donations.forEach(donation -> {
                if(donation.isEnabled()) {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setDate(donation.getDate());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
                    donationMessages.add(dm);
                }
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
            _user.setAccountName(user.getAccountName());
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

    @GetMapping("/auth/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<DonationInfoMessage> getDonationInfoById(@PathVariable("username") String username) {

        Optional<User> user_ = userRepository.findByUsername(username);
        Optional<DonationInfo> _donationInfo = donationInfoRepository.findByUserId(user_.get().getId());
        if(_donationInfo.isPresent()) {
            DonationInfo donationInfo = _donationInfo.get();

            DonationInfoMessage dim = new DonationInfoMessage();

            dim.setUrl(donationInfo.getUrl());
            dim.setSolde(donationInfo.getSolde());

            List<DonationMessage> donationMessages = new ArrayList<>();
            List<Donation> donations = donationInfo.getDonations();
            donations.forEach(donation -> {
                if(donation.isEnabled()) {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setDate(donation.getDate());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
                    donationMessages.add(dm);
                }
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
            _user.setAccountName(user.getAccountName());
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

    @GetMapping("/stats/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StatAdmin> getStats() {

        try {
            StatAdmin statAdmin = new StatAdmin();

            List<DonationInfo> donationInfos = new ArrayList<>(donationInfoRepository.findAll());
            if(donationInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //List<DonationInfoMessage> donationInfoMessages = new ArrayList<>();
            donationInfos.forEach(donationInfo -> {
                float newTotal = statAdmin.getTotalMoney() + donationInfo.getSolde();
                statAdmin.setTotalMoney(newTotal);

            });

            long totalUsers = userRepository.countByRoles("ROLE_USER");
            statAdmin.setTotalUsers(totalUsers);

            //List<Donation> donations = donationRepository.getDonationsPerMonth(4,2019);
            List<Donation> donations = new ArrayList<>(donationRepository.findAll());
            List<DonationMessage> donationMessages = new ArrayList<>();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });
            statAdmin.setDonations(donationMessages);

            List<Donation> donations1 = donationRepository.getTopDonors();
            List<DonationMessage> donationMessages1 = new ArrayList<>();
            donations1.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages1.add(dm);
            });
            statAdmin.setTopTenDonors(donationMessages1);

            return new ResponseEntity<>(statAdmin, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/{id}/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StatAdmin> getStatsById(@PathVariable("id") long id) {

        try {
            StatAdmin statAdmin = new StatAdmin();

            Optional<DonationInfo> _donationInfo = donationInfoRepository.findByUserId(id);
            if(!_donationInfo.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //List<DonationInfoMessage> donationInfoMessages = new ArrayList<>();
            DonationInfo donationInfo = _donationInfo.get();
            float newTotal = statAdmin.getTotalMoney() + donationInfo.getSolde();
            statAdmin.setTotalMoney(newTotal);

            //List<Donation> donations = donationRepository.getDonationsPerMonth(4,2019);
            List<Donation> donations = donationInfo.getDonations();
            List<DonationMessage> donationMessages = new ArrayList<>();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });
            statAdmin.setDonations(donationMessages);

            List<Donation> donations1 = donationRepository.getTopDonors();
            List<DonationMessage> donationMessages1 = new ArrayList<>();
            donations1.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages1.add(dm);
            });
            statAdmin.setTopTenDonors(donationMessages1);

            return new ResponseEntity<>(statAdmin, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/auth/")
    public ResponseEntity<?> newDonationInfo(@RequestBody DonationInfoMessageUserId donationInfoMessage) {

        DonationInfo donationInfo = new DonationInfo();

        donationInfo.setSolde(0);
        donationInfo.setDonations(new ArrayList<>());
        donationInfo.setEnabled(true);

        Optional<User> _user = userRepository.findById(donationInfoMessage.getId());
        if(_user.isPresent())
        {
            User user = _user.get();
            Optional<DonationInfo> _donationInfo = donationInfoRepository.findDonationInfoByUser(user);
            if (_donationInfo.isPresent()) {
                return new ResponseEntity<>(new ResponseMessage("Fail -> User already has a donation page!"),
                        HttpStatus.BAD_REQUEST);
            }
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

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> updateDonationInfo(@RequestBody DonationInfoMessageUserId donationInfoMessage) {

        Optional<User> _user = userRepository.findById(donationInfoMessage.getId());
        if(_user.isPresent()) {
            User user = _user.get();
            Optional<DonationInfo> _donationInfo = donationInfoRepository.findDonationInfoByUser(user);
            if(_donationInfo.isPresent()) {

                DonationInfo donationInfo = _donationInfo.get();
                donationInfoRepository.save(donationInfo);
                return new ResponseEntity<>(donationInfoMessage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Donation Info not found!"), HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(new ResponseMessage("User profile not found!"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") Long id) {

        Optional<User> _user = userRepository.findById(id);
        Optional<DonationInfo> donationInfoOptional = donationInfoRepository.findDonationInfoByUser(_user.get());
        if(donationInfoOptional.isPresent()) {
            DonationInfo donationInfo = donationInfoOptional.get();
            donationInfo.setEnabled(false);
            donationInfoRepository.save(donationInfo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/check/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> checkDonationsMessage(@PathVariable("id") Long id) {

        Optional<User> _user = userRepository.findById(id);
        if(_user.isPresent()) {
            User user = _user.get();
            Optional<DonationInfo> donationInfoOptional = donationInfoRepository.findDonationInfoByUser(user);
            if(donationInfoOptional.isPresent()) {
                DonationInfo donationInfo = donationInfoOptional.get();
                List<Donation> donations = donationInfo.getDonations();
                donations.forEach(donation -> {
                    donation.setEnabled(false);
                    donationRepository.save(donation);
                });
                donationInfo.setSolde(0);
                donationInfoRepository.save(donationInfo);

                return new ResponseEntity<>(new ResponseMessage("Donation Info updated!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Donation Info not found!"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("User not found!"), HttpStatus.NOT_FOUND);
        }
    }
}
