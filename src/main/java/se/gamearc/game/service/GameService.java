package se.gamearc.game.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.gamearc.exception.ResourceNotFoundException;
import se.gamearc.game.dto.GameDto;
import se.gamearc.game.entity.Game;
import se.gamearc.game.igdb.IGDBGameDto;
import se.gamearc.game.repository.GameRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {

  GenreService genreService;
  GameRepository gameRepository;

  public GameService(GameRepository gameRepository, GenreService genreService) {
    this.gameRepository = gameRepository;
    this.genreService = genreService;
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

  @Transactional
  public Game findOrCreateGame(IGDBGameDto igdbGame) {
    return gameRepository.findByTitle(igdbGame.title())
        .orElseGet(() -> {
          Game game = igdbGame.toGame();
          game.setGenres(genreService.findOrCreate(igdbGame.genres()));
          return gameRepository.save(game);
        });
  }
}
