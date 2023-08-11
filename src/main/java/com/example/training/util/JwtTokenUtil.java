package com.example.training.util;

import com.example.training.common.constant.AppConstants;
import com.example.training.security.UserPrincipal;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
  private static long EXPIRE_DURATION;
  private static String SECRET_KEY;

  public static String generateAccessToken(UserPrincipal userPrincipal) {
    Map<String, Object> roles = new HashMap<>();
    roles.put(AppConstants.ROLES, userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    return Jwts.builder()
        .setClaims(roles)
        .setSubject(String.format("%s,%s", userPrincipal.getId(), userPrincipal.getUsername()))
        .setIssuer("token")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
        .signWith(SignatureAlgorithm.HS384, SECRET_KEY)
        .compact();
  }

  public static boolean validateAccessToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public static String getSubject(String accessToken) {
    return parseClaims(accessToken).getSubject();
  }

  public static Object getRoles(String accessToken) {
    return parseClaims(accessToken).get(AppConstants.ROLES);
  }

  public static Claims parseClaims(String accessToken) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(accessToken)
        .getBody();
  }

  @Value("${jwt.expire-duration}")
  public void setExpireDuration(long expireDuration) {
    EXPIRE_DURATION = expireDuration;
  }

  @Value("${jwt.secret-key}")
  public void setSecretKey(String secretKey) {
    SECRET_KEY = secretKey;
  }
}
