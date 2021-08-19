package com.covengers.springapi.api;


import com.covengers.springapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

//    @Autowired
//    private UserRepository userRepository;

    @PostMapping("/api/join")
    public String Join(@RequestBody User user){
        System.out.println(user);
        return "success";
    }
}
