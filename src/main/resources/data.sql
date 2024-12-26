SET FOREIGN_KEY_CHECKS = 0;


TRUNCATE TABLE genre;
TRUNCATE TABLE game;
TRUNCATE TABLE user;
TRUNCATE TABLE user_game;
TRUNCATE TABLE status;


SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO
    genre (name)
VALUES ('RPG'),
       ('Shooter');

INSERT INTO status (name)
VALUES ('playing'),
       ('owned'),
       ('wishlist');

INSERT INTO game (title, description, genre_id)
VALUES ('Elder Scrolls V: Skyrim', 'Epic fantasy RPG', 1),
       ('Call of Duty: Modern Warfare', 'Tactical shooter', 2);

INSERT INTO user (username, password, email)
VALUES ('player1', 'password123', 'player1@example.com'),
       ('player2', 'password456', 'player2@example.com');

INSERT INTO user_game (game_id, user_id, status_id, comment ,story_rating, graphics_rating, gameplay_rating)
VALUES (1, 1, 1, 'aj lajk gem', 1, 1, 1), -- Player1 plays Skyrim
       (2, 1, 2, 'aj lajk gem', 1, 1, 1), -- Player1 owns CoD: MW
       (1, 2, 3, 'aj lajk gem', 1, 1, 1); -- Player2 wishes to play Skyrim


