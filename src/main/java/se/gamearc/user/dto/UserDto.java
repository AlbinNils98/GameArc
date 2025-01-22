package se.gamearc.user.dto;

import se.gamearc.user.entity.UserEntity;

public record UserDto(String username) {

  public static UserDto from(UserEntity user) {
    return new UserDto(user.getUsername());
  }
}
