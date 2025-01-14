package se.gamearc.userGame.api;

import se.gamearc.userGame.dto.UserGameDto;
import se.gamearc.userGame.dto.UserIGDBGameDto;

public record Ratings(Integer storyRating, Integer graphicsRating, Integer gameplayRating) {

  public static Ratings fromUserGameDto(UserGameDto userGameDto) {
    return new Ratings(
        userGameDto.storyRating(),
        userGameDto.graphicsRating(),
        userGameDto.gameplayRating()
    );
  }

  public static Ratings fromUserIGDBGameDto(UserIGDBGameDto userIGDBGameDto) {
    return new Ratings(
        userIGDBGameDto.storyRating(),
        userIGDBGameDto.graphicsRating(),
        userIGDBGameDto.gameplayRating()
    );
  }
}
