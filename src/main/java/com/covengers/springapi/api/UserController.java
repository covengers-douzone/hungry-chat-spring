package com.covengers.springapi.api;


import com.covengers.springapi.model.User;
import com.covengers.springapi.service.SmsService;
import com.covengers.springapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

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
    @PostMapping(value = "/sendSms")
    public ResponseEntity<?> sendSms(HttpServletRequest request) throws Exception {
        Boolean result = smsService.sms(request);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("fail",HttpStatus.EXPECTATION_FAILED);
    }
}
