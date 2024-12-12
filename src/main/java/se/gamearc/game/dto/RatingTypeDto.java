package se.gamearc.game.dto;

import se.gamearc.game.entity.RatingType;

public record RatingTypeDto(String name) {

  public static RatingTypeDto from(RatingType ratingType) {
    return new RatingTypeDto(ratingType.getName());
  }
}
