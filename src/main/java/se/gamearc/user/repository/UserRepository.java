package se.gamearc.user.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.gamearc.user.entity.User;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer> {
}
