package se.gamearc.game.dto;

import se.gamearc.userGame.entity.Status;
import se.gamearc.userGame.entity.UserGame;

import java.util.Set;
import java.util.stream.Collectors;

public record UserGameDto(GameDto game,
                          Status status,
                          String comment,
                          Integer storyRating,
                          Integer graphicsRating,
                          Integer gameplayRating) {
  public UserGameDto from(UserGame userGame) {
    return new UserGameDto(
        GameDto.from(userGame.getGame()),
        userGame.getStatus(),
        userGame.getComment(),
        userGame.getStoryRating(),
        userGame.getGraphicsRating(),
        userGame.getGameplayRating());
  }
}
