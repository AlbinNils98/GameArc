package se.gamearc.userGame;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.gamearc.game.entity.Game;
import se.gamearc.game.entity.Genre;
import se.gamearc.user.entity.UserEntity;
import se.gamearc.userGame.entity.Status;
import se.gamearc.userGame.entity.UserGame;

import java.util.HashSet;
import java.util.Set;


public class UserGameValidationTest {

  private final Validator validator;
  private static final UserEntity user = new UserEntity();
  private static final Game game = new Game();
  private static final UserGame userGame = new UserGame();

  public UserGameValidationTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @BeforeEach
  public void setup() {

    //Setting up valid UserGame object

    user.setUsername("name");
    user.setPassword("password");

    Set<Genre> genreSet = new HashSet<>();
    Genre genre = new Genre();
    genre.setName("genre");
    genreSet.add(genre);

    game.setTitle("title");
    game.setDescription("description");
    game.setCover("cover");
    game.setGenres(genreSet);

    Status status = new Status();
    status.setName("status");

    userGame.setUser(user);
    userGame.setGame(game);
    userGame.setComment("comment");
    userGame.setGameplayRating(1);
    userGame.setGraphicsRating(1);
    userGame.setStoryRating(1);
    userGame.setStatus(status);
  }

  @Test
  @DisplayName("UserGame should have a status attached to it")
  void userGameShouldHaveAStatusAttachedToIt() {
    Set<ConstraintViolation<UserGame>> noViolations = validator.validate(userGame);
    assertTrue(noViolations.isEmpty(), "There should be no violation when there is a valid Status object");

    userGame.setStatus(null);

    Set<ConstraintViolation<UserGame>> violations = validator.validate(userGame);
    assertFalse(violations.isEmpty(), "There should be violations from not valid status object");
    assertEquals(1, violations.size(), "There should be one violations from not valid status object");
  }

  @Test
  @DisplayName("Ratings should be between one and ten")
  void ratingsShouldBeBetweenOneAndTen() {
    Set<ConstraintViolation<UserGame>> noViolations = validator.validate(userGame);
    assertTrue(noViolations.isEmpty(), "There should be no violation when between one and ten");

    userGame.setStoryRating(0);
    userGame.setGameplayRating(0);
    userGame.setGraphicsRating(0);
    Set<ConstraintViolation<UserGame>> violations = validator.validate(userGame);
    assertFalse(violations.isEmpty(), "There should be violations from not valid ratings");
    assertEquals(3, violations.size(), "There should be one violation for each rating when not between one and ten");

  }

  @Test
  @DisplayName("UserGame should contain a valid User object")
  void userGameShouldContainAValidUserObject() {
    Set<ConstraintViolation<UserGame>> noViolations = validator.validate(userGame);
    assertTrue(noViolations.isEmpty(), "There should be no violation when not exceeding max char");

    userGame.setUser(null);

    Set<ConstraintViolation<UserGame>> violations = validator.validate(userGame);
    assertFalse(violations.isEmpty(), "There should be a violation when not valid User object");
    assertEquals(1, violations.size(), "There should be one violation when not valid User object");
  }

  @Test
  @DisplayName("UserGame should contain a valid game object")
  void userGameShouldContainAValidGameObject() {
    Set<ConstraintViolation<UserGame>> noViolations = validator.validate(userGame);
    assertTrue(noViolations.isEmpty(), "There should be no violation when not exceeding max char");

    userGame.setGame(null);

    Set<ConstraintViolation<UserGame>> violations = validator.validate(userGame);
    assertFalse(violations.isEmpty(), "There should be a violation when not valid Game object");
    assertEquals(1, violations.size(), "There should be one violation when not valid Game object");
  }
}
