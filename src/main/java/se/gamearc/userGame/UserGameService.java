package se.gamearc.userGame;

import org.springframework.stereotype.Service;
import se.gamearc.exception.ResourceNotFoundException;
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
    Set<UserGameDto> games = userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    if (games.isEmpty()) {
      throw new ResourceNotFoundException("No games found");
    }

    return checkIfEmpty(games);
  }

  public Set<UserGameDto> getUserGamesByTitle(String username, String gameTitle) {
    Set<UserGameDto> games = userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .filter(userGame -> userGame.getGame().getTitle().toLowerCase().contains(gameTitle.toLowerCase()))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    if (games.isEmpty()) {
      throw new ResourceNotFoundException("No games found");
    }

    return checkIfEmpty(games);
  }

  public Set<UserGameDto> getUserGamesByGenre(String username, String genre) {
    Set<UserGameDto> games = userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .filter(userGame -> userGame.getGame().getGenre().getName().equals(genre))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    return checkIfEmpty(games);
  };

  public Set<UserGameDto> getUserGamesByStatus(String username, String status) {
    Set<UserGameDto> games = userGameRepository.findAll().stream()
        .filter(userGame -> userGame.getUser().getUsername().equals(username))
        .filter(userGame -> userGame.getStatus().getName().equals(status))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    return checkIfEmpty(games);
  }

  private Set<UserGameDto> checkIfEmpty(Set<UserGameDto> games) {
    if (games.isEmpty()) {
      throw new ResourceNotFoundException("No games found for the given criteria.");
    }

    return games;
}
}
