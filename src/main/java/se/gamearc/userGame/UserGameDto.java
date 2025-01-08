package se.gamearc.userGame;

import se.gamearc.game.dto.GameDto;
import se.gamearc.userGame.entity.Status;
import se.gamearc.userGame.entity.UserGame;

public record UserGameDto(GameDto game,
                          String status,
                          String comment,
                          Integer storyRating,
                          Integer graphicsRating,
                          Integer gameplayRating) {
  public static UserGameDto from(UserGame userGame) {
    return new UserGameDto(
        GameDto.from(userGame.getGame()),
        userGame.getStatus().getName(),
        userGame.getComment(),
        userGame.getStoryRating(),
        userGame.getGraphicsRating(),
        userGame.getGameplayRating());
  }
}
