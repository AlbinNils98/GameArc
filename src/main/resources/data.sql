INSERT INTO genre (name)
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

INSERT INTO user_game (game_id, user_id, status_id)
VALUES (1, 1, 1), -- Player1 plays Skyrim
       (2, 1, 2), -- Player1 owns CoD: MW
       (1, 2, 3); -- Player2 wishes to play Skyrim

INSERT INTO rating (user_game_id, value)
VALUES (1, 8), -- Player1 rates Skyrim as 8
       (3, 9); -- Player2 rates Skyrim as 9

SELECT u.username,
       g.title       AS game_title,
       g.description AS game_description,
       g.cover       AS game_cover,
       ge.name       AS genre_name,
       s.name        AS status_name,
       r.value       AS user_rating
FROM user_game ug
         JOIN
     user u ON ug.user_id = u.id
         JOIN
     game g ON ug.game_id = g.id
         JOIN
     genre ge ON g.genre_id = ge.id
         JOIN
     status s ON ug.status_id = s.id
         LEFT JOIN
     rating r ON ug.id = r.user_game_id
WHERE u.id = 1; -- Replace '?' with the user's ID for the query