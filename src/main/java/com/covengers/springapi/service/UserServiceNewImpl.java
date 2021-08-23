package com.covengers.springapi.service;


import com.covengers.springapi.model.User;
import com.covengers.springapi.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class UserServiceNewImpl implements  UserServiceNew, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public User saveUser(User user) {
        log.info("Saving new user to the database");
        user.setName(user.getName()); //name
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPhoneNumber(user.getPhoneNumber());
        user.setIsDeleted(false); // false == 회원
        user.setBackgroundImageUrl("backgroundImage"); // dummy data
        user.setProfileImageUrl("profileImage"); //  dummy data
        user.setRole("ROLE_USER"); // default role == ROLE_USER
        user.setNickname(user.getName()); // nickname -> default == name
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user by username {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all of users");
        return userRepository.findAll();
    }

    @Override
    public void addTokenToUser(String username, String token) {
        User addTokenUser = userRepository.findByUsername(username);
        addTokenUser.setToken(token);
        userRepository.save(addTokenUser);
    }

    //    @Override
//    public void addRoleToUser(String username, String role) {
//        log.info("Saving new role {} to the database", role);
//        User user =userRepository.findByUsername(username);
//        user.setRole(role);
//    }


}
