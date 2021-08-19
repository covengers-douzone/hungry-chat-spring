package com.covengers.springapi.controller;


import com.covengers.springapi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/api/join")
    public String Join(User user){
        return "success";
    }
}
