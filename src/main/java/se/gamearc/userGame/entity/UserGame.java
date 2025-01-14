package se.gamearc.userGame.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.gamearc.BaseEntity;
import se.gamearc.game.entity.Game;
import se.gamearc.user.entity.User;
import se.gamearc.userGame.api.Ratings;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_game", schema = "gamearcdb")
public class UserGame extends BaseEntity {

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "game_id", nullable = false)
  private Game game;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "status_id", nullable = false)
  private Status status;

  @Lob
  @Column( name = "comment", columnDefinition = "TEXT")
  private String comment;

  @Max(10)
  @Min(1)
  @Column(name = "story_rating")
  private Integer storyRating;

  @Max(10)
  @Min(1)
  @Column(name = "graphics_rating")
  private Integer graphicsRating;

  @Max(10)
  @Min(1)
  @Column(name = "gameplay_rating")
  private Integer gameplayRating;

  public void checkThenSetRatings(Ratings ratings) {
    List<Integer> ratingsList = List.of(
        ratings.storyRating(),
        ratings.graphicsRating(),
        ratings.gameplayRating()
    );

    ratingsList.forEach(rating -> {
      if (rating < 1 || rating > 10) {
        throw new IllegalArgumentException("Rating must be between 1 and 10");
      }
    });

    this.storyRating = ratings.storyRating();
    this.graphicsRating = ratings.graphicsRating();
    this.gameplayRating = ratings.gameplayRating();
  }

}