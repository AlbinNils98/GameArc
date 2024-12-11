package se.gamearc.game.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import se.gamearc.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "status", schema = "gamearcdb")
public class Status extends BaseEntity {

  @Size(max = 100)
  @Column(name = "name", length = 100)
  private String name;

  @OneToMany(mappedBy = "status")
  private Set<UserGame> userGames = new LinkedHashSet<>();

}