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
//
//     Testing 을 위한 코드
//     이 코드는 main 이 실행된 이후에 실행된다.


//    @Bean
//    CommandLineRunner run(UserServiceNew userServiceNew){
//        Date date = new Date();
//        return args -> {
//            userServiceNew.saveUser(new User(null, "unknown", "unknown", "1111", "unknown", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "unknown", date, date, "unknown"));
//            userServiceNew.saveUser(new User(null, "Admin", "Admin", "covengers", "Admin", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_ADMIN", "token", "unknown", date, date, "Admin"));
//            userServiceNew.saveUser(new User(null, "MirabelleTow@gmail.com", "Mirabelle Tow", "1111", "Mirabelle Tow", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01057289028", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "TownsendSeary@gmail.com", "Townsend Seary", "1111", "Townsend Seary", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01074980731", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "elsa@gmail.com", "elsa", "1111", "elsa", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01059284727", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "dummy@gmail.com", "dummy", "1111", "dummy", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01028346735", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "coffee@gmail.com", "coffee", "1111", "coffee", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01098725827", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "covengers@gmail.com", "covengers", "1111", "covengers", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01037362957", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "yummy@gmail.com", "yummy", "1111", "yummy", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01037368946", date, date, "covengers"));
//
//
//        };
//    }
}


// userServiceNew.saveUser(new User(null, "cap@gmail.com", "Ketchup America", "1111", "Ketchup America", false, "https://t1.daumcdn.net/news/202105/21/pnn/20210521171800076keuh.jpg", "https://t1.daumcdn.net/news/202105/21/pnn/20210521171800076keuh.jpg", "ROLE_USER", "token", "01077777777", date, date, "covengers"));
// userServiceNew.saveUser(new User(null, "dr@gmail.com", "Dr.Stranger", "1111", "Dr.Stranger", false, "https://sm.ign.com/t/ign_kr/screenshot/default/dagseu_g92p.2560.jpg", "https://sm.ign.com/t/ign_kr/screenshot/default/dagseu_g92p.2560.jpg", "ROLE_USER", "token", "01077777777", date, date, "covengers"));
// userServiceNew.saveUser(new User(null, "hulk@gmail.com", "Green monster", "1111", "Green monster", false, "https://img.huffingtonpost.com/asset/5d7f3e61230000580554f916.jpeg?cache=vsNYzmVNEQ&ops=scalefit_630_noupscale", "https://img.huffingtonpost.com/asset/5d7f3e61230000580554f916.jpeg?cache=vsNYzmVNEQ&ops=scalefit_630_noupscale", "ROLE_USER", "token", "01077777777", date, date, "covengers"));
// userServiceNew.saveUser(new User(null, "thor@gmail.com", "Static electricity", "1111", "Static electricity", false, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXc6RCrbXnX0I1pJtWuCFkxmInSi1ViqlHzg&usqp=CAU","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXc6RCrbXnX0I1pJtWuCFkxmInSi1ViqlHzg&usqp=CAU", "ROLE_USER", "token", "01077777777", date, date, "covengers"));
