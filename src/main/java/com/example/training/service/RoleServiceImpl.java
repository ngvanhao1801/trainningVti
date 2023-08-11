package com.example.training.service;

import com.example.training.exception.DuplicateException;
import com.example.training.exception.NotFoundException;
import com.example.training.model.Role;
import com.example.training.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
  private RoleRepository roleRepository;

  public List<Role> getAllRoles() {
    return roleRepository.getAllRoles();
  }

  public int insert(Role role) {
    if (roleRepository.checkExistByNameOrCode(role) != 0) {
      throw new DuplicateException("Name or code already exists");
    }
    int result = roleRepository.insert(role);
    return result;
  }

  public int update(Role role) {
    if (roleRepository.checkExistById(role.getId()) == 0) {
      throw new NotFoundException("Role does not exist");
    }
    int result = roleRepository.update(role);
    return result;
  }

  public int delete(int id) {
    if (roleRepository.checkExistById(id) == 0) {
      throw new NotFoundException("Role does not exist");
    }
    int result = roleRepository.deleteById(id);
    return result;
  }

  public Role findById(int id) {
    if (roleRepository.checkExistById(id) == 0) {
      throw new NotFoundException("Role does not exist");
    }
    Role result = roleRepository.findById(id);
    return result;
  }

  @Override
  public List<Role> findRolesByUser(int userId) {
    return roleRepository.findRolesByUser(userId);
  }
}
