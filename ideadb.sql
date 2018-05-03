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
  `active` TINYINT NOT NULL DEFAULT 1,
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
  `bio` VARCHAR(1000) BINARY NULL,
  `profile_pic` VARCHAR(300) NOT NULL DEFAULT 'https://www.mybenshop.com/wp-content/uploads/2017/09/Rodin-the-Thinker-Sculpture-Medium-Figurine-Sandstone-Color-500x500.jpg',
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` TINYINT NOT NULL DEFAULT 1,
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
  `active` TINYINT NOT NULL DEFAULT 1,
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
  `vote` TINYINT NULL,
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
  `active` TINYINT NOT NULL DEFAULT 1,
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
  `vote` TINYINT NULL,
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
GRANT USAGE ON *.* TO ideaUser@localhost;
 DROP USER ideaUser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'ideaUser'@'localhost' IDENTIFIED BY 'midterm';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'ideaUser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (1, 'wombatlover1997', 'wombat', 1, 'natetrainor@hotmail.com', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (2, 'ilovepineapples', 'pineapple', 0, 'svenrskoglund@gmail.com', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (3, 'iWannaHike', 'hike', 0, 'graciec@gmail.com', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (4, 'awkward_monkey', 'pizza', 0, 'doraeharper@gmail.com', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (5, 'wazzup', '1994ever', 0, 'qazwsx@theta.gov', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (6, 'GordonRamsey', 'cooking', 0, 'ramsey@google.com', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (7, 'Socrates', 'greekLife', 1, 'crito@symposium.edu', 1);
INSERT INTO `user` (`id`, `username`, `password`, `admin`, `email`, `active`) VALUES (8, 'MoreCoffeeAndCowbell', 'hondaFit', 0, 'no@connection.gov', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (1, 1, 'Something brief', 'https://i.imgur.com/S5n7pxl.jpg', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (2, 2, 'Sven Skoglund likes pineapples', 'https://i.imgur.com/oMcBTCQ.jpg', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (3, 3, 'Let\'s go skiing!', 'https://i.imgur.com/g9ntKaa.jpg', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (4, 4, 'Surprise me, Nate.', 'https://i.imgur.com/bfU70Gl.png', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (5, 5, 'Hey, man. I\'m here to post, dude!', 'http://www.freakingnews.com/pictures/8000/Surfer-8401.jpg', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (6, 6, 'One of the greatest chefs in the known regions, Gordon Ramsey is a force to be reckoned with in the kitchen.', 'https://tse3.mm.bing.net/th?id=OIP.2_oO0BAJUpqUD535j1JrbwHaHa&pid=Api', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (7, 7, 'I am Socrates. Some might call me an intellectual because I know so much, but, in truth, I know nothing.', 'http://4.bp.blogspot.com/-hxY8PJVTQ5E/To7Rzv4z1jI/AAAAAAAAALw/yckQMUAlMyg/s1600/socrates2.jpg', DEFAULT, 1);
INSERT INTO `profile` (`id`, `user_id`, `bio`, `profile_pic`, `created_date`, `active`) VALUES (8, 8, 'I like driving my kids in our car.', 'http://uploads.haystak.com/Production_Templates/Images/2016/Honda/16hondafit2a/honda_16fit2a_angularfront_aegeanbluemetallic.png', DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `idea`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (1, 1, 'Let\'s talk about kangaroos', NULL, DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (2, 2, 'Heaven and Hell', 'Reality is a spectrum inside all of us ranging between heaven and hell. There are actions that reliably move an individual in one direction or another. Performing actions that move an individual in either direction repeatedly, consistently, and with discipline, will allow the individual to arrive closer to that end of the spectrum in the future. There is a compouding effect on efforts that are done with regularity and with discipline. Their effects are amplified over time in a manner to similar to compound interest. A life time of hellish actions will lead one deep into the hell end of the spectrum, a life time of heavenly actions will lead one deep into the heavenly end of the spectrum. Also, the more habitually a person moves in one direction. The easier it will be to continue to move in that direction. Actions vary in their magnitude, the direction they lead to on the spectrum, and the frequency at which they are repeatable before becoming harmful.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (3, 4, 'People who ski in jeans are stupid', 'People who ski in jeans should go home to Texas.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (4, 3, 'Idea for new app', 'I’m thinking  of building an app for outdoor enthusiasts. It’s like Tinder but not a dating app, just an app to connect with people who hike, mountain bike, or fish, so you can find a buddy to go do things with! Just in the planning phases…..', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (5, 7, 'What is the good?', 'Building on my demonstration that those regarded as experts in ethical matters did not have the understanding necessary for a good human life, my student Plato introduced the idea that their mistakes were due to their not engaging properly with a class of entities he called forms, chief examples of which were Justice, Beauty, and Equality. Whereas other thinkers—and Plato himself in certain passages—used the term without any precise technical force, Plato in the course of his career came to devote specialized attention to these entities. As he conceived them, they were accessible not to the senses but to the mind alone, and they were the most important constituents of reality, underlying the existence of the sensible world and giving it what intelligibility it has. In metaphysics Plato envisioned a systematic, rational treatment of the forms and their interrelations, starting with the most fundamental among them (the Good, or the One); in ethics and moral psychology he developed the view that the good life requires not just a certain kind of knowledge (as I had suggested) but also habituation to healthy emotional responses and therefore harmony between the three parts of the soul (according to Plato, reason, spirit, and appetite). His works also contain discussions in aesthetics, political philosophy, theology, cosmology, epistemology, and the philosophy of language. His school fostered research not just in philosophy narrowly conceived but in a wide range of endeavours that today would be called mathematical or scientific.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (6, 3, 'Biophilia', 'The Biophilia Hypothesis was introduced by E.O. Wilson in the early ’80’s based on Eric Fromm’s writing to express the notion of human’s innate connection to nature. He proposes that our connection to the natural world comes from deep in our genetic memory. Our evolutionary relationship with nature is complex; as hunter-gatherers, we relied on forests for shelter and wood for our fires and the natural bounties of fruit, tubers, and animals for survival. We were closely integrated into an ecosystem, following herds of prey animals and the natural cycles of growth and harvest. \nNow we sit all day at our computers, in our air-conditioned rooms and artificial light, and light up the night with the glow from televisions, devices, overhead lights. We’ve become very disconnected from the natural world, in many ways. Many of us have become disconnected from our food supply, forgetting that that carrot was once in the dirt, that hamburger was once a cow. \nWhen was the last time you stood barefoot in the grass? Give it a try - go outside and take off your shoes, wander around for a little bit in the grass and the dirt, try to pick up a small rock with your toes. How do you feel? Silly, perhaps? Did you smile? Were you worried about getting dirty or injured? Did it take you back to summertime when you were 5 years old without a care in the world? Whether it’s electron exchange or just the freedom of getting out of your shoes, there is something about actually being in contact with the earth’s surface. \nWhat are your own experiences and relationship with nature? Do you find peace sitting under a tree or would you prefer to view it from a window or in a photograph?', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (7, 6, 'YORKSHIRE PUDDING RECIPE', 'Beat 3 eggs together in a mixing bowl using a balloon whisk. Sift the flour with the salt, then gradually beat this into the eggs to make a smooth batter. Whisk in the milk until combined. Cover and leave to stand at room temperature for about 1 hour. Preheat the oven to 220°C/200°C fan/Gas 7. Put 2 teaspoons of vegetable oil into each compartment of two 4-hole Yorkshire pudding tins (see Tip). If you only have one tin, you’ll have to do this in two batches. Place the tin in the oven for 12–15 minutes to heat up the oil and tins until very hot. This is important for the rise. Stir the batter and pour into a jug. At the oven (this is safer than carrying a tin of hot oil across the kitchen), carefully pour some batter into the middle of the oil in each hole, remembering that it is very hot. Watch out as the oil will sizzle a bit as the batter hits it. Put the tins straight back into the oven and bake for about 15 minutes or until the Yorkshires are well risen, golden brown and crisp. Serve immediately with Roast Beef with gravy and all the trimmings.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (8, 7, 'Existentialism', 'Existentialism, any of various philosophies, most influential in continental Europe from about 1930 to the mid-20th century, that have in common an interpretation of human existence in the world that stresses its concreteness and its problematic character.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (9, 5, 'Surfboard site', 'CHeck this PlAce out! Surfboards.com', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (10, 3, 'Concerning trout', 'I have loved fly fishing for many years now. For myself, I like to clarify that it’s called fishing, not catching. I would slightly modify Thoreau and say “Many people go fishing all of their lives without knowing that it is not fish they are after.“. Sure, there’s a thrill when you feel that tell-tale tug or see the flash of a strong and healthy wild trout attacking your fly. I won’t deny it and the thrill’s especially strong when it’s a fly you tied. (Holy *bleep*, it actually worked!) Here’s the thing, though. To get to the catching point, I rambled a bit up a trail, through the trees and underbrush. I picked up a dozen rocks and checked out the caddis husks. I shook willow branches and tried to figure out what those tiny bugs were. I looked at the clouds; what the heck is the weather going to do? Then, standing creekside, in divine solitude, I breathe deep and am overwhelmed by gratitude – I GET to do this. I get to spend this time, on this water, in this forest, in these mountains. How did I get so lucky? In that moment, I feel so connected, so at home. The actual catching of a fish, well, that’s secondary for me. It’s the entire experience, from rocks to weeds to fish, it leaves me awestruck and grateful.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (11, 6, 'Mom’s Pound Cake', '1-1/2 cups unsalted butter, softened\n        2-1/4 cups packed brown sugar\n        5 large eggs, room temperature\n        2 teaspoons vanilla extract\n        3 cups all-purpose flour\n        1 teaspoon baking powder\n        1/4 teaspoon salt\nPreheat oven to 350°. Grease and flour a 10-in. fluted tube pan.\nCream butter and brown sugar until light and fluffy. Add eggs, one at a time, beating well after each addition. Beat in vanilla. In another bowl, whisk flour, baking powder and salt; add to creamed mixture, beating after each addition just until combined.\nTransfer to prepared pan. Bake until a toothpick inserted in center comes out clean, 55-65 minutes. Cool in pan 10 minutes before removing to a wire rack to cool completely.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (12, 4, 'Pineapple spaghetti, WTF?!', 'Just WTF?!', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (13, 2, 'Pineapple should go on pizza!', 'Pineapple is the superior pizza topping. Anyone who thinks otherwise is an inferior being from the ninth circle of Hell.', DEFAULT, 1);
INSERT INTO `idea` (`id`, `profile_id`, `name`, `content`, `date_created`, `active`) VALUES (14, 8, 'Car Games', 'My oldest is about to turn eleven. For the past two years or so, she has tried vary hard to persuade me to get her a phone. One of her gripes was that she would get bored in the car. I used this as an opportunity to connect with her and play a game while we are on the road. We live in the DC area and one of the perks is that there are people from all over the country that live there. We have a Subaru Outback and Honda Fit so our game consists of being the first one to call out 6 different Subaru Outbacks or Honda Fits in any combination AND 6 plates from different states. It\'s extra exciting to see plates from different countries and the game itself can get pretty intense because we are so competitive (no, I don\'t let her win)! There are times when we switch it up and have us call out the state and spell it out.', DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `idea_like`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 1, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 1, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (3, 1, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (4, 1, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 2, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 2, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (3, 2, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (4, 2, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 3, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 3, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (3, 3, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (4, 3, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (5, 5, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (6, 5, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 5, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 7, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 7, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (3, 7, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (4, 7, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (5, 7, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (7, 7, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 6, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (4, 6, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (6, 8, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 9, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (6, 9, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 10, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 10, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (5, 10, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (1, 4, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 4, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (4, 4, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (5, 4, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (6, 4, 0);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (7, 4, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (2, 14, 1);
INSERT INTO `idea_like` (`profile_id`, `idea_id`, `vote`) VALUES (5, 14, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (1, 2, 3, 'I agree. Jeans are telltale sign of someone who doesn\'t belong here.', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (2, 3, 13, 'Pineapple pizza rocks!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (3, 4, 13, 'Go to Hawaii if you want pineapple! We don\'t care for your fruity ways!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (4, 2, 12, 'Pineapple on spaghetti sounds pretty good. I\'ll have to try it!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (5, 4, 12, 'Hey guys, tone down the weed, will ya?', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (6, 5, 11, 'Sugar!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (7, 2, 11, 'adding a bit of sour cream to the batter is really delicious!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (8, 6, 5, 'Astounding idea', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (9, 4, 11, 'Excellent with lemon glaze!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (10, 1, 8, 'Neat', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (11, 7, 2, 'Glorious!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (12, 1, 4, 'This sounds like a cool idea! Can you share links to websites and pictures so youc an show others the adventures you had?', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (13, 2, 4, 'Ditto wombatlover1997.', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (14, 4, 4, 'If youc ould get that link sharing implementation in, I think this could be a great app!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (15, 5, 4, 'Radical!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (16, 6, 4, 'This is ridiculous!', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (17, 1, 4, 'Kickstarter?', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (18, 3, 4, 'Maybe ;)', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (19, 8, 14, 'What are some things you do as a family to keep your sanity in the car?', DEFAULT, 1);
INSERT INTO `comment` (`id`, `profile_id`, `idea_id`, `content`, `date_created`, `active`) VALUES (20, 2, 14, 'When I was a kid, my brother and I would play on our GameBoy colors. We especially loved the original Pokeman games. I never did catch em all though...', DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_like`
-- -----------------------------------------------------
START TRANSACTION;
USE `ideadb`;
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (1, 1, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (2, 1, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (3, 1, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (4, 1, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (1, 2, 0);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (2, 2, 0);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (3, 2, 0);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (4, 2, 0);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (1, 3, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (2, 3, 0);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (3, 3, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (4, 3, 0);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (2, 17, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (4, 17, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (7, 17, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (5, 17, 1);
INSERT INTO `comment_like` (`profile_id`, `comment_id`, `vote`) VALUES (6, 17, 0);

COMMIT;
