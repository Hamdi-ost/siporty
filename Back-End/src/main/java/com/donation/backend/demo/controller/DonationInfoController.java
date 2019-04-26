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

import java.util.*;

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
                dim.setSocialLink(donationInfo.getSocialLink());
                dim.setSolde(donationInfo.getSolde());

                List<DonationMessage> donationMessages = new ArrayList<>();
                List<Donation> donations = donationInfo.getDonations();
                donations.forEach(donation -> {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setDate(donation.getDate());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
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

        Optional<DonationInfo> _donationInfo = donationInfoRepository.findById(id);
        if(_donationInfo.isPresent()) {
            DonationInfo donationInfo = _donationInfo.get();

            DonationInfoMessage dim = new DonationInfoMessage();

            dim.setUrl(donationInfo.getUrl());
            dim.setSocialLink(donationInfo.getSocialLink());
            dim.setSolde(donationInfo.getSolde());

            List<DonationMessage> donationMessages = new ArrayList<>();
            List<Donation> donations = donationInfo.getDonations();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setDate(donation.getDate());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<DonationInfoMessage> getDonationStat(@PathVariable("id") long id) {

        Optional<DonationInfo> _donationInfo = donationInfoRepository.findById(id);
        if(_donationInfo.isPresent()) {
            DonationInfo donationInfo = _donationInfo.get();

            DonationInfoMessage dim = new DonationInfoMessage();

            dim.setUrl(donationInfo.getUrl());
            dim.setSocialLink(donationInfo.getSocialLink());
            dim.setSolde(donationInfo.getSolde());

            List<DonationMessage> donationMessages = new ArrayList<>();
            List<Donation> donations = donationInfo.getDonations();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setDate(donation.getDate());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
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

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> newDonationInfo(@RequestBody DonationInfoMessageUserId donationInfoMessage) {

        DonationInfo donationInfo = new DonationInfo();

        donationInfo.setSocialLink(donationInfoMessage.getSocialLink());
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
                donationInfo.setSocialLink(donationInfoMessage.getSocialLink());

                donationInfoRepository.save(donationInfo);

                return new ResponseEntity<>(donationInfoMessage, HttpStatus.OK);

                /*List<DonationMessage> donationMessages = donationInfoMessage.getDonationMessages();

                donationMessages.forEach(donationMessage -> {
                    float solde = donationInfo.getSolde() + donationMessage.getMontant();
                    donationInfo.setSolde(solde);

                    Donation donation = new Donation();
                    donation.setMontant(donationMessage.getMontant());
                    donation.setDonationInfo(donationInfo);
                    donation.setEnabled(true);

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    donation.setDate(dateFormat.format(date));
                });*/
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
