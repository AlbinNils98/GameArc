package se.gamearc.game.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.game.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends ListCrudRepository<Genre, Integer> {
  public Optional<Genre> findByName(String name);
}
