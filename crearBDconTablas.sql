CREATE DATABASE stacktrace;

CREATE TABLE `stacktrace`.`usuarios` ( 
    `userid` BIGINT NOT NULL AUTO_INCREMENT , 
    `dni` INT(10) NOT NULL , 
    `sexo` VARCHAR(10) NOT NULL , 
    `nombre` VARCHAR(50) NOT NULL , 
    `apellido` VARCHAR(50) NOT NULL , 
    `email` VARCHAR(255) NOT NULL , 
    `telefono` VARCHAR(30) NOT NULL , 
    `password` VARCHAR(255) NOT NULL , 
    PRIMARY KEY (`userid`)
);

CREATE TABLE `stacktrace`.`divisas` ( 
    `divisatipo` VARCHAR(10) NOT NULL , 
    `divisavalor` FLOAT NOT NULL , 
    PRIMARY KEY (`divisatipo`)
);

CREATE TABLE `stacktrace`.`billeteras` ( 
    `billeteraid` BIGINT NOT NULL AUTO_INCREMENT , 
    `userid` BIGINT DEFAULT NULL ,
    `saldototal` FLOAT NOT NULL ,
    PRIMARY KEY (`billeteraid`),
    FOREIGN KEY (`userid`) REFERENCES `usuarios`(`userid`) ON DELETE SET NULL
);

CREATE TABLE `stacktrace`.`saldosdivisas` ( 
    `saldosid` BIGINT NOT NULL AUTO_INCREMENT , 
    `billeteraid` BIGINT NOT NULL, 
    `divisatipo` VARCHAR(10) NOT NULL , 
    `divisasaldo` FLOAT NOT NULL , 
    PRIMARY KEY (`saldosid`),
    FOREIGN KEY (`billeteraid`) REFERENCES `billeteras`(`billeteraid`) ON DELETE CASCADE,
    FOREIGN KEY (`divisatipo`) REFERENCES `divisas`(`divisatipo`) ON DELETE CASCADE
);

CREATE TABLE `stacktrace`.`intercambios` ( 
    `operacionid` BIGINT NOT NULL AUTO_INCREMENT , 
    `operacionfechahora` VARCHAR(100) NOT NULL , 
    `billeteraidorigen` BIGINT NULL , 
    `billeteraiddestino` BIGINT NOT NULL , 
    `divisatipoorigen` VARCHAR(10) NOT NULL , 
    `divisatipodestino` VARCHAR(10) NOT NULL , 
    `monto` FLOAT NOT NULL , 
    PRIMARY KEY (`operacionid`),
    FOREIGN KEY (`billeteraidorigen`) REFERENCES `billeteras`(`billeteraid`) ON DELETE CASCADE,
    FOREIGN KEY (`divisatipoorigen`) REFERENCES `divisas`(`divisatipo`) ON DELETE CASCADE,
    FOREIGN KEY (`billeteraiddestino`) REFERENCES `billeteras`(`billeteraid`) ON DELETE CASCADE,
    FOREIGN KEY (`divisatipodestino`) REFERENCES `divisas`(`divisatipo`) ON DELETE CASCADE
);

CREATE TABLE `stacktrace`.`depositos` ( 
    `operacionid` BIGINT NOT NULL AUTO_INCREMENT , 
    `operacionfechahora` VARCHAR(100) NOT NULL , 
    `billeteraiddestino` BIGINT NOT NULL , 
    `divisatipodestino` VARCHAR(10) NOT NULL , 
    `monto` FLOAT NOT NULL , 
    PRIMARY KEY (`operacionid`),
    FOREIGN KEY (`billeteraiddestino`) REFERENCES `billeteras`(`billeteraid`) ON DELETE CASCADE,
    FOREIGN KEY (`divisatipodestino`) REFERENCES `divisas`(`divisatipo`) ON DELETE CASCADE
);
