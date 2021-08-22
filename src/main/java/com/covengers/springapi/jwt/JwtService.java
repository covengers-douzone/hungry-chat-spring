package com.covengers.springapi.jwt;

import com.covengers.springapi.model.User;

public interface JwtService {
    String createToken(String username);
    String getSubject(String token);
    void isUsable(String jwt) throws Exception;
}
