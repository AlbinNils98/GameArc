package se.gamearc.game.dto;

import se.gamearc.game.entity.Genre;

public record GenreDto(String name) {
  public static String from(Genre genre) {
    return genre.getName();
  }
}
