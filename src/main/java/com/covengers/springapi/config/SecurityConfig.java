//package com.covengers.springapi.config;
//
//import com.covengers.springapi.repo.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//    // Security 설정 및 Token 설정
//
//    private final CorsFilter corsFilter;
//    private final UserRepository userRepository;
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Bean // 해당 메서드의 리턴되는 값을 IoC로 등록해준다.
//    public BCryptPasswordEncoder encodedPwd(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .addFilter(corsFilter);
//        httpSecurity.authorizeRequests().anyRequest().permitAll();
////        httpSecurity.authorizeRequests().antMatchers("/api/user/**").permitAll();
////        httpSecurity.authorizeRequests().antMatchers("/api/chat/**").authenticated();
////        httpSecurity.authorizeRequests().antMatchers("/api/chat/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE,UNKNOWN");
//        httpSecurity.httpBasic().disable();
//        httpSecurity.csrf().disable();
////        httpSecurity.formLogin().loginProcessingUrl("/api/user/login"); //  REST API Controller
//        httpSecurity.formLogin().disable();
//        httpSecurity.userDetailsService(userDetailsServiceImpl);
//    }
//}
