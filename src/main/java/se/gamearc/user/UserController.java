package se.gamearc.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gamearc.user.dto.RegisterDto;
import se.gamearc.user.dto.UserDto;
import se.gamearc.user.service.UserService;


@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/register", consumes = "application/json")
  public ResponseEntity<String> register(@RequestBody RegisterDto user) throws Exception {
   userService.registerUser(user);
   return ResponseEntity.ok("User created successfully");
  }

  @GetMapping("/user-info")
  public UserDto getUser(HttpSession session) throws Exception {
    Integer userId = (Integer) session.getAttribute("userId");
    return userService.getUserInfo(userId);
  }
}
