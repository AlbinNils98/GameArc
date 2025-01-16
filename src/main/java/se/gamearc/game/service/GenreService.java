package se.gamearc.game.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.gamearc.game.entity.Genre;
import se.gamearc.game.repository.GenreRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {

  GenreRepository genreRepository;

  public GenreService(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Transactional
  public Set<Genre> findOrCreateGenre(List<String> genres) {
    Set<Genre> genresSet = new HashSet<>();

    genres.forEach(genreName -> {
      genreRepository.findByName(genreName)
          .map(genresSet::add)
          .orElseGet(() -> {
                Genre genre = new Genre();
                genre.setName(genreName);
                return genresSet.add(genreRepository.save(genre));
              });
    });

    return genresSet;
  }
}
