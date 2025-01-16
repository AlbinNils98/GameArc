package se.gamearc.igdb.responses;

import java.util.ArrayList;
import java.util.List;

public record IGDBGameResponse(Integer id, String name, String summary, IGDBCoverResponse cover, List<IGDBGenreResponse> genres) {

  public List<String> getGenreNames(){
    List<String> genreNames = new ArrayList<>();
    if (genres != null){
      genres.forEach(genreResponse ->
      {
        if (genreResponse != null) {
          genreNames.add(genreResponse.name());
        }
      });
    }
    return genreNames;
  }

  public String getCover(){
    if (cover != null) {
      return cover.url();
    }else {
      return null;
    }
  }
}

