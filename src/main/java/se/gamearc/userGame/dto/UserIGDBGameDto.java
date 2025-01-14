package se.gamearc.userGame.dto;

import se.gamearc.game.igdb.IGDBGameDto;

public record UserIGDBGameDto(IGDBGameDto game,
                              String status,
                              String comment,
                              Integer storyRating,
                              Integer graphicsRating,
                              Integer gameplayRating) {
}
