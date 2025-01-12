package se.gamearc.igdb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import se.gamearc.game.igdb.IGDBGameDto;
import se.gamearc.game.igdb.IGDBService;
import se.gamearc.game.igdb.responses.IGDBCoverResponse;
import se.gamearc.game.igdb.responses.IGDBGameResponse;
import se.gamearc.game.igdb.responses.IGDBGenreResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IGDBServiceTest {

  @Spy
  private IGDBService service;

  private static List<IGDBGameResponse> games;

  @BeforeAll
  static void setUp() {
    games = new ArrayList<>();

    IGDBGameResponse firstGameToShow = new IGDBGameResponse(
        1, "Call of duty", "First set of games",
        new IGDBCoverResponse(1, "url"),
        List.of(new IGDBGenreResponse(1, "Shooter"))
    );

    IGDBGameResponse secondGameToShow = new IGDBGameResponse(
        2, "Call of duty", "Second set of games",
        new IGDBCoverResponse(1, "url"),
        List.of(new IGDBGenreResponse(1, "Shooter"))
    );

    IGDBGameResponse gameNotToShow = new IGDBGameResponse(
        3, "Not correct title", "Not call of duty!",
        new IGDBCoverResponse(1, "url"),
        List.of(new IGDBGenreResponse(1, "Shooter"))
    );

    for (int i = 0; i < 10; i++) {
      games.add(firstGameToShow);
    }

    games.add(gameNotToShow);

    for (int i = 0; i < 10; i++) {
      games.add(secondGameToShow);
    }
  }

  @Test
  @DisplayName("Should fetch games by title and only return list with size of 10 with starting position 0")
  void shouldFetchGamesByTitleAndOnlyReturnListWithSizeOf10WithStartingPosition0() {

    Mockito.doReturn(games).when(service).sendPostRequest(Mockito.any(), Mockito.anyString());

    List<IGDBGameDto> result = service.fetchIGDBGamesByTitle("Call of Duty", 10, 0);

    assertAll(
        () -> assertEquals(10, result.size()),
        () -> result.forEach(game ->
            assertAll(
                () -> assertEquals("call of duty", game.name().toLowerCase()),
                () -> assertEquals("First set of games", game.summary())
            )
        )
    );
  }

  @Test
  @DisplayName("Should fetch next 10 games if offset is 10 but only with correct title")
  void shouldFetchNext10GamesIfOffsetIs10ButOnlyWithCorrectTitle() {
    Mockito.doReturn(games).when(service).sendPostRequest(Mockito.any(), Mockito.anyString());

    List<IGDBGameDto> result = service.fetchIGDBGamesByTitle("Call of Duty", 10, 10);

    assertAll(
        () -> assertEquals(10, result.size()),
        () -> result.forEach(game ->
            assertAll(
                () -> assertEquals("call of duty", game.name().toLowerCase()),
                () -> assertEquals("Second set of games", game.summary())
            )
        )
    );
  }

}