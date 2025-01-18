package se.gamearc.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import se.gamearc.exception.InvalidJwtAuthenticationException;
import se.gamearc.user.service.JWTService;
import se.gamearc.user.service.MyUserDetailService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private JWTService jwtService;

  @Autowired
  private ApplicationContext context;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String jwtToken = null;
    String username = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      jwtToken = authHeader.substring(7);
      try {
        username = jwtService.extractUsername(jwtToken);
      } catch (InvalidJwtAuthenticationException e) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("Invalid JWT token: " + e.getMessage());
        return;
      }
    }

    try {
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);

        if (jwtService.validateToken(jwtToken, userDetails)) {
          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource()
              .buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    }catch (InvalidJwtAuthenticationException e) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("Invalid JWT token: " + e.getMessage());
      return;
    }
    filterChain.doFilter(request, response);
  }
}
