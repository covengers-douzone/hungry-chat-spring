//package com.covengers.springapi.config;
//
//import com.covengers.springapi.Constant;
//import com.covengers.springapi.jwt.JwtService;
//import com.covengers.springapi.jwt.JwtServiceImpl;
//import com.covengers.springapi.model.User;
//import com.covengers.springapi.repo.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.Optional;
//
//@Slf4j
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private HttpServletResponse httpServletResponse;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // param 으로 들어오는 username 을 통해 DB를 조회,(by UserRepository)
//        // password 검증 등은 SpringSecurity 가 대행으로 해준다.
//        Optional<User> op = userRepository.findByUsername(username);
//        if(!op.isPresent()){
//            throw new UsernameNotFoundException("사용자 없음");
//        }
//        // 토큰 발생
//        String token = jwtService.createToken(username);
//
//        httpServletResponse.setHeader(Constant.AUTH_HEADER, token);
//        System.out.println(token);
//        User user = op.get();
//        return new SecurityUser(user);
//    }
//
//}
