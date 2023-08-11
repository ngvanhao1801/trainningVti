package com.example.training.security;

import com.example.training.model.Role;
import com.example.training.model.User;
import com.example.training.repository.RoleRepository;
import com.example.training.service.RoleService;
import com.example.training.service.RoleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
  private User user;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(User user, List<String> roles) {
    return UserPrincipal.builder()
        .user(user)
        .authorities(roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
        .build();
  }

  public int getId() {
    return user.getId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
