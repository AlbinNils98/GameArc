package se.gamearc.userGame.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.userGame.entity.Status;

import java.util.Optional;

public interface StatusRepository extends ListCrudRepository<Status, Integer> {

  public Optional<Status> findByName(String name);
}
