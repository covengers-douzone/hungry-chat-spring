package com.covengers.springapi.config;


import com.covengers.springapi.filter.CustomAuthenticationFilter;
import com.covengers.springapi.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfigNew extends WebSecurityConfigurerAdapter {
    // @RequiredArgsContructor 로 Injection 자동으로 시켜준다. @Autowired 하지 않아도돼
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CorsConfig corsConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/user/login");
        // 순서도 중요하다 순서가 꼬이지 않게 주의할것.
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(corsConfig.corsFilter());
        http.csrf().disable();
        http.httpBasic().disable();
//        http.authorizeRequests().antMatchers("/api/user/users").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers("/api/user/**").permitAll();
//        http.authorizeRequests().antMatchers("/api/chat/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter); // authenticaitionManager를 줘야한다. 인증서를 발급해주기 위해서
        // Customizing 한 UsernamePasswordAuthenticationFilter 보다 먼저 Filter가 적용될 수 있도록 설정.
        // request 를 intercept 해서 검증해야하므로.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        // 정의되어 있는 authenticationManager를 customfilter에 넣어
        // 새로운 인증을 만든다.
        // 이 메소드는 WebSecurityConfigurerAdapter에 있다.
        return super.authenticationManagerBean();
    }
}
