<<<<<<< HEAD
package com.covengers.springapi.config;

import com.covengers.springapi.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CorsFilter corsFilter;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean // 해당 메서드의 리턴되는 값을 IoC로 등록해준다.
    public BCryptPasswordEncoder encodedPwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Son").password(encodedPwd().encode("1234"))
                .authorities("ROLE_USER", "ROLE_ADMIN");
        auth.userDetailsService(userService).passwordEncoder(encodedPwd());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests((request) -> request.antMatchers("/api/login").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
                .addFilterBefore(new JWTAuthentication(userService, jwtTokenHelper), UsernamePasswordAuthenticationFilter.class)
                .addFilter(corsFilter) // corsFilter로 설정된 필터를 거쳐야 가능하다.
                .formLogin().disable() // form 태그로 로그인하는것을 사용하지 않겠다.
                .httpBasic().disable() // 기본적인 http 방식도 사용하지 않겠다.
                ;
    }
}
=======
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
>>>>>>> 48ce93124e5e030068fb13209f675442a13d50e3
