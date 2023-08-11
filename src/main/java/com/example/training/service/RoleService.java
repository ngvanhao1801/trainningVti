package com.example.training.service;

import com.example.training.model.Role;

import java.util.List;

public interface RoleService {
  List<Role> getAllRoles();

  int insert(Role role);

  int update(Role role);

  int delete(int id);

  Role findById(int id);
  List<Role> findRolesByUser(int userId);
}
