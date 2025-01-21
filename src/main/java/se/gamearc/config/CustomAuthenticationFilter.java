package se.gamearc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import se.gamearc.user.UserPrincipal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    setFilterProcessesUrl("/api/login");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res){
    try {
      Map<String, String> credentials = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);
      String username = credentials.get("username");
      String password = credentials.get("password");
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
      Authentication auth = authenticationManager.authenticate(authToken);

      SecurityContextHolder.getContext().setAuthentication(auth);
      return auth;
    }catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    UserPrincipal userDetails = (UserPrincipal) authResult.getPrincipal();

    req.getSession().setAttribute("userId", userDetails.getId());
    req.getSession().setAttribute("username", userDetails.getUsername());
    System.out.println("Session ID: " + req.getSession().getId());
    System.out.println("Authenticated User: " + userDetails.getUsername());
    res.setStatus(HttpServletResponse.SC_OK);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }
}
