package se.gamearc.game.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import se.gamearc.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "rating_type", schema = "gamearcdb")
public class RatingType extends BaseEntity {

  @Size(max = 255)
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

}