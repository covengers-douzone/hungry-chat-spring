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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        }
        else{
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
            user.setIsDeleted(false); // false == ??????
            user.setBackgroundImageUrl("http://simpleicon.com/wp-content/uploads/account.png"); // dummy data
            user.setProfileImageUrl("http://simpleicon.com/wp-content/uploads/account.png"); //  dummy data
            user.setRole("ROLE_USER"); // default role == ROLE_USER
            user.setNickname(user.getName()); // nickname -> default == name
            user.setComments("covengers");
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
    public User getUserAndPassword(String username, String password) {
        log.info("Finding one user {}", username, password);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(username);

        if(bCryptPasswordEncoder.matches(password,user.getPassword())){
            return userRepository.findByUsernameAndPassword(username, user.getPassword());
        }
        return null;
    }

    @Override
    public Boolean pwUpdate(User data) {
        User user = userRepository.findByUsername(data.getUsername());
        String rawPassword = user.getPassword();
        System.out.println("rawPassword: " + rawPassword);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean userActivation(User user) {
        User userinfo = userRepository.findByUsername(user.getUsername());

        userinfo.setIsDeleted(false);
        userRepository.save(userinfo);
        return true;
    }

    @Override
    public void addTokenToUser(String username, String token) {
        log.info("Saving new token {} to the database", token);
        User addTokenUser = userRepository.findByUsername(username);
        addTokenUser.setToken("Bearer "+token);
        userRepository.save(addTokenUser);
    }

    @Override
    public String findByNameAndPhoneNumber(String name, String phonenumber) {
        User user = userRepository.findByNameAndPhoneNumber(name, phonenumber);
        return user.getUsername();
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Long getNo(String username) {
        User user = userRepository.findByUsername(username);
        return user.getNo();
    }

    @Override
    public User saveUnknownUser() {
        log.info("Saving new unknown User to the database");
        int randomPIN = (int)(Math.random()*9999);
//        int randomPIN = (int)(Math.random()*9000)+1000;
        System.out.println("Unknown----------------------" + randomPIN);

        User user = new User();
        user.setUsername("Unknown" + randomPIN);
        user.setName("Unknown" + randomPIN); //name
        user.setPassword(passwordEncoder.encode("Unknown"));
        user.setNickname(user.getName()); // nickname -> default == name
        user.setIsDeleted(false); // false == ??????
        user.setBackgroundImageUrl("http://simpleicon.com/wp-content/uploads/account.png"); // dummy data
        user.setProfileImageUrl("http://simpleicon.com/wp-content/uploads/account.png"); //  dummy data
        user.setRole("ROLE_UNKNOWN"); // default role == ROLE_USER
        user.setToken("token");
        user.setPhoneNumber("Unknown");
        user.setComments("covengers");
        userRepository.save(user);

        return userRepository.findByUsername(user.getUsername());
    }
}
