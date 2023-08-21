CREATE TABLE IF NOT EXISTS `the_news`.`news` (
  `serial_number` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `update_date` DATE NOT NULL,
  `tags` VARCHAR(45) NOT NULL,
  `content` LONGTEXT NOT NULL,
  PRIMARY KEY (`serial_number`));

  
  CREATE TABLE IF NOT EXISTS `the_news`.`tags` (
  `tags_number` INT NOT NULL AUTO_INCREMENT,
  `tags` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tags_number`));
