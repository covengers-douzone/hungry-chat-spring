package com.covengers.springapi.service;

import com.covengers.springapi.model.User;
import com.covengers.springapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean join(User requestUser){
        String encPassword = bCryptPasswordEncoder.encode(requestUser.getPassword());

        if(userRepository.findByUsername(requestUser.getUsername()) == null) {
            User user = new User();
            user.setUsername(requestUser.getUsername()); //email
            user.setName(requestUser.getName()); //name
            user.setPassword(encPassword); // encoded password
            //user.setPhoneNumber(requestUser.getPhoneNumber());
            user.setIsDeleted(false); // false == 회원
            user.setBackgroundImageUrl("backgroundImage"); // dummy data
            user.setProfileImageUrl("profileImage"); //  dummy data
            user.setRole("ROLE_USER"); // default role == ROLE_USER
            user.setNickname(requestUser.getName()); // nickname -> default == name

            userRepository.save(user);
            return true;
        }
        return false;
    }
}
