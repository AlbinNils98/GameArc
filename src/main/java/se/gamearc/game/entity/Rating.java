package se.gamearc.game.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.gamearc.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "rating", schema = "gamearcdb")
public class Rating extends BaseEntity {

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_game_id", nullable = false)
  private UserGame userGame;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "rating_type_id", nullable = false)
  private RatingType ratingType;

  @NotNull
  @Column(name = "value", nullable = false)
  private Integer value;

}