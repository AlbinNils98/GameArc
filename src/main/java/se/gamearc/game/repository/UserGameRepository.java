package se.gamearc.game.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.game.entity.UserGame;

public interface UserGameRepository extends ListCrudRepository <UserGame, Integer>{
}
