package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.*;
import com.donation.backend.demo.message.response.IncomeBetweenDates;
import com.donation.backend.demo.message.response.MonthIncome;
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
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
                    // if(donation.isEnabled()) {
                        DonationMessage dm = new DonationMessage();
                        dm.setMontant(donation.getMontant());
                        dm.setDate(donation.getDate());
                        dm.setName(donation.getName());
                        dm.setMessage(donation.getMessage());
                        donationMessages.add(dm);
                   // }
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
                //if(donation.isEnabled()) {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setDate(donation.getDate());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
                    donationMessages.add(dm);
                //}
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
                //if(donation.isEnabled()) {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setDate(donation.getDate());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
                    donationMessages.add(dm);
                //}
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

    @PostMapping("/income/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<IncomeBetweenDates> getIncomePerWeek(@PathVariable("id") long id, @RequestBody IncomeDate incomeDate) {

        try {
            IncomeBetweenDates incomeBetweenDates = new IncomeBetweenDates();
            Optional<DonationInfo> _donationInfo = donationInfoRepository.findByUserId(id);
            if(!_donationInfo.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            DonationInfo donationInfo = _donationInfo.get();
            incomeBetweenDates.setIncome(0);

            List<Donation> donations = donationRepository.getDonationsPerWeek(incomeDate.getDate(), incomeDate.getDate2(), donationInfo.getId());
            List<DonationMessage> donationMessages = new ArrayList<>();
            donations.forEach(donation -> {

                float income = incomeBetweenDates.getIncome() + donation.getMontant();
                incomeBetweenDates.setIncome(income);

                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });
            incomeBetweenDates.setDonationMessages(donationMessages);

            return new ResponseEntity<>(incomeBetweenDates, HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/income-year/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<IncomeYear> getIncomePerYear(@PathVariable("id") long id) {

        try {
            IncomeYear incomeYear = new IncomeYear();
            Optional<DonationInfo> _donationInfo = donationInfoRepository.findByUserId(id);
            if(!_donationInfo.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            DonationInfo donationInfo = _donationInfo.get();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            String nextMonthFirstDay = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            String nextMonthLastDay = dateFormat.format(calendar.getTime());
            //System.out.println(nextMonthLastDay);

            MonthIncome monthIncome = new MonthIncome();
            String month = new SimpleDateFormat("MMMM").format(calendar.getTime());
            monthIncome.setMonth(month);
            monthIncome.setIncome(0);
            List<Donation> donations = donationRepository.getDonationsPerWeek(nextMonthFirstDay, nextMonthLastDay, donationInfo.getId());
            donations.forEach(donation -> {
                float income = monthIncome.getIncome() + donation.getMontant();
                monthIncome.setIncome(income);
            });
            incomeYear.addMonthIncome(monthIncome);

            for(int i=0; i<11; i++) {
                calendar.add(Calendar.MONTH, 1);
                incomeYear.addMonthIncome(setData(calendar, dateFormat, donationInfo));
            }

            return new ResponseEntity<>(incomeYear, HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public MonthIncome setData(Calendar calendar, SimpleDateFormat dateFormat, DonationInfo donationInfo) {
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String nextMonthFirstDay = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String nextMonthLastDay = dateFormat.format(calendar.getTime());
        //System.out.println(nextMonthFirstDay);

        MonthIncome monthIncome = new MonthIncome();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());
        monthIncome.setMonth(month);
        monthIncome.setIncome(0);
        List<Donation> donations = donationRepository.getDonationsPerWeek(nextMonthFirstDay, nextMonthLastDay, donationInfo.getId());
        donations.forEach(donation -> {
            float income = monthIncome.getIncome() + donation.getMontant();
            monthIncome.setIncome(income);
        });

        return monthIncome;
    }

    @PostMapping("/stats/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StatAdmin> getStats(@RequestBody StatInfo statInfo) {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal1.clear(Calendar.MINUTE);
            cal1.clear(Calendar.SECOND);
            cal1.clear(Calendar.MILLISECOND);
            cal1.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek());
            String currentDate = dateFormat.format(cal1.getTime());
            System.out.println(dateFormat.format(cal1.getTime()));

            cal1.add(Calendar.DATE, +7);
            String nextWeekDate = dateFormat.format(cal1.getTime());
            statInfo.setDate2(nextWeekDate);
            System.out.println(dateFormat.format(cal1.getTime()));

            String[] dates = currentDate.split("/");
            String[] dates1 = nextWeekDate.split("/");

            statInfo.setDay1(dates[0]);
            statInfo.setDay2(dates1[0]);

            statInfo.setMonth(dates[1]);
            statInfo.setYear(dates[2]);
            System.out.println(statInfo.getDay1()+" | "+statInfo.getDay2()+" | "+statInfo.getMonth()+" | "+statInfo.getYear());

            StatAdmin statAdmin = new StatAdmin();

            List<DonationInfo> donationInfos = new ArrayList<>(donationInfoRepository.findAll());
            if(donationInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            donationInfos.forEach(donationInfo -> {
                float newTotal = statAdmin.getAmountStillNotPayed() + donationInfo.getSolde();
                statAdmin.setAmountStillNotPayed(newTotal);

                long totalDonors = donationRepository.countAllByDonationInfoId(donationInfo.getId());
                statAdmin.setTotalDonors(totalDonors);

                List<Donation> donations = donationRepository.getDonationsPerMonthAndDonationInfoId(statInfo.getMonth(),statInfo.getYear(), donationInfo.getId());
                donations.forEach(donation -> {
                    float oldIncome = statAdmin.getIncomeThisMonth();
                    statAdmin.setIncomeThisMonth(donation.getMontant() + oldIncome);
                });

                List<Donation> donations1 = donationRepository.getDonationsPerWeek(statInfo.getDay1(), statInfo.getDay2(), donationInfo.getId());
                donations1.forEach(donation -> {
                    //System.out.println(donation.getName());
                    float oldIncome = statAdmin.getIncomeThisWeek();
                    statAdmin.setIncomeThisWeek(donation.getMontant() + oldIncome);
                });

                List<Donation> donations2 = donationRepository.getTopDonorsPerMonth(statInfo.getMonth(),statInfo.getYear(), donationInfo.getId());
                List<DonationMessage> donationMessages1 = new ArrayList<>();
                donations2.forEach(donation -> {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
                    dm.setDate(donation.getDate());
                    donationMessages1.add(dm);
                });
                statAdmin.setTopTenDonorsMonth(donationMessages1);

                List<Donation> donations3 = donationRepository.getTopDonorsPerWeek(statInfo.getDay1(), statInfo.getDay2(), donationInfo.getId());
                List<DonationMessage> donationMessages2 = new ArrayList<>();
                donations3.forEach(donation -> {
                    DonationMessage dm = new DonationMessage();
                    dm.setMontant(donation.getMontant());
                    dm.setName(donation.getName());
                    dm.setMessage(donation.getMessage());
                    dm.setDate(donation.getDate());
                    donationMessages2.add(dm);
                });
                statAdmin.setTopTenDonorsWeek(donationMessages2);
            });

            long totalUsers = userRepository.countByRoles("ROLE_USER");
            statAdmin.setTotalUsers(totalUsers);

            /*List<Donation> donations = new ArrayList<>(donationRepository.findAll());
            List<DonationMessage> donationMessages = new ArrayList<>();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });
            statAdmin.setDonations(donationMessages);*/

            return new ResponseEntity<>(statAdmin, HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/stats/{id}/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<StatAdmin> getStatsById(@PathVariable("id") long id, @RequestBody StatInfo statInfo) {

        try {
            StatAdmin statAdmin = new StatAdmin();

            Optional<DonationInfo> _donationInfo = donationInfoRepository.findByUserId(id);
            if(!_donationInfo.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            DonationInfo donationInfo = _donationInfo.get();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal1.clear(Calendar.MINUTE);
            cal1.clear(Calendar.SECOND);
            cal1.clear(Calendar.MILLISECOND);
            cal1.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek());
            String currentDate = dateFormat.format(cal1.getTime());
            System.out.println(dateFormat.format(cal1.getTime()));

            cal1.add(Calendar.DATE, +7);
            String nextWeekDate = dateFormat.format(cal1.getTime());
            statInfo.setDate2(nextWeekDate);
            System.out.println(dateFormat.format(cal1.getTime()));

            String[] dates = currentDate.split("/");
            String[] dates1 = nextWeekDate.split("/");

            statInfo.setDay1(dates[0]);
            statInfo.setDay2(dates1[0]);

            statInfo.setMonth(dates[1]);
            statInfo.setYear(dates[2]);
            //System.out.println(statInfo.getDay1()+" | "+statInfo.getDay2()+" | "+statInfo.getMonth()+" | "+statInfo.getYear());

            float newTotal = statAdmin.getAmountStillNotPayed() + donationInfo.getSolde();
            statAdmin.setAmountStillNotPayed(newTotal);

            long totalDonors = donationRepository.countAllByDonationInfoId(donationInfo.getId());
            statAdmin.setTotalDonors(totalDonors);

            List<Donation> donations = donationRepository.getDonationsPerMonthAndDonationInfoId(statInfo.getMonth(),statInfo.getYear(), donationInfo.getId());
            donations.forEach(donation -> {
                float oldIncome = statAdmin.getIncomeThisMonth();
                statAdmin.setIncomeThisMonth(donation.getMontant() + oldIncome);
            });

            List<Donation> donations1 = donationRepository.getDonationsPerWeek(statInfo.getDay1(), statInfo.getDay2(), donationInfo.getId());
            donations1.forEach(donation -> {
                //System.out.println(donation.getName());
                float oldIncome = statAdmin.getIncomeThisWeek();
                statAdmin.setIncomeThisWeek(donation.getMontant() + oldIncome);
            });

            List<Donation> donations2 = donationRepository.getTopDonorsPerMonth(statInfo.getMonth(),statInfo.getYear(), donationInfo.getId());
            List<DonationMessage> donationMessages1 = new ArrayList<>();
            donations2.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages1.add(dm);
            });
            statAdmin.setTopTenDonorsMonth(donationMessages1);

            List<Donation> donations3 = donationRepository.getTopDonorsPerWeek(statInfo.getDay1(), statInfo.getDay2(), donationInfo.getId());
            List<DonationMessage> donationMessages2 = new ArrayList<>();
            donations3.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages2.add(dm);
            });
            statAdmin.setTopTenDonorsWeek(donationMessages2);

            long totalUsers = userRepository.countByRoles("ROLE_USER");
            statAdmin.setTotalUsers(totalUsers);

            /*List<Donation> donations = new ArrayList<>(donationRepository.findAll());
            List<DonationMessage> donationMessages = new ArrayList<>();
            donations.forEach(donation -> {
                DonationMessage dm = new DonationMessage();
                dm.setMontant(donation.getMontant());
                dm.setName(donation.getName());
                dm.setMessage(donation.getMessage());
                dm.setDate(donation.getDate());
                donationMessages.add(dm);
            });
            statAdmin.setDonations(donationMessages);*/

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
