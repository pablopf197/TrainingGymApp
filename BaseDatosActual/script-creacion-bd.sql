-- MySQL Workbench Forward Engineering

DROP DATABASE bdgym;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdgym
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdgym
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdgym` DEFAULT CHARACTER SET latin1 ;
USE `bdgym` ;

-- -----------------------------------------------------
-- Table `bdgym`.`trol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`trol` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `rol` ENUM('admin', 'entrenador', 'cliente') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`tipoentrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`tipoentrenamiento` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('body_building', 'cross_training') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `dni` VARCHAR(10) NOT NULL,
  `genero` VARCHAR(30) NOT NULL,
  `correo` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(10) NOT NULL,
  `fecha_ingreso` DATE NULL DEFAULT NULL,
  `nombre_usuario` VARCHAR(30) NOT NULL,
  `contraseña` VARCHAR(30) NOT NULL,
  `validado` TINYINT(4) NOT NULL,
  `tipoentrenamiento_id` INT(11) NOT NULL,
  `trol_id` INT(11) NOT NULL,
  `entrenador_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE,
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
  UNIQUE INDEX `nombreUsuario_UNIQUE` (`nombre_usuario` ASC) VISIBLE,
  INDEX `fk_usuario_tipoEntrenamiento1_idx` (`tipoentrenamiento_id` ASC) VISIBLE,
  INDEX `fk_usuario_tRol1_idx` (`trol_id` ASC) VISIBLE,
  INDEX `fk_usuario_usuario1_idx` (`entrenador_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_tRol1`
    FOREIGN KEY (`trol_id`)
    REFERENCES `bdgym`.`trol` (`id`),
  CONSTRAINT `fk_usuario_tipoEntrenamiento1`
    FOREIGN KEY (`tipoentrenamiento_id`)
    REFERENCES `bdgym`.`tipoentrenamiento` (`id`),
  CONSTRAINT `fk_usuario_usuario1`
    FOREIGN KEY (`entrenador_id`)
    REFERENCES `bdgym`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`cliente` (
  `usuario_id` INT(11) NOT NULL,
  `peso` FLOAT NULL DEFAULT NULL,
  `altura` FLOAT NULL DEFAULT NULL,
  `objetivos` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  INDEX `fk_cliente_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdgym`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`tipoejerciciobodybuilding`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`tipoejerciciobodybuilding` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('pierna', 'hombro', 'pecho', 'espalda', 'biceps', 'triceps') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`tipoejerciciocrosstraining`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`tipoejerciciocrosstraining` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('fuerza/resistencia', 'capacidad aeróbica', 'velocidad/potencia', 'estabilidad', 'movilidad') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`ejercicio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  `descripcion` VARCHAR(250) NULL DEFAULT NULL,
  `video` VARCHAR(250) NULL DEFAULT NULL,
  `tipoentrenamiento_id` INT(11) NULL DEFAULT NULL,
  `tipoejerciciobodybuilding_id` INT(11) NULL DEFAULT NULL,
  `tipoejerciciocrosstraining_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ejercicio_tipoEntrenamiento1_idx` (`tipoentrenamiento_id` ASC) VISIBLE,
  INDEX `fk_ejercicio_tipoEjercicioBodyBuilding1_idx` (`tipoejerciciobodybuilding_id` ASC) VISIBLE,
  INDEX `fk_ejercicio_tipoEjercicioCrossTraining1_idx` (`tipoejerciciocrosstraining_id` ASC) VISIBLE,
  CONSTRAINT `fk_ejercicio_tipoEjercicioBodyBuilding1`
    FOREIGN KEY (`tipoejerciciobodybuilding_id`)
    REFERENCES `bdgym`.`tipoejerciciobodybuilding` (`id`),
  CONSTRAINT `fk_ejercicio_tipoEjercicioCrossTraining1`
    FOREIGN KEY (`tipoejerciciocrosstraining_id`)
    REFERENCES `bdgym`.`tipoejerciciocrosstraining` (`id`),
  CONSTRAINT `fk_ejercicio_tipoEntrenamiento1`
    FOREIGN KEY (`tipoentrenamiento_id`)
    REFERENCES `bdgym`.`tipoentrenamiento` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`rutina_predefinida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`rutina_predefinida` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `objetivos` VARCHAR(250) NULL DEFAULT NULL,
  `usuario_id` INT(11) NOT NULL,
  `tipoentrenamiento_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rutina_predefinida_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_rutina_predefinida_tipoEntrenamiento1_idx` (`tipoentrenamiento_id` ASC) VISIBLE,
  CONSTRAINT `fk_rutina_predefinida_tipoEntrenamiento1`
    FOREIGN KEY (`tipoentrenamiento_id`)
    REFERENCES `bdgym`.`tipoentrenamiento` (`id`),
  CONSTRAINT `fk_rutina_predefinida_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdgym`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`rutina_asignada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`rutina_asignada` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `rutina_predefinida_id` INT(11) NOT NULL,
  `usuario_id` INT(11) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rutina_asignada_rutina_predefinida1_idx` (`rutina_predefinida_id` ASC) VISIBLE,
  INDEX `fk_rutina_asignada_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_rutina_asignada_rutina_predefinida1`
    FOREIGN KEY (`rutina_predefinida_id`)
    REFERENCES `bdgym`.`rutina_predefinida` (`id`),
  CONSTRAINT `fk_rutina_asignada_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdgym`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`sesionentrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`sesionentrenamiento` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(250) NULL DEFAULT NULL,
  `usuario_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sesionentrenamiento_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_sesionentrenamiento_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdgym`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`rutina_sesionentrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`rutina_sesionentrenamiento` (
  `sesionentrenamiento_id` INT(11) NOT NULL,
  `rutina_predefinida_id` INT(11) NOT NULL,
  `posicion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sesionentrenamiento_id`, `rutina_predefinida_id`),
  INDEX `fk_rutina_sesionentrenamiento_sesionentrenamiento1_idx` (`sesionentrenamiento_id` ASC) VISIBLE,
  INDEX `fk_rutina_sesionentrenamiento_rutina_predefinida1_idx` (`rutina_predefinida_id` ASC) VISIBLE,
  CONSTRAINT `fk_rutina_sesionentrenamiento_rutina_predefinida1`
    FOREIGN KEY (`rutina_predefinida_id`)
    REFERENCES `bdgym`.`rutina_predefinida` (`id`),
  CONSTRAINT `fk_rutina_sesionentrenamiento_sesionentrenamiento1`
    FOREIGN KEY (`sesionentrenamiento_id`)
    REFERENCES `bdgym`.`sesionentrenamiento` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`sesionejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`sesionejercicio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `series` INT(11) NULL DEFAULT NULL,
  `repeticiones` INT(11) NULL DEFAULT NULL,
  `duracion` INT(11) NULL DEFAULT NULL,
  `ejercicio_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sesionejercicio_ejercicio1_idx` (`ejercicio_id` ASC) VISIBLE,
  CONSTRAINT `fk_sesionejercicio_ejercicio1`
    FOREIGN KEY (`ejercicio_id`)
    REFERENCES `bdgym`.`ejercicio` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`sesionentrenamiento_has_sesionejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`sesionentrenamiento_has_sesionejercicio` (
  `sesionentrenamiento_id` INT(11) NOT NULL,
  `sesionejercicio_id` INT(11) NOT NULL,
  `posicion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sesionentrenamiento_id`, `sesionejercicio_id`),
  INDEX `fk_sesionentrenamiento_has_sesionejercicio_sesionejercicio1_idx` (`sesionejercicio_id` ASC) VISIBLE,
  INDEX `fk_sesionentrenamiento_has_sesionejercicio_sesionentrenamie_idx` (`sesionentrenamiento_id` ASC) VISIBLE,
  CONSTRAINT `fk_sesionentrenamiento_has_sesionejercicio_sesionejercicio1`
    FOREIGN KEY (`sesionejercicio_id`)
    REFERENCES `bdgym`.`sesionejercicio` (`id`),
  CONSTRAINT `fk_sesionentrenamiento_has_sesionejercicio_sesionentrenamiento1`
    FOREIGN KEY (`sesionentrenamiento_id`)
    REFERENCES `bdgym`.`sesionentrenamiento` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdgym`.`valoracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdgym`.`valoracion` (
  `puntuacion` INT(11) NULL DEFAULT NULL,
  `descripcion` VARCHAR(250) NULL DEFAULT NULL,
  `usuario_id` INT(11) NOT NULL,
  `sesionejercicio_id` INT(11) NOT NULL,
  `rutina_asignada_id` INT(11) NOT NULL,
  PRIMARY KEY (`usuario_id`, `sesionejercicio_id`, `rutina_asignada_id`),
  INDEX `fk_valoracion_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_valoracion_sesionejercicio1_idx` (`sesionejercicio_id` ASC) VISIBLE,
  INDEX `fk_valoracion_rutina_asignada1_idx` (`rutina_asignada_id` ASC) VISIBLE,
  CONSTRAINT `fk_valoracion_rutina_asignada1`
    FOREIGN KEY (`rutina_asignada_id`)
    REFERENCES `bdgym`.`rutina_asignada` (`id`),
  CONSTRAINT `fk_valoracion_sesionejercicio1`
    FOREIGN KEY (`sesionejercicio_id`)
    REFERENCES `bdgym`.`sesionejercicio` (`id`),
  CONSTRAINT `fk_valoracion_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdgym`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
