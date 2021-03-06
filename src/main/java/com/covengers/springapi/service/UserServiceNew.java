package com.covengers.springapi.service;

import com.covengers.springapi.model.User;

import java.util.List;

public interface UserServiceNew {
   void saveUser(User user);
   List<User> getUsers(); // 전체 유저 가져오기
   void addTokenToUser(String username, String token);
   User getUser(String username);
   User getUserAndPassword(String username, String password);

   Boolean pwUpdate(User user);
   Boolean userActivation(User user);

   String findByNameAndPhoneNumber(String name, String phonenumber);
   User findByPhoneNumber(String phoneNumber);
   Long getNo(String username);
   User saveUnknownUser();
}
