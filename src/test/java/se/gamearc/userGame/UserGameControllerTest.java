package se.gamearc.userGame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import se.gamearc.exception.ResourceNotFoundException;
import se.gamearc.game.dto.GameDto;
import se.gamearc.game.dto.GenreDto;


import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserGameController.class)
public class UserGameControllerTest {

  @MockBean
  private UserGameService userGameService;

  @Autowired
  private MockMvc mvc;

  @FunctionalInterface
  private interface ServiceMockConfigurer {
    void configure();
  }

  private static final Set<UserGameDto> userGames = new HashSet<>();

  @BeforeAll
  static void setUp() {
    userGames.add(
        new UserGameDto(
            new GameDto("game",
                "description",
                "coverUrl",
                1,
                new GenreDto(
                    "genre")),
            "status",
            "comment",
            1, 1, 1));
  }

  private void fetchResourceAndAssertWithResource(String url, ServiceMockConfigurer configurer) throws Exception {

    configurer.configure();

    mvc.perform(get(url))
        .andExpect(status().isOk())
        .andDo(result -> {
          System.out.println(result.getResponse().getContentAsString());
        })
        .andExpect(jsonPath("$[0].game.title").value("game"))
        .andExpect(jsonPath("$[0].game.description").value("description"))
        .andExpect(jsonPath("$[0].game.cover").value("coverUrl"))
        .andExpect(jsonPath("$[0].game.totalRating").value(1))
        .andExpect(jsonPath("$[0].game.genre.name").value("genre"))
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
        "/api/user-games/username",
        () -> Mockito.when(userGameService.getAllUserGames("username"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching all of users game should return error response when no resource found")
  void fetchingAllOfUsersGameShouldReturnErrorResponseWhenNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/username",
        () -> Mockito.when(userGameService.getAllUserGames("username"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Fetching users games by genre should return a json object and return status ok if not empty")
  void fetchingUsersGamesByGenreShouldReturnAJsonObjectAndReturnStatusOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/username/genre/genreName",
        () -> Mockito.when(userGameService.getUserGamesByGenre("username", "genreName"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching users game by genre should return error response if no resource found")
  void fetchingUsersGameByGenreShouldReturnErrorResponseIfNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/username/genre/genreName",
        () -> Mockito.when(userGameService.getUserGamesByGenre("username", "genreName"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Fetching a users games by title should return json object and status ok if not empty")
  void fetchingAUsersGamesByTitleShouldReturnJsonObjectAndStatusOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/username/title/gameTitle",
        () -> Mockito.when(userGameService.getUserGamesByTitle("username", "gameTitle"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching users game by game title should return error if no resource found")
  void fetchingUsersGameByGameTitleShouldReturnErrorIfNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/username/title/gameTitle",
        () -> Mockito.when(userGameService.getUserGamesByTitle("username", "gameTitle"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

  @Test
  @DisplayName("Fetching a users games by status should return json object and status ok if not empty")
  void fetchingAUsersGamesByStatusShouldReturnJsonObjectAndStatusOkIfNotEmpty() throws Exception {
    fetchResourceAndAssertWithResource(
        "/api/user-games/username/status/statusName",
        () -> Mockito.when(userGameService.getUserGamesByStatus("username", "statusName"))
            .thenReturn(userGames)
    );
  }

  @Test
  @DisplayName("Fetching users games by status should return error response if no resource found")
  void fetchingUsersGamesByStatusShouldReturnErrorResponseIfNoResourceFound() throws Exception {
    fetchResourceAndAssertResourceNotFound(
        "/api/user-games/username/status/statusName",
        () -> Mockito.when(userGameService.getUserGamesByStatus("username", "statusName"))
            .thenThrow(new ResourceNotFoundException("No games found"))
    );
  }

}
