package se.gamearc.userGame.dto;

import se.gamearc.igdb.IGDBGameDto;

public record UserIGDBGameDto(IGDBGameDto game,
                              String status,
                              String comment,
                              Integer storyRating,
                              Integer graphicsRating,
                              Integer gameplayRating) {
}
