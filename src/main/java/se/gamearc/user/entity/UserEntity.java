package se.gamearc.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import se.gamearc.BaseEntity;
import se.gamearc.userGame.entity.UserGame;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "gamearcdb")
public class UserEntity extends BaseEntity  {

  @Size(max = 255)
  @NotNull
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Size(max = 60)
  @NotNull
  @JsonIgnore
  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @OneToMany(mappedBy = "user")
  private Set<UserGame> userGames = new LinkedHashSet<>();

}