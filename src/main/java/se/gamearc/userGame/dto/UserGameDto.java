package se.gamearc.userGame.dto;

import se.gamearc.game.dto.GameDto;
import se.gamearc.userGame.entity.UserGame;

public record UserGameDto(
    Integer id,
    GameDto game,
    String status,
    String comment,
    Integer storyRating,
    Integer graphicsRating,
    Integer gameplayRating) {
  public static UserGameDto from(UserGame userGame) {
    return new UserGameDto(
        userGame.getId(),
        GameDto.from(userGame.getGame()),
        userGame.getStatus().getName(),
        userGame.getComment(),
        userGame.getStoryRating(),
        userGame.getGraphicsRating(),
        userGame.getGameplayRating());
  }
}
