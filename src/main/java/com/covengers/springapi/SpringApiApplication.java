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
//
//            userServiceNew.saveUser(new User(null, "cap@gmail.com", "Ketchup America", "1111", "Ketchup America", false, "https://t1.daumcdn.net/news/202105/21/pnn/20210521171800076keuh.jpg", "https://t1.daumcdn.net/news/202105/21/pnn/20210521171800076keuh.jpg", "ROLE_USER", "token", "01022222222", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "dr@gmail.com", "Dr.Stranger", "1111", "Dr.Stranger", false, "https://sm.ign.com/t/ign_kr/screenshot/default/dagseu_g92p.2560.jpg", "https://sm.ign.com/t/ign_kr/screenshot/default/dagseu_g92p.2560.jpg", "ROLE_USER", "token", "01033333333", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "hulk@gmail.com", "Green monster", "1111", "Green monster", false, "https://img.huffingtonpost.com/asset/5d7f3e61230000580554f916.jpeg?cache=vsNYzmVNEQ&ops=scalefit_630_noupscale", "https://img.huffingtonpost.com/asset/5d7f3e61230000580554f916.jpeg?cache=vsNYzmVNEQ&ops=scalefit_630_noupscale", "ROLE_USER", "token", "01011111111", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "thor@gmail.com", "Static electricity", "1111", "Static electricity", false, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXc6RCrbXnX0I1pJtWuCFkxmInSi1ViqlHzg&usqp=CAU","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXc6RCrbXnX0I1pJtWuCFkxmInSi1ViqlHzg&usqp=CAU", "ROLE_USER", "token", "01055555555", date, date, "covengers"));
//
//            userServiceNew.saveUser(new User(null, "yujin@gmail.com", "배유진", "1111", "배유진", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01066666666", date, date, "covengers"));
//            //userServiceNew.saveUser(new User(null, "daeheon@gmail.com", "박대헌", "1111", "박대헌", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01077777777", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "jaehyeon@gmail.com", "손재현", "1111", "손재현", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01088888888", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "woosung@gmail.com", "강우성", "1111", "강우성", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01099999999", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "camera@gmail.com", "진사랑", "1111", "진사랑", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01000000000", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "covengers@gmail.com", "covengers", "1111", "covengers", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01010101010", date, date, "covengers"));
//            userServiceNew.saveUser(new User(null, "yummy@gmail.com", "우해진", "1111", "우해진", false, "http://simpleicon.com/wp-content/uploads/account.png", "http://simpleicon.com/wp-content/uploads/account.png", "ROLE_USER", "token", "01034343434", date, date, "covengers"));
//        };
//    }
}
