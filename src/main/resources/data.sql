SET FOREIGN_KEY_CHECKS = 0;


TRUNCATE TABLE genre;
TRUNCATE TABLE game;
TRUNCATE TABLE user;
TRUNCATE TABLE user_game;
TRUNCATE TABLE status;


SET FOREIGN_KEY_CHECKS = 1;

-- Adding more genres (keeping existing genres)
INSERT INTO genre (name)
VALUES ('RPG'),
       ('Shooter'),
       ('Adventure'),
       ('Puzzle'),
       ('Strategy'),
       ('Simulation'),
       ('Action'),
       ('Fighting'),
       ('Horror'),
       ('Sports');

-- Adding more statuses (keeping existing statuses)
INSERT INTO status (name)
VALUES ('playing'),
       ('owned'),
       ('wishlist'),
       ('completed'),
       ('abandoned'),
       ('on hold');

-- Adding more games (keeping existing games)
INSERT INTO game (title, description, genre_id)
VALUES ('Elder Scrolls V: Skyrim', 'Epic fantasy RPG', 1),
       ('Call of Duty: Modern Warfare', 'Tactical shooter', 2),
       ('The Witcher 3: Wild Hunt', 'Open world RPG with deep story', 1),
       ('Half-Life: Alyx', 'VR first-person shooter', 2),
       ('Portal 2', 'First-person puzzle platformer', 4),
       ('Civilization VI', 'Turn-based strategy game', 3),
       ('Dark Souls III', 'Challenging action RPG', 1),
       ('Overwatch', 'Multiplayer first-person shooter', 2),
       ('Minecraft', 'Sandbox construction game', 4),
       ('Grand Theft Auto V', 'Open-world action-adventure', 1),
       ('Mortal Kombat 11', 'Fighting game with brutal combat', 8),
       ('Resident Evil 2', 'Survival horror game', 9),
       ('FIFA 23', 'Football simulation game', 10),
       ('The Legend of Zelda: Breath of the Wild', 'Open-world action-adventure', 1),
       ('Hollow Knight', 'Action-adventure with exploration and combat', 7);

-- Adding more users (keeping existing users)
INSERT INTO user (username, password, email)
VALUES ('player1', 'password123', 'player1@example.com'),
       ('player2', 'password456', 'player2@example.com'),
       ('player3', 'password789', 'player3@example.com'),
       ('player4', 'password101', 'player4@example.com'),
       ('player5', 'password112', 'player5@example.com');

-- Adding more user_game entries (making sure each user has more games in their library)
INSERT INTO user_game (game_id, user_id, status_id, comment ,story_rating, graphics_rating, gameplay_rating)
VALUES (1, 1, 1, 'aj lajk gem', 6, 7, 8), -- Player1 plays Skyrim
       (2, 1, 2, 'aj lajk gem', 7, 6, 7), -- Player1 owns CoD: MW
       (1, 2, 3, 'aj lajk gem', 8, 7, 9), -- Player2 wishes to play Skyrim
       (3, 1, 1, 'A fantastic game', 9, 9, 10), -- Player1 plays Witcher 3
       (4, 2, 2, 'Great strategy game', 8, 7, 8), -- Player2 owns Civ VI
       (3, 2, 3, 'Looking forward to this one', 7, 6, 7), -- Player2 wishes to play Witcher 3
       (2, 3, 1, 'Loved the VR experience', 10, 9, 10), -- Player3 plays Half-Life: Alyx
       (4, 4, 1, 'Great for long sessions', 7, 8, 7), -- Player4 plays Civ VI
       (7, 1, 2, 'I died a lot but still great!', 8, 9, 9), -- Player1 owns Dark Souls III
       (8, 2, 1, 'Addictive multiplayer experience', 7, 8, 8), -- Player2 plays Overwatch
       (5, 3, 1, 'Solving puzzles for hours', 8, 8, 8), -- Player3 plays Portal 2
       (6, 4, 2, 'Fun strategy but requires patience', 7, 7, 7), -- Player4 owns Civ VI
       (9, 3, 1, 'The endless possibilities!', 10, 10, 10), -- Player3 plays Minecraft
       (9, 2, 2, 'Building and creating non-stop', 8, 9, 9), -- Player2 owns Minecraft
       (10, 1, 2, 'Can never get enough of this game', 9, 10, 9), -- Player1 owns GTA V
       (11, 1, 1, 'Brutal fights, great game', 8, 9, 9), -- Player1 plays Mortal Kombat 11
       (12, 2, 3, 'Scary but exciting!', 9, 8, 9), -- Player2 wishes to play Resident Evil 2
       (13, 3, 1, 'Football game of the year!', 9, 10, 9), -- Player3 plays FIFA 23
       (13, 4, 1, 'Great multiplayer experience', 8, 9, 8), -- Player4 plays FIFA 23
       (14, 5, 1, 'Amazing open world', 10, 10, 10), -- Player5 plays Zelda: BotW
       (15, 5, 3, 'Challenging but rewarding', 9, 9, 9), -- Player5 wishes to play Hollow Knight
       (10, 2, 1, 'I’ve completed it 3 times!', 10, 9, 10), -- Player2 plays GTA V
       (6, 3, 2, 'Very immersive, love the story', 9, 8, 8); -- Player3 owns Civ VI