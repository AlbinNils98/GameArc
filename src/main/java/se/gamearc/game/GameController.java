package se.gamearc.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.gamearc.game.dto.GameDto;
import se.gamearc.service.GameService;

import java.util.List;
import java.util.Set;

@RequestMapping("/api")
@RestController
public class GameController {
  GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/games")
  public Set<GameDto> getGames() {
    return gameService.getAllGames();
  }

  @GetMapping("/games/{id}")
  public GameDto getGame(@PathVariable Integer id) {
    return gameService.getGameById(id);
  }

  @GetMapping("/games/name/{name}")
  public Set<GameDto> getGameByName(@PathVariable String name) {
    return gameService.getGameByName(name);
  }
}
