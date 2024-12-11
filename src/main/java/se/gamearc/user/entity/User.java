package se.gamearc.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import se.gamearc.BaseEntity;
import se.gamearc.game.entity.UserGame;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "gamearcdb")
public class User extends BaseEntity  {

  @Size(max = 255)
  @NotNull
  @Column(name = "username", nullable = false)
  private String username;

  @Size(max = 60)
  @NotNull
  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @Size(max = 100)
  @NotNull
  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @OneToMany(mappedBy = "user")
  private Set<UserGame> userGames = new LinkedHashSet<>();

}