package se.gamearc.game.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.gamearc.BaseEntity;
import se.gamearc.userGame.entity.UserGame;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "game", schema = "gamearcdb")
public class Game extends BaseEntity {

  @Size(max = 255)
  @NotNull
  @Column(name = "title", nullable = false)
  private String title;

  @Lob
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Size(max = 500)
  @Column(name = "cover", length = 500)
  private String cover;

  @Column(name = "total_rating")
  private Integer totalRating;

  @ManyToMany
  @JoinTable(
      name = "game_genre",
      joinColumns = @JoinColumn(name = "game_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private Set<Genre> genres = new LinkedHashSet<>();

  @OneToMany(mappedBy = "game")
  private Set<UserGame> userGames = new LinkedHashSet<>();

}