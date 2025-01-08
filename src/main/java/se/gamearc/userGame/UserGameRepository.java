package se.gamearc.userGame;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.userGame.entity.UserGame;

public interface UserGameRepository extends ListCrudRepository <UserGame, Integer>{
}
