package org.demo.kaiaskin.services.mysql;

import io.jsonwebtoken.*;
import org.demo.kaiaskin.models.mysql.enums.Roles;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;


public interface JwtService {

   void init();
    String generateToken(UserDetails user, Roles role);
   String extractEmail(String token) ;
   String extractRole(String token);
   boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenValid(String token) ;
    boolean isTokenExpired(String token) ;
    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver) ;

}
