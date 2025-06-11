package se.gamearc.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class SameSiteCookieFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    chain.doFilter(request, response); // Proceed with the request

    if (response instanceof HttpServletResponse res && request instanceof HttpServletRequest req) {
      Collection<String> headers = res.getHeaders("Set-Cookie");

      boolean first = true;
      for (String header : headers) {
        if (header.contains("JSESSIONID") && !header.contains("SameSite")) {
          String updatedHeader = updateSameSite(header, req);

          if (first) {
            res.setHeader("Set-Cookie", updatedHeader);
            first = false;
          } else {
            res.addHeader("Set-Cookie", updatedHeader);
          }
        }
      }
    }
  }

  private String updateSameSite(String header, HttpServletRequest request) {
    boolean isDev = request.getServerName().startsWith("localhost")
        || request.getServerName().startsWith("127.");

    return isDev
        ? header + "; SameSite=None"                  // dev: don't require Secure
        : header + "; SameSite=None; Secure";         // prod: require Secure
  }
}