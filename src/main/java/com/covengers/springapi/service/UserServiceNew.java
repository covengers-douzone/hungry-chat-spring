package com.covengers.springapi.service;

import com.covengers.springapi.model.User;

import java.util.List;

public interface UserServiceNew {
   void saveUser(User user);
   List<User> getUsers(); // 전체 유저 가져오기
   void addTokenToUser(String username, String token);
   User getUser(String username);
   Boolean pwUpdate(User user);

   String findByNameAndPhoneNumber(String name, String phonenumber);

   User findByPhoneNumber(String phoneNumber);
   Long getNo(String username);
}
