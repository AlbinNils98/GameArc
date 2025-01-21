package se.gamearc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gamearc.user.service.UserService;


@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/api/register")
  public ResponseEntity<String> register(@RequestBody UserDto user) throws Exception {
   userService.registerUser(user);
   return ResponseEntity.ok("User created successfully");
  }

//  @PostMapping("/api/login")
//  public ResponseEntity<String> login(@RequestBody UserDto user) throws Exception {
//
//    String msg = "Hello " + user.username() + " from Login!";
//
//    try {
//      return new ResponseEntity<>(msg, HttpStatus.OK);
//    } catch (Exception e) {
//      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }

}
