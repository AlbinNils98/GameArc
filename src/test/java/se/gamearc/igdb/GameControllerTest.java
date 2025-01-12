package se.gamearc.igdb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import se.gamearc.game.GameController;
import se.gamearc.game.igdb.IGDBGameDto;
import se.gamearc.game.igdb.IGDBService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
public class GameControllerTest {

  @MockBean
  IGDBService IgdbService;

  @Autowired
  private MockMvc mvc;

  @Test
  @DisplayName("Fetching games by title should return game dtos with http status ok")
  void fetchingGamesByTitleShouldReturnGameDtosWithHttpStatusOk() throws Exception {
    List<IGDBGameDto> mockedGames = List.of(
        new IGDBGameDto("Call of Duty", "A game about calls?", "url", List.of("Shooter")),
        new IGDBGameDto("Call of Duty 2", "A game about calls?", "url", List.of("Shooter")));

    Mockito.when(IgdbService.fetchIGDBGamesByTitle("call of duty", 10, 0)).thenReturn(mockedGames);

    mvc.perform(get("/api/games/name/call of duty"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;"))
        .andExpect(jsonPath("$.size()").value(mockedGames.size()))
        .andExpect(jsonPath("$[0].name").value("Call of Duty"))
        .andExpect(jsonPath("$[1].name").value("Call of Duty 2"));
  }
}
