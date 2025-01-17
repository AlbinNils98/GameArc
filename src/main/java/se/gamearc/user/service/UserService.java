package se.gamearc.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.gamearc.exception.InvalidArgumentException;
import se.gamearc.user.UserDto;
import se.gamearc.user.entity.UserEntity;
import se.gamearc.user.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JWTService jwtService;

  @Autowired
  AuthenticationManager authnManager;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  public void registerUser(UserDto userDto) throws Exception {
    UserEntity user = new UserEntity();
    user.setUsername(userDto.username());
    user.setPassword(encoder.encode(userDto.password()));
    userRepository.save(user);
  }

  public String verify(UserDto userDto) throws AuthenticationException {
    try {
    Authentication auth =
        authnManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userDto.username(), userDto.password()));
    if (auth.isAuthenticated()) {
      return jwtService.generateToken(userDto.username());
    }
    }catch(AuthenticationException e) {
      throw new InvalidArgumentException("Invalid username or password");
    }
    return "Fail";
  }
}
