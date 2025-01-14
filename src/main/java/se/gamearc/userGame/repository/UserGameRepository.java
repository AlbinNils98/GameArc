package se.gamearc.userGame.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.userGame.entity.UserGame;

import java.util.List;
import java.util.Optional;

public interface UserGameRepository extends ListCrudRepository <UserGame, Integer>{

  public Optional<UserGame> findUserGameByGameTitleAndUserId(String gameTitle, Integer id);

  public List<UserGame> findUserGamesByUserId(Integer id);
}
