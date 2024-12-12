package se.gamearc.game.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.game.entity.Game;

import java.util.Optional;

public interface GameRepository extends ListCrudRepository<Game, Integer> {
  Optional<Game> findByTitle(String title);
}
