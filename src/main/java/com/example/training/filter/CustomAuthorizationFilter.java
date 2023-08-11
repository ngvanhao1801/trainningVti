package com.example.training.filter;

import com.example.training.model.User;
import com.example.training.repository.UserRepository;
import com.example.training.security.UserPrincipal;
import com.example.training.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@AllArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

      String token = authorizationHeader.substring("Bearer ".length());
      if (JwtTokenUtil.validateAccessToken(token)) {

        String[] subject = JwtTokenUtil.getSubject(token).split(",");
        int id = Integer.valueOf(subject[0]);
        List<String> roles = (List<String>) JwtTokenUtil.getRoles(token);

//                Set authorities and roles
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        User user = userRepository.selectUserById(id);
        if (user != null) {
          UserPrincipal userPrincipal = UserPrincipal.create(user, roles);

          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

          filterChain.doFilter(request, response);
        }
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }

}
