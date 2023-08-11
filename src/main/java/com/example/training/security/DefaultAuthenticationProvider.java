package com.example.training.security;

import com.example.training.config.SecurityConfig;
import com.example.training.exception.InvalidException;
import com.example.training.model.User;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("defaultAuthProvider")
@Primary
@Slf4j
@AllArgsConstructor
public class DefaultAuthenticationProvider implements AuthenticationProvider {
  private final UserDetailsService userDetailServices;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = Objects.toString(authentication.getCredentials(), null);
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      throw new InvalidException("Wrong username or password");
    }
    UserPrincipal userPrincipal = (UserPrincipal) userDetailServices.loadUserByUsername(username);
    if (Objects.nonNull(userPrincipal)) {
      if (SecurityConfig.passwordEncoder().matches(password, userPrincipal.getPassword())) {
        return new UsernamePasswordAuthenticationToken(userPrincipal, password, userPrincipal.getAuthorities());
      }
    }
    throw new InvalidException("Wrong password");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
