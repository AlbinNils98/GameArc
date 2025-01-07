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

  @Size(max = 255)
  @Lob
  @Column( name = "comment")
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

  private Integer calculateAverageRating() {
    return (storyRating + graphicsRating + gameplayRating) / 3;
  }


}