package com.example.training.repository;

import com.example.training.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {

  int insertUser(User user);

  int updateUser(User user);

  User selectUserById(int id);

  List<User> selectAllUser();

  int deleteUserById(int id);

  boolean isExistsByUsername(String username);

  boolean isExistsById(int id);
  User findByUsername(String username);
}
