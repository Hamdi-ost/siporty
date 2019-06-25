package com.donation.backend.demo.controller;

import com.donation.backend.demo.message.request.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                roles, userDb.isEnabled(), userDb.getImage());

        if(user.isEnabled()) {
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
        user.setEnabled(true);

        //Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


        /*strRoles.forEach(role -> {
            Role roleDb = roleRepository.findByName(RoleName.valueOf(role))
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
            roles.add(roleDb);
        });*/

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
        ,_roles, user.isEnabled(), user.getImage());

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
