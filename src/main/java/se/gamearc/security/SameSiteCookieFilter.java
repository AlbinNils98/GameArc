package se.gamearc.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SameSiteCookieFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    if (!(response instanceof HttpServletResponse res) || !(request instanceof HttpServletRequest req)) {
      chain.doFilter(request, response);
      return;
    }

    HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(res) {
      @Override
      public void addHeader(String name, String value) {
        if ("Set-Cookie".equalsIgnoreCase(name) && value.contains("JSESSIONID") && !value.contains("SameSite")) {
          value = updateSameSite(value, req);
        }
        super.addHeader(name, value);
      }

      @Override
      public void setHeader(String name, String value) {
        if ("Set-Cookie".equalsIgnoreCase(name) && value.contains("JSESSIONID") && !value.contains("SameSite")) {
          value = updateSameSite(value, req);
        }
        super.setHeader(name, value);
      }
    };

    chain.doFilter(req, responseWrapper);
  }

  private String updateSameSite(String header, HttpServletRequest request) {
    boolean isDev = request.getServerName().startsWith("localhost")
        || request.getServerName().startsWith("127.");

    return isDev
        ? header + "; SameSite=None"
        : header + "; SameSite=None; Secure";
  }
}