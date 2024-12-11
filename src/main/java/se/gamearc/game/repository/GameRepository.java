package se.gamearc.game.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.game.entity.Game;

public interface GameRepository extends ListCrudRepository<Game, Integer> {
}
