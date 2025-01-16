package se.gamearc.game;

import org.springframework.web.bind.annotation.*;
import se.gamearc.igdb.IGDBGameDto;
import se.gamearc.igdb.IGDBService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class GameController {

  IGDBService igdbService;

  public GameController(IGDBService igdbService) {
    this.igdbService = igdbService;
  }

  @GetMapping("/games/name/{title}")
  public List<IGDBGameDto> getGameByTitle(
      @PathVariable String title,
      @RequestParam(defaultValue = "10") Integer limit,
      @RequestParam(defaultValue = "0") Integer offset) {
      return igdbService.fetchIGDBGamesByTitle(title, limit, offset);
    }
  }
