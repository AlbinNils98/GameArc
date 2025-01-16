package se.gamearc.userGame.api;

import se.gamearc.userGame.dto.UserGameUpdateDto;
import se.gamearc.userGame.dto.UserIGDBGameDto;

public record Ratings(Integer storyRating, Integer graphicsRating, Integer gameplayRating) {

  public static Ratings fromUserGameUpdateDto(UserGameUpdateDto userGameUpdateDto) {
    return new Ratings(
        userGameUpdateDto.storyRating(),
        userGameUpdateDto.graphicsRating(),
        userGameUpdateDto.gameplayRating()
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
