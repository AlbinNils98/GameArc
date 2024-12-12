package se.gamearc.game.dto;

import se.gamearc.game.entity.UserGame;
import se.gamearc.user.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserGameDto(GameDto game, Set<RatingDto> ratings) {
  public UserGameDto from(UserGame userGame) {
    return new UserGameDto(
        GameDto.from(userGame.getGame()),
        userGame.getRatings().stream()
            .map(RatingDto::from)
            .collect(Collectors.toSet()));
  }
}
