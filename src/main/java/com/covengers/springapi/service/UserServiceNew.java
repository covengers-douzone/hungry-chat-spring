package com.covengers.springapi.service;

import com.covengers.springapi.model.User;

import java.util.List;

public interface UserServiceNew {
   User saveUser(User user);
   List<User> getUsers(); // 전체 유저 가져오기
   void addTokenToUser(String username, String token);
   User getUser(String username);
}
