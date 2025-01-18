package se.gamearc.userGame;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.gamearc.exception.ResourceNotFoundException;
import se.gamearc.game.dto.GameDto;
import se.gamearc.game.entity.Game;
import se.gamearc.game.entity.Genre;
import se.gamearc.igdb.IGDBGameDto;
import se.gamearc.user.entity.UserEntity;
import se.gamearc.user.service.JWTService;
import se.gamearc.userGame.dto.UserGameDto;
import se.gamearc.userGame.dto.UserGameUpdateDto;
import se.gamearc.userGame.dto.UserIGDBGameDto;
import se.gamearc.userGame.entity.Status;
import se.gamearc.userGame.entity.UserGame;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserGameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserGameControllerTest {

  @MockBean
  private UserGameService userGameService;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private JWTService jwtService;

  @FunctionalInterface
  private interface ServiceMockConfigurer {
    void configure();
  }

  private static final Set<UserGameDto> userGames = new HashSet<>();

  @BeforeAll
  static void setUp() {
    Set<String> genres = new HashSet<>();
    genres.add("genre");

    userGames.add(
        new UserGameDto(
            1,
            new GameDto(
                "game",
                "description",
                "coverUrl",
                1,
                genres),
            "status",
            "comment",
            1, 1, 1));
  }

  private void fetchResourceAndAssertWithResource(String url, ServiceMockConfigurer configurer) throws Exception {

    configurer.configure();

    mvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].game.title").value("game"))
        .andExpect(jsonPath("$[0].game.description").value("description"))
        .andExpect(jsonPath("$[0].game.cover").value("coverUrl"))
        .andExpect(jsonPath("$[0].game.totalRating").value(1))
        .andExpect(jsonPath("$[0].game.genres[0]").value("genre"))
        .andExpect(jsonPath("$[0].comment").value("comment"))
        .andExpect(jsonPath("$[0].status").value("status"))
        .andExpect(jsonPath("$[0].storyRating").value(1))
        .andExpect(jsonPath("$[0].gameplayRating").value(1))
        .andExpect(jsonPath("$[0].graphicsRating").value(1));
  }

  private void fetchResourceAndAssertResourceNotFound(String url, ServiceMockConfigurer configurer) throws Exception {
    configurer.configure();

    mvc.perform(get(url))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("No games found"))
        .andExpect(jsonPath("$.details").value("uri=" + url));
  }

  @Test
  @DisplayName("Fetching all users games should return a json object and status code ok if not empty")
  void fetchingAllUsersGamesShouldReturnAJsonObjectAndStatusCodeOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/1",
        () -> Mockito.when(userGameService.getAllUserGames(1))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching all of users game should return error response when no resource found")
  void fetchingAllOfUsersGameShouldReturnErrorResponseWhenNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/1",
        () -> Mockito.when(userGameService.getAllUserGames(1))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Fetching users games by genre should return a json object and return status ok if not empty")
  void fetchingUsersGamesByGenreShouldReturnAJsonObjectAndReturnStatusOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/1/genre/genreName",
        () -> Mockito.when(userGameService.getUserGamesByGenre(1, "genreName"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching users game by genre should return error response if no resource found")
  void fetchingUsersGameByGenreShouldReturnErrorResponseIfNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/1/genre/genreName",
        () -> Mockito.when(userGameService.getUserGamesByGenre(1, "genreName"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Fetching a users games by title should return json object and status ok if not empty")
  void fetchingAUsersGamesByTitleShouldReturnJsonObjectAndStatusOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/1/title/gameTitle",
        () -> Mockito.when(userGameService.getUserGamesByTitle(1, "gameTitle"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching users game by game title should return error if no resource found")
  void fetchingUsersGameByGameTitleShouldReturnErrorIfNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/1/title/gameTitle",
        () -> Mockito.when(userGameService.getUserGamesByTitle(1, "gameTitle"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Fetching a users games by status should return json object and status ok if not empty")
  void fetchingAUsersGamesByStatusShouldReturnJsonObjectAndStatusOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/1/status/statusName",
        () -> Mockito.when(userGameService.getUserGamesByStatus(1, "statusName"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching users games by status should return error response if no resource found")
  void fetchingUsersGamesByStatusShouldReturnErrorResponseIfNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/1/status/statusName",
        () -> Mockito.when(userGameService.getUserGamesByStatus(1, "statusName"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Making a Post request to add a new user game should return 201 created if successful")
  void makingAPostRequestToAddANewUserGameShouldReturn201CreatedIfSuccessful() throws Exception {

    UserIGDBGameDto userIGDBGameDto = new UserIGDBGameDto(
        new IGDBGameDto("title", "desc", "cover", List.of("genre")),
        "status",
        "comment",
        1, 1, 1
    );

    UserGame userGame = getUserGame();

    Mockito.when(userGameService.saveUserGame(1, userIGDBGameDto)).thenReturn(userGame);

    mvc.perform(post("/api/user-games/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userIGDBGameDto)))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/api/user-games/1/title"));
  }

  @Test
  @DisplayName("Making a POST request to add a new user game should return status 404 not found if user or status not found")
  void makingAPostRequestToAddANewUserGameShouldReturnStatus404NotFoundIfUserOrStatusNotFound() throws Exception {
    UserIGDBGameDto userIGDBGameDto = new UserIGDBGameDto(
        new IGDBGameDto("title", "desc", "cover", List.of("genre")),
        "status",
        "comment",
        1, 1, 1
    );

    Mockito.when(userGameService.saveUserGame(1, userIGDBGameDto))
        .thenThrow(new ResourceNotFoundException("User or status not found"));

    mvc.perform(post("/api/user-games/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userIGDBGameDto)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("User or status not found"))
        .andExpect(jsonPath("$.details").value("uri=/api/user-games/1"));
  }

  private static @NotNull UserGame getUserGame() {
    Set<Genre> genres = new HashSet<>();
    Genre genre = new Genre();
    genre.setName("genre");
    genres.add(genre);

    Game game = new Game();
    game.setTitle("title");
    game.setDescription("desc");
    game.setTotalRating(0);
    game.setGenres(genres);

    Status status = new Status();
    status.setName("status");

    UserGame userGame = new UserGame();
    userGame.setId(1);
    userGame.setUser(new UserEntity());
    userGame.setGame(game);
    userGame.setStatus(status);
    userGame.setComment("comment");
    userGame.setStoryRating(1);
    userGame.setGameplayRating(1);
    userGame.setGraphicsRating(1);
    return userGame;
  }

  @Test
  @DisplayName("Making a PUT request for editing a user game should return status 200 ok if successful")
  void makingAPutRequestForEditingAUserGameShouldReturnStatus200OkIfSuccessful() throws Exception {
    UserGameUpdateDto userGameUpdateDto = new UserGameUpdateDto(1, "status", "comment", 1, 1, 1);

    mvc.perform(put("/api/user-games/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userGameUpdateDto)))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Making a PUT request for editing a user game should return status 404 not found if some value in userGameDto is not found")
  void makingAPutRequestForEditingAUserGameShouldReturnStatus404NotFoundIfSomeValueInUserGameDtoIsNotFound() throws Exception {

    UserGameUpdateDto userGameUpdateDto = new UserGameUpdateDto(1, "status", "comment", 1, 1, 1);

    Mockito.doThrow(new ResourceNotFoundException("Some value in userGameDto is wrong or not in users library"))
        .when(userGameService).updateUserGame(1, userGameUpdateDto);

    mvc.perform(put("/api/user-games/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userGameUpdateDto)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Some value in userGameDto is wrong or not in users library"))
        .andExpect(jsonPath("$.details").value("uri=/api/user-games/1"));
  }

}
