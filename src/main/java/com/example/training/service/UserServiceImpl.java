package com.example.training.service;

import com.example.training.config.SecurityConfig;
import com.example.training.exception.DuplicateException;
import com.example.training.exception.NotFoundException;
import com.example.training.model.User;
import com.example.training.repository.UserRepository;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Setter
@Getter
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public List<User> selectAllUser() {
    return userRepository.selectAllUser();
  }

  public User selectUserById(int id) {
    if (!userRepository.isExistsById(id)) {
      throw new NotFoundException("Can't find user with id: " + id);
    }
    User result = userRepository.selectUserById(id);
    return result;
  }

  public int insertUser(User user) {
    if (userRepository.isExistsByUsername(user.getUsername())) {
      throw new DuplicateException("Username is exited");
    }
    if (user!=null && StringUtils.isNotBlank(user.getPassword())) {
      String encodedPassword = SecurityConfig.passwordEncoder().encode(user.getPassword());
      user.setPassword(encodedPassword);
    }
    int result = userRepository.insertUser(user);
    return result;
  }

  public int updateUser(User user) {
    if (!userRepository.isExistsById(user.getId())) {
      throw new NotFoundException("User does not exist");
    }
    int result = userRepository.updateUser(user);
    return result;
  }

  public int deleteUserById(int id) {
    if (userRepository.deleteUserById(id) == 0) {
      throw new NotFoundException("Can't find user with id: " + id);
    }
    int result = userRepository.deleteUserById(id);
    return result;
  }
}
