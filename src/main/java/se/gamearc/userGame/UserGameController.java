package se.gamearc.userGame;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gamearc.userGame.dto.UserIGDBGameDto;
import se.gamearc.userGame.dto.UserGameDto;
import se.gamearc.userGame.entity.UserGame;

import java.net.URI;
import java.util.Set;

@RequestMapping("/api/")
@RestController
public class UserGameController {

  UserGameService userGameService;

  public UserGameController(UserGameService userGameService) {
    this.userGameService = userGameService;
  }

  @GetMapping("user-games/{userId}")
  public Set<UserGameDto> getUserGames(@PathVariable Integer userId) {
    return userGameService.getAllUserGames(userId);
  }

  @GetMapping("user-games/{userId}/title/{gameTitle}")
  public Set<UserGameDto> getUserGamesByTitle(@PathVariable Integer userId, @PathVariable String gameTitle) {
    return userGameService.getUserGamesByTitle(userId, gameTitle);
  }

  @GetMapping("user-games/{userId}/genre/{gameGenre}")
  public Set<UserGameDto> getUserGamesByGenre(@PathVariable Integer userId, @PathVariable String gameGenre) {
    return userGameService.getUserGamesByGenre(userId, gameGenre);
  }

  @GetMapping("user-games/{userId}/status/{status}")
  public Set<UserGameDto> getUserGamesByStatus(@PathVariable Integer userId, @PathVariable String status) {
    return userGameService.getUserGamesByStatus(userId, status);
  }

  @PostMapping("user-games/{userId}")
  public ResponseEntity<Void> saveUserGame(@PathVariable Integer userId, @RequestBody UserIGDBGameDto userIGDBGameDto) {
    UserGame game = userGameService.saveUserGame(userId, userIGDBGameDto);
    return ResponseEntity.created(URI.create("/api/user-games/" + userId + "/" + userIGDBGameDto.game().title())).build();
  }

  @PutMapping("user-games/{userId}")
  public ResponseEntity<Void> updateUserGame(@PathVariable Integer userId, @RequestBody UserGameDto userGameDto) {
    userGameService.updateUserGame(userId, userGameDto);
    return ResponseEntity.ok().build();
  }
}
