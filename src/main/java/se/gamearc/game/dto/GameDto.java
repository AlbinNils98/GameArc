package se.gamearc.game.dto;

import se.gamearc.game.entity.Game;

import java.util.Set;
import java.util.stream.Collectors;

public record GameDto(
    String title,
    String description,
    String cover,
    Integer totalRating,
    Set<String> genres) {
  public static GameDto from(Game game) {
    return new GameDto(
        game.getTitle(),
        game.getDescription(),
        game.getCover(),
        game.getTotalRating(),
        game.getGenres().stream()
            .map(GenreDto::from)
            .collect(Collectors.toSet()));
  }
}
