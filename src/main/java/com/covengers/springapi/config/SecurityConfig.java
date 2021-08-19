package com.covengers.springapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Bean // 해당 메서드의 리턴되는 값을 IoC로 등록해준다.
    public BCryptPasswordEncoder encodedPwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter) // corsFilter로 설정된 필터를 거쳐야 가능하다.
                .formLogin().disable() // form 태그로 로그인하는것을 사용하지 않겠다.
                .httpBasic().disable() // 기본적인 http 방식도 사용하지 않겠다.
                .authorizeRequests()
//                .antMatchers("/api/unknown/**")
//                .access("hasRole('ROLE_UNKNOWN')")
//                .antMatchers("/api/user/**")
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/api/admin/**")
//                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
