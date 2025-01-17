package se.gamearc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.gamearc.user.entity.UserEntity;
import se.gamearc.user.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserDto user) throws Exception {
   userService.registerUser(user);
   return ResponseEntity.ok("User created successfully");
  }

  @PostMapping("/login")
  public String login(@RequestBody UserDto user) throws Exception {
    return userService.verify(user);
  }
}
