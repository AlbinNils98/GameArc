INSERT INTO genre (name)
VALUES ('RPG'),
       ('Shooter');

INSERT INTO status (name)
VALUES ('playing'),
       ('owned'),
       ('wishlist');

INSERT INTO rating_type (name)
VALUES
    ('gameplay'),
    ('graphics'),
    ('story');

INSERT INTO game (title, description, genre_id)
VALUES ('Elder Scrolls V: Skyrim', 'Epic fantasy RPG', 1),
       ('Call of Duty: Modern Warfare', 'Tactical shooter', 2);

INSERT INTO user (username, password, email)
VALUES ('player1', 'password123', 'player1@example.com'),
       ('player2', 'password456', 'player2@example.com');

INSERT INTO user_game (game_id, user_id, status_id)
VALUES (1, 1, 1), -- Player1 plays Skyrim
       (2, 1, 2), -- Player1 owns CoD: MW
       (1, 2, 3); -- Player2 wishes to play Skyrim

INSERT INTO rating (user_game_id, rating_type_id, value)
VALUES
    (1, 1, 8), -- Player1 rates Skyrim's gameplay as 8
    (1, 2, 7), -- Player1 rates Skyrim's graphics as 7
    (2, 1, 9), -- Player2 rates Skyrim's gameplay as 9
    (2, 3, 8); -- Player2 rates Skyrim's story as 8
