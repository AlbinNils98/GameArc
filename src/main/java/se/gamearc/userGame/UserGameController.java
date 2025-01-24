package se.gamearc.userGame;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gamearc.userGame.dto.UserGameUpdateDto;
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

  @GetMapping("user-games/")
  public Set<UserGameDto> getUserGames(HttpSession session) {
    Integer userId = (Integer) session.getAttribute("userId");
    System.out.println(userId);
    return userGameService.getAllUserGames(userId);
  }

  @GetMapping("user-games/title/{gameTitle}")
  public Set<UserGameDto> getUserGamesByTitle(HttpSession session, @PathVariable String gameTitle) {
    Integer userId = (Integer) session.getAttribute("userId");
    return userGameService.getUserGamesByTitle(userId, gameTitle);
  }

  @GetMapping("user-games/genre/{gameGenre}")
  public Set<UserGameDto> getUserGamesByGenre(HttpSession session, @PathVariable String gameGenre) {
    Integer userId = (Integer) session.getAttribute("userId");
    return userGameService.getUserGamesByGenre(userId, gameGenre);
  }

  @GetMapping("user-games/status/{status}")
  public Set<UserGameDto> getUserGamesByStatus(HttpSession session, @PathVariable String status) {
    Integer userId = (Integer) session.getAttribute("userId");
    return userGameService.getUserGamesByStatus(userId, status);
  }

  @PostMapping("user-games/")
  public ResponseEntity<Void> saveUserGame(HttpSession session, @RequestBody UserIGDBGameDto userIGDBGameDto) {
    Integer userId = (Integer) session.getAttribute("userId");
    UserGame game = userGameService.saveUserGame(userId, userIGDBGameDto);
    String encodedTitle = game.getGame().getTitle().replace(" ", "%20");
    String location = "/api/user-games/%s/%s".formatted("title", encodedTitle);
    return ResponseEntity.created(URI.create(location)).build();
  }

  @PutMapping("user-games/")
  public ResponseEntity<Void> updateUserGame(HttpSession session, @RequestBody UserGameUpdateDto userGameUpdateDto) {
    Integer userId = (Integer) session.getAttribute("userId");
    userGameService.updateUserGame(userId, userGameUpdateDto);
    return ResponseEntity.ok().build();
  }

}
