package se.gamearc.game.igdb;

import se.gamearc.game.entity.Game;
import se.gamearc.game.igdb.responses.IGDBGameResponse;

import java.util.List;

public record IGDBGameDto(
    String title,
    String description,
    String cover,
    List<String> genres) {

  public static IGDBGameDto from(IGDBGameResponse response) {
    return new IGDBGameDto(
        response.name(),
        response.summary(),
        response.getCover(),
        response.getGenreNames()
    );
  }

  public Game toGame(){
    Game game = new Game();
    game.setTitle(title);
    game.setCover(cover);
    game.setDescription(description);
    return game;
  }
}
