package se.gamearc.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import se.gamearc.exception.InvalidJwtAuthenticationException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

  private String secretKey = "";

  public JWTService() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = keyGen.generateKey();
      secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }

  }

  public String generateToken(String username) {

    Map<String, Object> claims = new HashMap<>();
    claims.put("username", username);

    return Jwts.builder()
        .claims()
        .add(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
        .and()
        .signWith(getKey())
        .compact();
  }

  private SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String jwtToken) {
    try {
      return extractClaim(jwtToken, Claims::getSubject);
    }catch (Exception e) {
      throw new InvalidJwtAuthenticationException("could not extract username");
    }
  }

  private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwtToken);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String jwtToken) {
    return Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(jwtToken)
        .getPayload();
  }

  public boolean validateToken(String jwtToken, UserDetails userDetails) {
    try {
      final String username = extractUsername(jwtToken);
      if (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken)) {
        return true;
      }
    } catch (MalformedJwtException e) {
      throw new InvalidJwtAuthenticationException("JWT token is malformed");
    }catch (ExpiredJwtException e) {
      throw new InvalidJwtAuthenticationException("JWT token has expired");
    }catch (Exception e) {
      throw new InvalidJwtAuthenticationException("Invalid JWT token");
    }
    return false;
  }

  private boolean isTokenExpired(String jwtToken) {
    try {
      return extractExpiration(jwtToken).before(new Date());
    }catch (NullPointerException e) {
      throw new InvalidJwtAuthenticationException("JWT token expiration date is missing or malformed.");
    }catch (Exception e) {
      throw new InvalidJwtAuthenticationException("JWT token may be expired or has a malformed expiration date.");
    }
  }

  private Date extractExpiration(String jwtToken) {
    try {
      return extractClaim(jwtToken, Claims::getExpiration);
    }catch (NullPointerException e) {
      throw new InvalidJwtAuthenticationException("JWT token expiration date is missing.");
    }catch (Exception e) {
      throw new InvalidJwtAuthenticationException("Failed to extract expiration date from JWT token.");
    }

  }
}
