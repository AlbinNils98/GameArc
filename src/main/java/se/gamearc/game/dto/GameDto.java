package se.gamearc.game.dto;

import se.gamearc.game.entity.Game;

public record GameDto(Integer id, String title, String description, String cover, Integer totalRating, GenreDto genre) {
  public static GameDto from(Game game) {
    return new GameDto(
        game.getId(),
        game.getTitle(),
        game.getDescription(),
        game.getCover(),
        game.getTotalRating(),
        GenreDto.from(game.getGenre()));
  }
}
