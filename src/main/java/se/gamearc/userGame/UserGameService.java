package se.gamearc.userGame;

import org.springframework.stereotype.Service;
import se.gamearc.user.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserGameService {

  UserGameRepository userGameRepository;
  UserRepository userRepository;

  public UserGameService(UserGameRepository userGameRepository, UserRepository userRepository) {
    this.userGameRepository = userGameRepository;
    this.userRepository = userRepository;
  }

  public Set<UserGameDto> getAllUserGames(String username) {
    return userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());
  }

  public Set<UserGameDto> getUserGamesByTitle(String username, String gameTitle) {
    return userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .filter(userGame -> userGame.getGame().getTitle().toLowerCase().contains(gameTitle.toLowerCase()))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());
  }

  public Set<UserGameDto> getUserGamesByGenre(String username, String genre) {
    return userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .filter(userGame -> userGame.getGame().getGenre().getName().equals(genre))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());
  };

  public Set<UserGameDto> getUserGamesByStatus(String username, String status) {
    return userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .filter(userGame -> userGame.getStatus().getName().equals(status))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());
  }
}
