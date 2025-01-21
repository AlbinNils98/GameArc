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

  public UserController(UserService userService, AuthenticationManager authenticationManager) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserDto user) throws Exception {
   userService.registerUser(user);
   return ResponseEntity.ok("User created successfully");
  }

}
