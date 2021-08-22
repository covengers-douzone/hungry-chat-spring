package com.covengers.springapi.jwt;

import com.covengers.springapi.Constant;
import com.covengers.springapi.model.User;
import com.covengers.springapi.repo.UserRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public String createToken(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.SECRET_KEY);
        Key signKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        Map <String,Object> map = new HashMap<>();
        map.put("username", username);     // claim 에 담을 username 값 생성
        long nowTime = System.currentTimeMillis(); // 만료시간

        return (Jwts.builder()
                .setSubject(Constant.SUBJECT)
                .addClaims(map)
                .signWith(signatureAlgorithm, signKey)
                .setExpiration(new Date(nowTime + Constant.EXPIRE_TIME))
                .compact());
    }

    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Constant.SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public void isUsable(String jwt) throws Exception {
        Jwt<Header, Claims> claimsJwt = Jwts.parser().setSigningKey(Constant.SECRET_KEY).parseClaimsJwt(jwt);
        System.out.println(claimsJwt.toString());
    }
}
