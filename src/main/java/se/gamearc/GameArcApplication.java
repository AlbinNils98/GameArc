package se.gamearc;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameArcApplication {

  public static void main(String[] args) {
    SpringApplication.run(GameArcApplication.class, args);
  }

  @PostConstruct
  public void afterStartup() {
    System.out.println("✅ GameArc is up and running.");
  }
}
