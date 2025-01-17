CREATE DATABASE IF NOT EXISTS gamearcdb;

CREATE TABLE IF NOT EXISTS genre(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS game(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    cover VARCHAR(500),
    total_rating INT CHECK ( total_rating BETWEEN 1 AND 10)
);

CREATE TABLE IF NOT EXISTS genre(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS game_genre(
    game_id INT,
    genre_id INT,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS status(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS user_game(
    id INT PRIMARY KEY AUTO_INCREMENT,
    game_id INT NOT NULL,
    user_id INT NOT NULL,
    status_id INT NOT NULL,
    comment TEXT,
    story_rating INT CHECK ( story_rating BETWEEN 1 AND 10),
    graphics_rating INT CHECK ( graphics_rating BETWEEN 1 AND 10),
    gameplay_rating INT CHECK ( gameplay_rating BETWEEN 1 AND 10),
    FOREIGN KEY (status_id) REFERENCES status(id),
    FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE ,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


