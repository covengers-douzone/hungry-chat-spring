package com.covengers.springapi.service;

import com.covengers.springapi.model.User;

import java.util.List;

public interface UserServiceNew {
   User saveUser(User user);
//   void addRoleToUser(String username, String role);
   User getUser(String username);
   List<User> getUsers(); // 전체 유저 가져오기
}
