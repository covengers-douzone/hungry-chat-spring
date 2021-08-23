package com.covengers.springapi.api;


import com.covengers.springapi.config.JWTTokenHelper;
import com.covengers.springapi.model.Sms;
import com.covengers.springapi.model.User;
import com.covengers.springapi.repo.UserRepository;
import com.covengers.springapi.request.AuthenticationRequest;
import com.covengers.springapi.response.LoginResponse;
import com.covengers.springapi.response.UserInfo;
import com.covengers.springapi.service.SmsService;
import com.covengers.springapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;


@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JWTTokenHelper jWTTokenHelper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/api/join")
    public ResponseEntity<?> Join(@RequestBody User user){
        boolean result = userService.join(user);
        if(result) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @ResponseBody
    @PostMapping(value = "/api/sendSms")
    public ResponseEntity<?> sendSms(@RequestBody Sms sms) throws Exception {
        Boolean result = smsService.sms(sms);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("fail",HttpStatus.EXPECTATION_FAILED);
    }

//---------------------------------------------------

    @PostMapping("/api/login")
    public ResponseEntity<?> Login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User)authentication.getPrincipal();
        String jwtToken = jWTTokenHelper.generateToken(user.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UserInfo userinfo = new UserInfo();
        userinfo.setName(userObj.getName());
        userinfo.setRoles(userObj.getAuthorities().toArray());
        return ResponseEntity.ok(userinfo);
    }
}
