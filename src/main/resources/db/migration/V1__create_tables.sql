DROP TABLE IF EXISTS `tournament`;
CREATE TABLE `tournament`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`            varchar(255) NOT NULL,
    `maxParticipants` bigint(20),
    `matchQuantity`   bigint(20),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants`
(
    `id`   bigint(20)  NOT NULL AUTO_INCREMENT,
    `name` varchar(20) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `match`;
CREATE TABLE `match`
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT,
    `startTime`         varchar(255) NOT NULL,
    `finishTime`        varchar(255) NOT NULL,
    `participant1Score` bigint(20),
    `participant2Score` bigint(20),
    `tournament_id`     bigint(20) DEFAULT NULL,
    `participant1_id`   bigint(20) DEFAULT NULL,
    `participant2_id`   bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_match` (`tournament_id`),
    KEY `FK_participant1` (`participant1_id`),
    KEY `FK_participant2` (`participant2_id`),
    CONSTRAINT `FK_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
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