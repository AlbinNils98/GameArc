package se.gamearc.game.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.gamearc.game.entity.Game;
import se.gamearc.igdb.IGDBGameDto;
import se.gamearc.game.repository.GameRepository;

@Service
public class GameService {

  GenreService genreService;
  GameRepository gameRepository;

  public GameService(GameRepository gameRepository, GenreService genreService) {
    this.gameRepository = gameRepository;
    this.genreService = genreService;
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
