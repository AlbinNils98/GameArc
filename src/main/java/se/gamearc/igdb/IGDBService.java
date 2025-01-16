package se.gamearc.igdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.gamearc.igdb.responses.IGDBGameResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IGDBService {

  private final String IGDB_URL = "https://api.igdb.com/v4/games/";

  @Value("${igdb.client-id}")
  private String IGDB_CLIENT_ID;
  @Value("${igdb.key}")
  private String IGDB_KEY;

  private RestTemplate createRestTemplate() {
    return new RestTemplateBuilder(rt -> rt
        .getInterceptors().add((request, body, execution) -> {
          request.getHeaders().add("Client-ID", IGDB_CLIENT_ID);
          request.getHeaders().add("Authorization", "Bearer " + IGDB_KEY);
          request.getHeaders().setContentType(MediaType.TEXT_PLAIN);
          return execution.execute(request, body);
        })).build();
  }

  public List<IGDBGameDto> fetchIGDBGamesByTitle(String title, Integer limit, Integer offset) {
    RestTemplate restTemplate = createRestTemplate();

    String requestBody = String.format("""
        fields name, summary, cover.url, genres.name;
        search "%s";
        where category = 0 & version_parent = null;
        limit 100;
        """, title);

    List<IGDBGameResponse> games = sendPostRequest(restTemplate, requestBody);

    if (games.isEmpty()) {
      return List.of();
    } else {
      List<IGDBGameDto> filteredGames = games.stream()
          .filter(igdbGameResponse -> igdbGameResponse.name().toLowerCase().contains(title.toLowerCase()))
          .map(IGDBGameDto::from)
          .collect(Collectors.toList());
      return getPaginatedResult(filteredGames, limit, offset);
    }
  }

  private List<IGDBGameDto> getPaginatedResult(List<IGDBGameDto> games, Integer limit, Integer offset) {
    int fromIndex = offset;
    int toIndex = Math.min(fromIndex + limit, games.size());

    if (fromIndex >= games.size()) {
      return Collections.emptyList();
    }

    return games.subList(fromIndex, toIndex);
  }

  public List<IGDBGameResponse> sendPostRequest(RestTemplate restTemplate, String requestBody) {
    HttpEntity<String> entity = new HttpEntity<>(requestBody);

    try {
      ResponseEntity<List<IGDBGameResponse>> response = restTemplate.exchange(
          IGDB_URL,
          HttpMethod.POST,
          entity,
          new ParameterizedTypeReference<>() {
          }
      );
      if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
        return response.getBody();
      } else {
        System.out.println("Failed with status code: " + response.getStatusCode());
      }
    } catch (RestClientException e) {
      System.out.println("Request error: " + e.getMessage());
    }
    return List.of();
  }
}

