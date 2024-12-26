package se.gamearc.userGame;

import org.springframework.stereotype.Service;

@Service
public class UserGameService {

  UserGameRepository userGameRepository;

  public UserGameService(UserGameRepository userGameRepository) {
    this.userGameRepository = userGameRepository;
  }
}
