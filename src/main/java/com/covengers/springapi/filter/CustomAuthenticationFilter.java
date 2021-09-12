package com.covengers.springapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.covengers.springapi.ApplicationContextProvider;
import com.covengers.springapi.Constant;
import com.covengers.springapi.dto.JsonResult;
import com.covengers.springapi.service.UserServiceNewImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // unsuccessfulAuthentication => 로그인 몇 회 실패하면 잠김 등의 기능 구현 가능
    private final AuthenticationManager authenticationManager;
    private boolean postOnly = true; // postOnly 로 받을지 true false로 결정, 아래 로직 참고
    private Map<String, String> jsonResult;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String passwordParameter = super.getPasswordParameter();
        if(request.getHeader("Content-Type").equals(ContentType.APPLICATION_JSON.getMimeType())){
            return jsonResult.get(passwordParameter);
        }
        return request.getParameter(passwordParameter);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String usernameParameter = super.getUsernameParameter();
        if(request.getHeader("Content-Type").equals(ContentType.APPLICATION_JSON.getMimeType())){
            return jsonResult.get(usernameParameter);
        }
        return request.getParameter(usernameParameter);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 여기서 request.getParameter는 www.xform 데이터 형식이다.
        // 우린 react 로부터 json 데이터로 받아오기 때문에
        // 이 부분을 해결해야한다.
        if(postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

//         request 의 형식이 JSON 형태일때,
        if(request.getHeader("Content-Type").equals(ContentType.APPLICATION_JSON.getMimeType())){
            ObjectMapper mapper = new ObjectMapper();
            try{
                this.jsonResult = mapper.readValue(request.getReader().lines().collect(Collectors.joining()), new TypeReference<Map<String, String>>() {
                });
            } catch (IOException e){
                e.printStackTrace();
                throw new AuthenticationServiceException("Request Content-Type(application/json) Parsing Error");
            }
        }
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        log.info("Username :{}", username); log.info("Pasword :{}", password);

        if(username == null){
            username = "";
        }
        if(password == null){
            password = "";
        }
        username = username.trim();

        // username, password or username, password, authority 로 만들 수 있다.
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // credential 를 만들어서 유저를 인증해줄 수 있게 authentiacationManager를 호출.
        // 인증서로 만들어진(log in 시도시에 받아온 username, password로 만들어진) token을 주면서 이 토큰은 인증된 토큰이다라고 말하는것.
        // 여기서 받아온 데이터를 Json 객체로 바꿔 body에 보내주기 위해서는 request를 ObjectMapper를 사용해 변환후 보내주는 방법이 있다.
        // attemptAuthentication 이 실패하게 되면 spring은 speed out of the user, 시간 초과 발생 시킨다.
        // 성공시에는 successfulAuthentication으로
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        log.info("successfulAuthentication 작동");
        // 1. 토큰 생성
        // 2. 토큰 보내기
        // 3. 헤더 혹은 바디에 데이터를 담아서 보내야한다. (response로)

        // 우리가 모델링에서 정의한 User가 아닌 Spring이 가지고있는 UserDetails 유저를 가지고 와야 한다.
        // 인증된 유저를 가져오는것.
//         User user = authentication.getPrincipal();
        User user = (User)authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC512(Constant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        String access_token = JWT.create()
                .withSubject(user.getUsername()) // 이름과 같음. 아무거나 들어가도 상관없지만 유니크한 정보가 필요.
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.EXPIRE_TIME))
                .withIssuer(request.getRequestURI().toString()) // 토큰의 저자, 혹은 회사 등을 기입. 여기선 url
                .withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())) // roles 를 simple string 으로 주는 로직
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername()) // 이름과 같음. 아무거나 들어가도 상관없지만 유니크한 정보가 필요.
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.EXPIRE_TIME * 10)) // refresh token은 시간을 좀 더 늘려서
                .withIssuer(request.getRequestURI().toString()) // 토큰의 저자, 혹은 회사 등을 기입. 여기선 url
                .sign(algorithm);


        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        UserServiceNewImpl serviceBean = applicationContext.getBean("userServiceNewImpl", UserServiceNewImpl.class);
        System.out.println("ServiceBean : " + serviceBean.getUser(user.getUsername()));
        serviceBean.addTokenToUser(user.getUsername(), access_token);

        // Header 로 보내기
        response.setHeader("Authorization", "Bearer "+access_token);
        //response.setHeader("access_token", access_token);
        // response.setHeader("refresh_token", access_token);

//        com.covengers.springapi.model.User myUser = serviceBean.getUser(user.getUsername());
        // Body 로 보내기
        // 여기서 user는 인증된 user를 가리킴
        System.out.println("user.getAuthorities()"+ user.getAuthorities());
        Map<String, String> token = new HashMap<>();
        token.put("Authorization", "Bearer "+access_token);
        token.put("username", user.getUsername());
        token.put("no",serviceBean.getNo(user.getUsername()).toString());
        token.put("name",serviceBean.getUser(user.getUsername()).getName());
//        token.put("role",(myUser.getRole()));
        token.put("role",((serviceBean.getUser(user.getUsername())).getRole()));

        response.setContentType("application/json"); //json 형태로 보내기
        new ObjectMapper().writeValue(response.getOutputStream(), token);
    }
}
