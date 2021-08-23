package com.covengers.springapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.covengers.springapi.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    // Authorization filter, 오는 요청을 Intercept 해서 Token에 대한 검증을 진행할 것.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 검증을 할 필요가 없는 uri는 아무것도 하지 않고 다음 필터로 넘긴다.
        // join, main, login에서는 검증을 할 필요가 없으므로 다음과 같은 로직
        if(request.getContextPath().equals("/api/user/**")){
            filterChain.doFilter(request,response);
        } else {
            String authorizationHeader = request.getHeader("Authorization");
            // 이 토큰으로 오는건 다른 검사를 할 것 없다는 의미를 내포한다. "Bearer"
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length()); // Bearer 를 제외하고 token 가져오기
                    Algorithm algorithm = Algorithm.HMAC512(Constant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));
                    // jwt verifier를 만드는 방법.
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                    String expireTime = decodedJWT.getExpiresAt().toString();

                    // Conversion 하는 이유.
                    // SimpleGrantedAuthority 는 GrantedAuthority를 상속받고
                    // Spring이 권한에 대한 검사를 하기 위해선 GrantedAuthority 형태를 받길 원하기 때문에
                    // 아래와 같이 변환해주어야한다.
                    // UsernamePasswordAuthenticationToken 에 넣어서 SpringSecurity Context session에 넣어주기 위해
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    // 비밀번호는 넣지 않아도된다.
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    // 토큰이 같기 때문에 이 유저는 인증된 유저이고
                    // 그 유저의 이름과 비밀번호, 그리고 권한에 대해 Spring에 알려준다.
                    // 그럼 우리가 설정한 바에 따라 SpringSecurity가 검사하여 진행해줄것.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    log.error("Error login: {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(403);
//                    response.sendError(403);
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType("application/json"); //json 형태로 보내기
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
