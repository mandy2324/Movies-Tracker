CREATE DATABASE `movie_tracker_db`;

use movie_tracker_db;

CREATE TABLE `movies` (
  `movieId` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `length` time(5) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tracker` (
  `status` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `movieIdF` int DEFAULT NULL,
  `userIdF` int DEFAULT NULL,
  KEY `userId_idx` (`userIdF`),
  KEY `moviesIDF_idx` (`movieIdF`),
  CONSTRAINT `moviesIDF` FOREIGN KEY (`movieIdF`) REFERENCES `movies` (`movieId`),
  CONSTRAINT `userIDF` FOREIGN KEY (`userIdF`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;