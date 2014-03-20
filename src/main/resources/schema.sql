CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `expireat` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


