package se.gamearc.userGame.dto;

public record UserGameUpdateDto(Integer gameId,
                                String status,
                                String comment,
                                Integer storyRating,
                                Integer graphicsRating,
                                Integer gameplayRating) {
}
