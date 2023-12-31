package com.example.training.filter;

import com.example.training.security.UserPrincipal;
import com.example.training.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    log.info("Username is: {}",username);
    log.info("Password is : {}", password);

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    UserPrincipal userPrincipal = (UserPrincipal) authResult.getPrincipal();

    String accessToken = JwtTokenUtil.generateAccessToken(userPrincipal);
    String refreshToken = JwtTokenUtil.generateAccessToken(userPrincipal);

    Map<String, String> tokens = new HashMap<>();
    tokens.put("access_token",accessToken);
    tokens.put("refresh_token",refreshToken);

    response.setContentType("application/json");
    new ObjectMapper().writeValue(response.getOutputStream(),tokens);
  }
}
