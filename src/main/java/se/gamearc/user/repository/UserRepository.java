package se.gamearc.user.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.user.entity.User;

public interface UserRepository extends ListCrudRepository<User, Integer> {
  User findByUsername(String username);
}
