package com.covengers.springapi.api;


import com.covengers.springapi.model.Sms;
import com.covengers.springapi.model.User;
import com.covengers.springapi.repo.UserRepository;
import com.covengers.springapi.service.SmsService;
import com.covengers.springapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.io.PrintWriter;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsService smsService;

    @PostMapping("/api/join")
    public ResponseEntity<?> Join(@RequestBody User user){
        userService.join(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
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
    public ResponseEntity<?> Login(@RequestBody User user){
        System.out.println(user);
        User user2 = new User();
        user2 = userRepository.findByUsername(user.getUsername());
        System.out.println(user2);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
