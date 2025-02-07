SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";
DROP SCHEMA IF EXISTS `libros` ;
CREATE SCHEMA IF NOT EXISTS `librosexamen2` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `librosexamen2` ;

-- -----------------------------------------------------
-- Table `libros`.`Alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `librosexamen2`.`Estudiante` (
	`dni_estudiante` VARCHAR(9) NOT NULL,
	`nombre` VARCHAR(150) NULL DEFAULT NULL,
	`primerApellido` VARCHAR(150) NULL DEFAULT NULL,
	`segundApellido` VARCHAR(150) NULL DEFAULT NULL,
	PRIMARY KEY (`dni_estudiante`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `libros`.`Libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `librosexamen2`.`Libro` (
	`isbn` INT NOT NULL AUTO_INCREMENT,
	`titulo` VARCHAR(150) NULL DEFAULT NULL,
	`autor` VARCHAR(200) NULL DEFAULT NULL,
	`editorial` VARCHAR(150) NULL DEFAULT NULL,
	`estado` VARCHAR(50) NULL DEFAULT NULL,
	`baja` INT NULL DEFAULT '0',
	PRIMARY KEY (`isbn`)
)ENGINE = InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `libros`.`Historio_prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `librosexamen2`.`Historial_prestamo` (
	`id_prestamo` INT NOT NULL AUTO_INCREMENT,
	`dni_estudiante` VARCHAR(9) NOT NULL,
	`isbn` INT NOT NULL,
    `prestamo_online` BOOL NOT NULL,
	`fecha_prestamo` DATETIME NULL DEFAULT NULL,
	`fecha_devolucion` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id_prestamo`),
    CONSTRAINT `FK_Historial_prestamo_Libro` FOREIGN KEY (`isbn`) REFERENCES `librosexamen2`.`Libro` (`isbn`),
	CONSTRAINT `FK_Historial_prestamo_Estudiante` FOREIGN KEY (`dni_estudiante`) REFERENCES `librosexamen2`.`Estudiante` (`dni_estudiante`)
	
)ENGINE = InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `libros`.`Prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `librosexamen2`.`Prestamo` (
	`id_prestamo` INT  NOT NULL AUTO_INCREMENT,
	`dni_estudiante` VARCHAR(9) NOT NULL,
	`isbn` INT NOT NULL,
    `prestamo_online` BOOL NOT NULL,
	`fecha_prestamo` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id_prestamo`),
	CONSTRAINT `FK_Prestamo_Libro` FOREIGN KEY (`isbn`)  REFERENCES `librosexamen2`.`Libro` (`isbn`),
	CONSTRAINT `FK_Prestamo_Estudiante` FOREIGN KEY (`dni_estudiante`) REFERENCES `librosexamen2`.`Estudiante` (`dni_estudiante`)
)ENGINE = InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

COMMIT;

