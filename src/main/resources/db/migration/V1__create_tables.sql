DROP TABLE IF EXISTS `tournament`;
CREATE TABLE `tournament`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`             varchar(255) NOT NULL,
    `max_participants` bigint(20),
    `match_quantity`   bigint(20),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants`
(
    `id`   bigint(20)  NOT NULL AUTO_INCREMENT,
    `participant_name` varchar(20) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `matches`;
CREATE TABLE `matches`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `start_time1`        DATE  DEFAULT NULL,
    `finish_time1`       DATE  DEFAULT NULL,
    `tournament_id`      bigint(20) DEFAULT NULL,
    `participant1_id`    bigint(20) DEFAULT NULL,
    `participant2_id`    bigint(20) DEFAULT NULL,
    `participant1_score` bigint(5) ,
    `participant2_score` bigint(5) ,
    PRIMARY KEY (`id`),
    KEY `FK_match` (`tournament_id`),
    KEY `FK_participant1` (`participant1_id`),
    KEY `FK_participant2` (`participant2_id`),
    CONSTRAINT `FK_match` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
    CONSTRAINT `FK_participant1` FOREIGN KEY (`participant1_id`) REFERENCES `participants` (`id`),
    CONSTRAINT `FK_participant2` FOREIGN KEY (`participant2_id`) REFERENCES `participants` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `participants_in_tournaments`;
CREATE TABLE `participants_in_tournaments`
(
    `tournament_id`   bigint(20) NOT NULL,
    `participants_id` bigint(20) NOT NULL,
    KEY `FK_tournament_participants` (`tournament_id`),
    KEY `FK_participants` (`participants_id`),
    CONSTRAINT `FK_participants` FOREIGN KEY (`participants_id`) REFERENCES `participants` (`id`),
    CONSTRAINT `FK_tournament_participants` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `participants_in_matches`;
CREATE TABLE `participants_in_matches`
(
    `match_id`   bigint(20) NOT NULL,
    `participants1_id` bigint(20) NOT NULL,
    KEY `FK_match_participants` (`match_id`),
    KEY `FK_participants_in_match` (`participants1_id`),
    CONSTRAINT `FK_participants_in_match` FOREIGN KEY (`participants1_id`) REFERENCES `participants` (`id`),
    CONSTRAINT `FK_match_participants` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;