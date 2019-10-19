package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.UserInfo;
import com.donation.backend.demo.security.services.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.donation.backend.demo.message.request.LoginForm;
import com.donation.backend.demo.message.request.SignUpForm;
import com.donation.backend.demo.message.response.JwtResponse;
import com.donation.backend.demo.message.response.ResponseMessage;
import com.donation.backend.demo.model.Role;
import com.donation.backend.demo.model.RoleName;
import com.donation.backend.demo.model.User;
import com.donation.backend.demo.repository.RoleRepository;
import com.donation.backend.demo.security.jwt.JwtProvider;
import com.donation.backend.demo.repository.UserRepository;

import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/users")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User userDb = userRepository.findByUsername(loginRequest.getUsername()).get();
        List<String> roles = new ArrayList<>();
        Set<Role> _roles = userDb.getRoles();
        _roles.forEach(role -> {
            roles.add(role.getName().name());
        });
        UserInfo user = new UserInfo(userDb.getId(), userDb.getFirstName(), userDb.getLastName(), userDb.getBanque(),
                userDb.getAgence(), userDb.getCcb(), userDb.getAccountName(), userDb.getSocialLink(), userDb.getUsername(), userDb.getEmail(),
                roles, userDb.isEnabled(), userDb.getPhone());

        if(user.isEnabled()) {
            return ResponseEntity.ok(new JwtResponse(jwt, user, userDetails.getAuthorities()));
        }
        return new ResponseEntity<>(new ResponseMessage("Fail -> User is banned!"), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/authenticate/admin")
    public ResponseEntity<?> authenticateAdmin(@RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User userDb = userRepository.findByUsername(loginRequest.getUsername()).get();
        List<String> roles = new ArrayList<>();
        Set<Role> _roles = userDb.getRoles();
        _roles.forEach(role -> {
            roles.add(role.getName().name());
        });
        UserInfo user = new UserInfo(userDb.getId(), userDb.getFirstName(), userDb.getLastName(), userDb.getBanque(),
                userDb.getAgence(), userDb.getCcb(), userDb.getAccountName(), userDb.getSocialLink(), userDb.getUsername(), userDb.getEmail(),
                roles, userDb.isEnabled(), userDb.getPhone());

        if(user.isEnabled() && user.getRoles().contains("ROLE_ADMIN")) {
            return ResponseEntity.ok(new JwtResponse(jwt, user, userDetails.getAuthorities()));
        }
        return new ResponseEntity<>(new ResponseMessage("Fail -> User is banned!"), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();
        user.setFirstName(signUpRequest.getFirstname());
        user.setLastName(signUpRequest.getLastname());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setEnabled(false);

        Set<Role> roles = new HashSet<>();
        Role roleDb = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
        roles.add(roleDb);

        user.setRoles(roles);
        userRepository.save(user);

        List<String> _roles = new ArrayList<>();
        roles.forEach(role -> {
            _roles.add(role.getName().name());
        });
        UserInfo userInfo = new UserInfo(user.getId(), user.getFirstName(), user.getLastName(), user.getBanque(),
                user.getAgence(), user.getCcb(), user.getAccountName(), user.getSocialLink(), user.getUsername(), user.getEmail()
        ,_roles, user.isEnabled(), user.getPhone());

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/register/validate/{id}")
    public ResponseEntity<?> validateRegistrationUser(@PathVariable("id") long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            /*UserInfo _user = new UserInfo();
            _user.setId(user.getId());
            _user.setFirstname(user.getFirstName());
            _user.setLastname(user.getLastName());
            _user.setUsername(user.getUsername());
            _user.setBanque(user.getBanque());
            _user.setAgence(user.getAgence());
            _user.setCcb(user.getCcb());
            _user.setPhone(user.getPhone());
            _user.setAccountName(user.getAccountName());
            _user.setSocialLink(user.getSocialLink());
            _user.setEmail(user.getEmail());
            _user.setEnabled(user.isEnabled());

            List<String> _roles = new ArrayList<>();
            Set<Role> roles = user.getRoles();
            roles.forEach(role -> {
                _roles.add(role.getName().name());
            });

            _user.setRoles(_roles);*/

            user.setEnabled(true);
            userRepository.save(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/register/validate-email/{id}")
    public ResponseEntity<?> sendValidateRegistrationEmail(@PathVariable("id") long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setFrom("ibcomprodmail@gmail.com");
            mailMessage.setSubject("Signup Successful !");
            mailMessage.setText( "Hello "+user.getFirstName()+",\n\nYour username : \nUsername : "+user.getUsername()+"\n"+"\n" +"Cordialement,\n" +
                    "Siporty.\n");

            javaMailSender.send(mailMessage);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/reset-password/{email}")
    public ResponseEntity<?> sendResetPasswordEmail(@PathVariable("email") String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            RandomString randomString = new RandomString();
            String newPassword = randomString.nextString();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setFrom("ibcomprodmail@gmail.com");
            mailMessage.setSubject("Reset password Siporty!");
            mailMessage.setText( "Hello "+user.getFirstName()+",\n\nVos identifiants : \nUsername : "+user.getUsername()+"\nPassword : "+ newPassword +" \n"+"\n" +"Cordialement,\n" +
                    "Siporty.\n");

            javaMailSender.send(mailMessage);
            user.setPassword(encoder.encode(newPassword));

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
