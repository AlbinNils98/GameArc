package se.gamearc.game.dto;

import se.gamearc.game.entity.Rating;

public record RatingDto(RatingTypeDto type,Integer rating) {
  public static RatingDto from(Rating rating) {
    return new RatingDto(
        RatingTypeDto.from(rating.getRatingType()),
        rating.getValue());
  }
}
