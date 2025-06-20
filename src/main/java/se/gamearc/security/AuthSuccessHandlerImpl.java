package se.gamearc.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import se.gamearc.user.UserPrincipal;

import java.io.IOException;

@Component
public class AuthSuccessHandlerImpl implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    UserPrincipal authResult = (UserPrincipal) authentication.getPrincipal();
    request.getSession().setAttribute("userId", authResult.getId());

    response.setStatus(HttpServletResponse.SC_OK);
  }
}
