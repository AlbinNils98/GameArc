package se.gamearc.userGame;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/api/")
@RestController
public class UserGameController {

  UserGameService userGameService;

  public UserGameController(UserGameService userGameService) {
    this.userGameService = userGameService;
  }

  @GetMapping("user-games/{username}")
  public Set<UserGameDto> getUserGames(@PathVariable String username) {
    return userGameService.getAllUserGames(username);
  }

  @GetMapping("user-games/{username}/name/{gameTitle}")
  public Set<UserGameDto> getUserGamesByTitle(@PathVariable String username, @PathVariable String gameTitle) {
    return userGameService.getUserGamesByTitle(username, gameTitle);
  }

  @GetMapping("user-games/{username}/genre/{gameGenre}")
  public Set<UserGameDto> getUserGamesByGenre(@PathVariable String username, @PathVariable String gameGenre) {
    return userGameService.getUserGamesByGenre(username, gameGenre);
  }

  @GetMapping("user-games/{username}/status/{status}")
  public Set<UserGameDto> getUserGamesByStatus(@PathVariable String username, @PathVariable String status) {
    return userGameService.getUserGamesByStatus(username, status);
  }
}
