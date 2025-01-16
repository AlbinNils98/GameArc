package se.gamearc.userGame;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.gamearc.exception.ResourceNotFoundException;
import se.gamearc.game.repository.GameRepository;
import se.gamearc.game.service.GameService;
import se.gamearc.game.service.GenreService;
import se.gamearc.user.repository.UserRepository;
import se.gamearc.userGame.api.Ratings;
import se.gamearc.userGame.dto.UserGameUpdateDto;
import se.gamearc.userGame.dto.UserIGDBGameDto;
import se.gamearc.userGame.dto.UserGameDto;
import se.gamearc.userGame.entity.UserGame;
import se.gamearc.userGame.repository.StatusRepository;
import se.gamearc.userGame.repository.UserGameRepository;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserGameService {

  GenreService genreService;
  GameService gameService;
  UserGameRepository userGameRepository;
  UserRepository userRepository;
  StatusRepository statusRepository;

  public UserGameService(UserGameRepository userGameRepository, UserRepository userRepository, StatusRepository statusRepository, GameRepository gameRepository, GenreService genreService, GameService gameService) {
    this.userGameRepository = userGameRepository;
    this.userRepository = userRepository;
    this.statusRepository = statusRepository;
    this.gameService = gameService;
    this.genreService = genreService;
  }

  public Set<UserGameDto> getAllUserGames(Integer userId) {
    Set<UserGameDto> games = userGameRepository.findUserGamesByUserId(userId).stream()
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    return checkIfEmpty(games);
  }

  public Set<UserGameDto> getUserGamesByTitle(Integer userId, String gameTitle) {
    Set<UserGameDto> games = userGameRepository.findUserGamesByUserId(userId).stream()
        .filter(userGame -> userGame.getGame().getTitle().toLowerCase().contains(gameTitle.toLowerCase()))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    return checkIfEmpty(games);
  }

  public Set<UserGameDto> getUserGamesByGenre(Integer userId, String genre) {
    Set<UserGameDto> games = userGameRepository.findUserGamesByUserId(userId).stream()
        .filter(userGame -> userGame.getGame().getGenres().stream()
            .anyMatch(gameGenre -> gameGenre.getName().toLowerCase().contains(genre.toLowerCase())))
        .map(UserGameDto::from)
        .collect(Collectors.toSet());

    return checkIfEmpty(games);
  };

  public Set<UserGameDto> getUserGamesByStatus(Integer userId, String status) {
    Set<UserGameDto> games = userGameRepository.findUserGamesByUserId(userId).stream()
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

@Transactional
  public UserGame saveUserGame(Integer userId, UserIGDBGameDto userIGDBGameDto) {
    UserGame userGame = new UserGame();
    userGame.setUser(userRepository.findById(userId)
        .orElseThrow(
            () -> new ResourceNotFoundException("No user found of user id: " + userId)));
    userGame.setGame(gameService.findOrCreateGame(userIGDBGameDto.game()));
    userGame.setComment(userIGDBGameDto.comment());
    userGame.setStatus(
        statusRepository.findByName(userIGDBGameDto.status())
            .orElseThrow(
                () -> new ResourceNotFoundException("Status not found"))
    );

    userGame.checkThenSetRatings(Ratings.fromUserIGDBGameDto(userIGDBGameDto));

    return userGameRepository.save(userGame);
  }

  public void updateUserGame(Integer userId, UserGameUpdateDto userGameUpdateDto) {
    UserGame userGame = userGameRepository.findUserGameByGameIdAndUserId(userGameUpdateDto.gameId(), userId)
        .orElseThrow(() -> new ResourceNotFoundException("No game found with user id: %d and/or game id: %d"
            .formatted(userId, userGameUpdateDto.gameId())));

    userGame.setStatus(statusRepository.findByName(userGameUpdateDto.status()).orElseThrow(
        () -> new ResourceNotFoundException("Status not found")
    ));
    
    userGame.checkThenSetRatings(Ratings.fromUserGameUpdateDto(userGameUpdateDto));
    if (!Objects.equals(userGame.getComment(), userGameUpdateDto.comment())) {
      userGame.setComment(userGameUpdateDto.comment());
      System.out.println("Comment updated to: " + userGameUpdateDto.comment());
      System.out.println("New comment is: " + userGame.getComment());
    }
    userGame.setComment(userGame.getComment());

    userGameRepository.save(userGame);
  }
}
