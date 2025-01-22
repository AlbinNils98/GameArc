package se.gamearc.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
