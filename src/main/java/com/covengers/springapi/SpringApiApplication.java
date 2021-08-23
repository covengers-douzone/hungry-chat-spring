package com.covengers.springapi;

import com.covengers.springapi.model.User;
import com.covengers.springapi.service.UserServiceNew;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SpringApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Testing 을 위한 코드
    // 이 코드는 main 이 실행된 이후에 실행된다.
//    @Bean
//    CommandLineRunner run(UserServiceNew userServiceNew){
//        Date date = new Date();
//        return args -> {
//            userServiceNew.saveUser(new User(null, "elsa@gmail.com", "elsa", "1234", "elsa", false, "backgroundImg", "profileImg", "ROLE_USER", "token", "01057289028", date, date));
//        };
//    }



}
