package com.example.training.repository;

import com.example.training.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRepository {
  List<Role> getAllRoles();

  int insert(Role role);

  int update(Role role);

  int deleteById(int id);

  int checkExistByNameOrCode(Role role);

  int checkExistById(int id);

  Role findById(int id);

  List<Role> findRolesByUser(int userId);
}