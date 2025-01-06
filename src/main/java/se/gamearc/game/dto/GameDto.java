package se.gamearc.game.dto;

import se.gamearc.game.entity.Game;

public record GameDto(String title, String description, String cover, Integer totalRating, GenreDto genre) {
  public static GameDto from(Game game) {
    return new GameDto(
        game.getTitle(),
        game.getDescription(),
        game.getCover(),
        game.getTotalRating(),
        GenreDto.from(game.getGenre()));
  }
}
