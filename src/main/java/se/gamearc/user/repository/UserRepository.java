package se.gamearc.user.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {
  UserEntity findByUsername(String username);
}
