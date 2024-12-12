package se.gamearc.game.dto;

import se.gamearc.game.entity.Genre;

import java.util.Set;
import java.util.stream.Collectors;

public record GenreWithGamesDto(String name ,Set<GameDto> games) {
  public static GenreWithGamesDto from(Genre genre){
    return new GenreWithGamesDto(
        genre.getName(),
        genre.getGames().stream()
            .map(GameDto::from)
            .collect(Collectors.toSet()));
  }
}
