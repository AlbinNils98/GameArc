
# SET FOREIGN_KEY_CHECKS = 0;

# TRUNCATE TABLE genre;
# TRUNCATE TABLE game;
# TRUNCATE TABLE users;
# TRUNCATE TABLE user_game;
# TRUNCATE TABLE status;
# TRUNCATE TABLE game_genre;

# SET FOREIGN_KEY_CHECKS = 1;

# INSERT INTO status (name)
# VALUES ('playing'),
#       ('owned'),
#       ('wishlist'),
#       ('completed'),
#       ('abandoned'),
#       ('on hold');
# INSERT INTO genre (name)
# VALUES ('RPG'),
#        ('Shooter'),
#        ('Adventure'),
#        ('Puzzle'),
#        ('Strategy'),
#        ('Simulation'),
#        ('Action'),
#        ('Fighting'),
#        ('Horror'),
#        ('Sports');
#

#
# INSERT INTO game (title, description, cover, total_rating)
# VALUES ('Elder Scrolls V: Skyrim', 'Epic fantasy RPG', 'cover_skyrim.jpg', 9),
#        ('Call of Duty: Modern Warfare', 'Tactical shooter', 'cover_cod_mw.jpg', 8),
#        ('The Witcher 3: Wild Hunt', 'Open world RPG with deep story', 'cover_witcher3.jpg', 10),
#        ('Half-Life: Alyx', 'VR first-person shooter', 'cover_hl_alyx.jpg', 9),
#        ('Portal 2', 'First-person puzzle platformer', 'cover_portal2.jpg', 9),
#        ('Civilization VI', 'Turn-based strategy game', 'cover_civ6.jpg', 8),
#        ('Dark Souls III', 'Challenging action RPG', 'cover_ds3.jpg', 9),
#        ('Overwatch', 'Multiplayer first-person shooter', 'cover_overwatch.jpg', 8),
#        ('Minecraft', 'Sandbox construction game', 'cover_minecraft.jpg', 10),
#        ('Grand Theft Auto V', 'Open-world action-adventure', 'cover_gta5.jpg', 10),
#        ('Mortal Kombat 11', 'Fighting game with brutal combat', 'cover_mk11.jpg', 9),
#        ('Resident Evil 2', 'Survival horror game', 'cover_re2.jpg', 9),
#        ('FIFA 23', 'Football simulation game', 'cover_fifa23.jpg', 8),
#        ('The Legend of Zelda: Breath of the Wild', 'Open-world action-adventure', 'cover_botw.jpg', 10),
#        ('Hollow Knight', 'Action-adventure with exploration and combat', 'cover_hollow_knight.jpg', 9);
#
#
# INSERT INTO game_genre (game_id, genre_id)
# VALUES (1, 1),  -- Skyrim is an RPG
#        (1, 3),  -- Skyrim also has Adventure elements
#        (2, 2),  -- CoD: Modern Warfare is a Shooter
#        (3, 1),  -- Witcher 3 is an RPG
#        (3, 3),  -- Witcher 3 also has Adventure elements
#        (4, 2),  -- Half-Life: Alyx is a Shooter
#        (5, 4),  -- Portal 2 is a Puzzle game
#        (6, 5),  -- Civilization VI is a Strategy game
#        (7, 1),  -- Dark Souls III is an RPG
#        (7, 7),  -- Dark Souls III has Action elements
#        (8, 2),  -- Overwatch is a Shooter
#        (9, 4),  -- Minecraft is a Puzzle game
#        (9, 6),  -- Minecraft is also a Simulation game
#        (10, 3), -- GTA V is an Adventure game
#        (10, 7), -- GTA V has Action elements
#        (11, 8), -- Mortal Kombat 11 is a Fighting game
#        (12, 9), -- Resident Evil 2 is a Horror game
#        (13, 10),-- FIFA 23 is a Sports game
#        (14, 3), -- Zelda: BotW is an Adventure game
#        (14, 7), -- Zelda: BotW has Action elements
#        (15, 3), -- Hollow Knight is an Adventure game
#        (15, 7); -- Hollow Knight has Action elements


# INSERT INTO user_game (game_id, user_id, status_id, comment, story_rating, graphics_rating, gameplay_rating)
# VALUES (1, 1, 1, 'aj lajk gem', 6, 7, 8),
#        (2, 1, 2, 'aj lajk gem', 7, 6, 7),
#        (1, 2, 3, 'aj lajk gem', 8, 7, 9),
#        (3, 1, 1, 'A fantastic game', 9, 9, 10),
#        (4, 2, 2, 'Great strategy game', 8, 7, 8),
#        (3, 2, 3, 'Looking forward to this one', 7, 6, 7),
#        (2, 3, 1, 'Loved the VR experience', 10, 9, 10),
#        (4, 4, 1, 'Great for long sessions', 7, 8, 7),
#        (7, 1, 2, 'I died a lot but still great!', 8, 9, 9),
#        (8, 2, 1, 'Addictive multiplayer experience', 7, 8, 8),
#        (5, 3, 1, 'Solving puzzles for hours', 8, 8, 8),
#        (6, 4, 2, 'Fun strategy but requires patience', 7, 7, 7),
#        (9, 3, 1, 'The endless possibilities!', 10, 10, 10),
#        (9, 2, 2, 'Building and creating non-stop', 8, 9, 9),
#        (10, 1, 2, 'Can never get enough of this game', 9, 10, 9),
#        (11, 1, 1, 'Brutal fights, great game', 8, 9, 9),
#        (12, 2, 3, 'Scary but exciting!', 9, 8, 9),
#        (13, 3, 1, 'Football game of the year!', 9, 10, 9),
#        (13, 4, 1, 'Great multiplayer experience', 8, 9, 8),
#        (14, 5, 1, 'Amazing open world', 10, 10, 10),
#        (15, 5, 3, 'Challenging but rewarding', 9, 9, 9),
#        (10, 2, 1, 'I’ve completed it 3 times!', 10, 9, 10),
#        (6, 3, 2, 'Very immersive, love the story', 9, 8, 8);
