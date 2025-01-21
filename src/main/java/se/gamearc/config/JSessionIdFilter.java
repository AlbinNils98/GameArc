//package se.gamearc.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//@Component
//public class JSessionIdFilter extends OncePerRequestFilter {
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//      throws ServletException, IOException {
//    boolean jsessionIdValid = false;
//    Cookie[] cookies = request.getCookies();
//
//    if (cookies != null) {
//      Cookie jsessionIdCookie = Arrays.stream(cookies)
//          .filter(cookie -> "JSESSIONID".equals(cookie.getName()))
//          .findFirst()
//          .orElse(null);
//      if (jsessionIdCookie != null) {
//        SecurityContext context = (SecurityContext) request.getSession()
//            .getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
//        if (context != null && context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
//          jsessionIdValid = true;
//        }
//      }
//    }
//
//    if (!jsessionIdValid) {
//      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid session or not authenticated");
//      return;
//    }
//
//    filterChain.doFilter(request, response);
//  }
//}
