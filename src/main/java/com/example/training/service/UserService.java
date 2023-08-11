package com.example.training.service;

import com.example.training.model.User;

import java.util.List;

public interface UserService {

  int updateUser(User user);

  int deleteUserById(int id);

  int insertUser(User user);

  List<User> selectAllUser();

  User selectUserById(int id);
}

