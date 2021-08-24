package com.covengers.springapi.service;


import com.covengers.springapi.model.User;
import com.covengers.springapi.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceNewImpl implements  UserServiceNew, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceNewImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found");
        } else{
            log.info("User found in the database:{}", username);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public void saveUser(User user) {
            log.info("Saving new user to the database");
            user.setName(user.getName()); //name
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPhoneNumber(user.getPhoneNumber());
            user.setIsDeleted(false); // false == 회원
            user.setBackgroundImageUrl("backgroundImage"); // dummy data
            user.setProfileImageUrl("profileImage"); //  dummy data
            user.setRole("ROLE_USER"); // default role == ROLE_USER
            user.setNickname(user.getName()); // nickname -> default == name
            userRepository.save(user);
    }
    @Override
    public List<User> getUsers() {
        log.info("Fetching all of users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching one user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void addTokenToUser(String username, String token) {
        log.info("Saving new token {} to the database", token);
        User addTokenUser = userRepository.findByUsername(username);
        System.out.println(addTokenUser);
        addTokenUser.setToken(token);
        userRepository.save(addTokenUser);
    }

    @Override
    public String findUsername(String name, String phonenumber) {
        User user = userRepository.findByNameAndPhoneNumber(name, phonenumber);
        return user.getUsername();
    }
}
