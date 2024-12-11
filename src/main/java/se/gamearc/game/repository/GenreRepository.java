package se.gamearc.game.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.game.entity.Genre;

public interface GenreRepository extends ListCrudRepository<Genre, Integer> {
}
