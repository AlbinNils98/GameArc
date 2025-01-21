package se.gamearc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.gamearc.user.service.UserService;


@RestController
public class UserController {

  private final UserService userService;

  AuthenticationManager authenticationManager;

  public UserController(UserService userService, AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
  }


  @PostMapping("/api/register")
  public ResponseEntity<String> register(@RequestBody UserDto user) throws Exception {
   userService.registerUser(user);
   return ResponseEntity.ok("User created successfully");
  }

  @PostMapping("/api/login")
  public ResponseEntity<String> login(@RequestBody UserDto user) throws Exception {

    try {
      UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(user.username(), user.password());
      Authentication auth = authenticationManager.authenticate(authToken);
      SecurityContextHolder.getContext().setAuthentication(auth);

      return ResponseEntity.ok("User logged in successfully");
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }



  }

}
