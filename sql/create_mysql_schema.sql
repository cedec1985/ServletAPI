-- MySQL initialization script for ServletAPI (creates database, user and articles table)
-- Adjust database name, username and password as needed.

-- 1) Create database (if not exists)
CREATE DATABASE IF NOT EXISTS `servletapi` 
  CHARACTER SET = utf8mb4 
  COLLATE = utf8mb4_unicode_ci;

-- 2) Create user (replace 'app'@'localhost' and 'yourpassword' with your chosen credentials)
-- If you prefer to use an existing user, skip this step.
CREATE USER IF NOT EXISTS 'app'@'localhost' IDENTIFIED BY 'yourpassword';

-- 3) Grant privileges on the database to the user
GRANT ALL PRIVILEGES ON `servletapi`.* TO 'app'@'localhost';
FLUSH PRIVILEGES;

-- 4) Use the database
USE `servletapi`;

-- 5) Create the articles table
-- This schema corresponds to the JPA entity Product.Article
CREATE TABLE IF NOT EXISTS `articles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `ref` VARCHAR(255) DEFAULT NULL,
  `ean` VARCHAR(255) DEFAULT NULL,
  `delivery_date` DATE DEFAULT NULL,
  `recipient` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ref` (`ref`),
  UNIQUE KEY `uk_ean` (`ean`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6) Optional: insert sample data
INSERT INTO `articles` (`name`, `ref`, `ean`, `delivery_date`, `recipient`) VALUES
('Exemple Produit A', 'REF-A-001', '1234567890123', '2026-08-01', 'Magasin A'),
('Exemple Produit B', 'REF-B-002', '9876543210987', '2026-08-15', 'Magasin B');
