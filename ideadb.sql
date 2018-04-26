-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ideadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ideadb` ;

-- -----------------------------------------------------
-- Schema ideadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ideadb` DEFAULT CHARACTER SET utf8 ;
USE `ideadb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `admin` TINYINT NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile` ;

CREATE TABLE IF NOT EXISTS `profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `bio` VARCHAR(1000) NULL,
  `profile_pic` VARCHAR(300) NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_profile`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `idea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `idea` ;

CREATE TABLE IF NOT EXISTS `idea` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `profile_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `content` TEXT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `profile_id_idx` (`profile_id` ASC),
  CONSTRAINT `fk_profile_idea`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `idea_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `idea_like` ;

CREATE TABLE IF NOT EXISTS `idea_like` (
  `profile_id` INT NOT NULL,
  `idea_id` INT NOT NULL,
  `like` TINYINT NOT NULL,
  PRIMARY KEY (`profile_id`, `idea_id`),
  INDEX `idea_id_idx` (`idea_id` ASC),
  CONSTRAINT `fk_profile_idea_like`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idea_idea_like`
    FOREIGN KEY (`idea_id`)
    REFERENCES `idea` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `profile_id` INT NOT NULL,
  `idea_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `profile_id_idx` (`profile_id` ASC),
  INDEX `idea_id_idx` (`idea_id` ASC),
  CONSTRAINT `fk_profile_comment`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idea_comment`
    FOREIGN KEY (`idea_id`)
    REFERENCES `idea` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment_like` ;

CREATE TABLE IF NOT EXISTS `comment_like` (
  `profile_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  `like` TINYINT NOT NULL,
  INDEX `profile_id_idx` (`profile_id` ASC),
  PRIMARY KEY (`profile_id`, `comment_id`),
  INDEX `fk_comment_comment_like_idx` (`comment_id` ASC),
  CONSTRAINT `fk_profile_comment_like`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment_like`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO ideaUser;
 DROP USER ideaUser;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'ideaUser' IDENTIFIED BY 'midterm';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'ideaUser';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`) VALUES (1, 'wombatlover1997', 'wombat', 1, 'natetrainor@msn.com');
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`) VALUES (2, 'ilovepineapples', 'pineapple', 0, 'svenrskoglund@gmail.com');
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`) VALUES (3, 'iWannaHike', 'hike', 0, 'graciec@gmail.com');
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`) VALUES (4, 'awkward_monkey', 'pizza', 0, 'doraeharper@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`) VALUES (1, 1, 'Something brief', NULL, DEFAULT);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`) VALUES (2, 2, 'Sven Skoglund likes pineapples', NULL, DEFAULT);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`) VALUES (3, 3, 'Let\'s go skiing!', NULL, DEFAULT);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`) VALUES (4, 4, 'Surprise me, Nate.', NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `idea`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`) VALUES (1, 1, 'Let\'s talk about kangaroos', NULL, DEFAULT);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`) VALUES (2, 2, 'Pineapple should go on pizza!', 'Pineapple is the superior pizza topping. Anyone who thinks otherwise is an inferior being from the ninth circle of Hell.', DEFAULT);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`) VALUES (3, 3, 'People who ski in jeans are stupid', 'People who ski in jeans should go home to Texas.', DEFAULT);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`) VALUES (4, 4, 'Pineapple spaghetti, WTF?!', 'Just WTF?!', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`) VALUES (1, 2, 3, 'I agree. Jeans are telltale sign of someone who doesn\'t belong here.', DEFAULT);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`) VALUES (2, 3, 2, 'Pineapple pizza rocks!', DEFAULT);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`) VALUES (3, 4, 2, 'Go to Hawaii if you want pineapple! We don\'t care for your fruity ways!', DEFAULT);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`) VALUES (4, 2, 4, 'Pineapple on spaghetti sounds pretty good. I\'ll have to try it!', DEFAULT);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`) VALUES (5, 4, 4, 'Hey guys, tone down the weed, will ya?', DEFAULT);

COMMIT;