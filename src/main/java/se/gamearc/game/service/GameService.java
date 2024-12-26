package se.gamearc.game.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.gamearc.game.dto.GameDto;
import se.gamearc.game.entity.Game;
import se.gamearc.game.repository.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {

  GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public Set<GameDto> getAllGames() {
    return gameRepository.findAll().stream()
        .map(GameDto::from)
        .collect(Collectors.toSet());
  }

  public GameDto getGameById(int id) {
    return GameDto.from(gameRepository.findById(id).get());
  }

  public Set<GameDto> getGameByName(String name) {
    Set<GameDto> games = gameRepository.findAll().stream()
        .filter(game ->
            game.getTitle().toLowerCase().contains(name.toLowerCase()))
        .map(GameDto::from)
        .collect(Collectors.toSet());
    return games;
  }
}
