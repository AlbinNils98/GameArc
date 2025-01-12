package se.gamearc.game.igdb;

import se.gamearc.game.igdb.responses.IGDBGameResponse;

import java.util.List;

public record IGDBGameDto(String name, String summary, String cover, List<String> genres) {

  public static IGDBGameDto from(IGDBGameResponse response) {
    return new IGDBGameDto(
        response.name(),
        response.summary(),
        response.getCover(),
        response.getGenreNames()
    );
  }
}
