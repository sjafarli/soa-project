--DROP TABLE IF EXISTS `voting`;

CREATE TABLE `voting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `imdb_id` varchar(45) NOT NULL,UNIQUE,
  `votes` INT DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `voting` (`id`, `name`, `imdb_id`,`votes`)
VALUES
  (1,'Frozen', 'tt2294629', 2),
  (2,'Cars', 'tt0317219', 0)
  --(3,'Joker', 'tt7286456', 8);
