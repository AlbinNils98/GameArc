package se.gamearc.service;

import org.springframework.stereotype.Service;
import se.gamearc.game.repository.UserGameRepository;

@Service
public class UserGameService {

  UserGameRepository userGameRepository;

  public UserGameService(UserGameRepository userGameRepository) {
    this.userGameRepository = userGameRepository;
  }
}
