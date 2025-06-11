package se.gamearc.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.gamearc.exception.AlreadyExistsException;
import se.gamearc.exception.ResourceNotFoundException;
import se.gamearc.user.dto.RegisterDto;
import se.gamearc.user.dto.UserDto;
import se.gamearc.user.entity.UserEntity;
import se.gamearc.user.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  public void registerUser(RegisterDto registerDto) throws Exception {
    if (registerDto.username().length() < 4) {
      throw new IllegalArgumentException("Username length must be at least 4 characters");
    }
    if (registerDto.username().length() > 12) {
      throw new IllegalArgumentException("Username length can't be longer than 12 characters");
    }
    if (registerDto.password().length() < 8) {
      throw new IllegalArgumentException("Password length must be at least 8 characters");
    }
    UserEntity userEntity = userRepository.findByUsername(registerDto.username());
    if (userEntity != null) {
      throw new AlreadyExistsException("Username is already taken");
    }
    UserEntity user = new UserEntity();
    user.setUsername(registerDto.username());
    user.setPassword(encoder.encode(registerDto.password()));
    userRepository.save(user);
  }

  public UserDto getUserInfo(Integer userId) throws Exception {

    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return UserDto.from(user);
  }
}
