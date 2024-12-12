package se.gamearc.game.dto;

import se.gamearc.game.entity.Genre;

import java.io.Serializable;

public record GenreDto(Integer id, String name) {
  public static GenreDto from(Genre genre) {
    return new GenreDto(genre.getId(), genre.getName());
  }
}
