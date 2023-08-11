package com.example.training.security;

import com.example.training.model.Role;
import com.example.training.model.User;
import com.example.training.repository.RoleRepository;
import com.example.training.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OpUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) throw new UsernameNotFoundException("User not found with username or email : " + username);
    List<Role> roles = roleRepository.findRolesByUser(user.getId());
    List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());

    return UserPrincipal.create(user, roleNames);
  }
}
