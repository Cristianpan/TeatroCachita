CREATE TABLE `obra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `genero` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `primerActor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `segundoActor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `precioBoleto` decimal(10,2) NOT NULL,
  `duracion` int(11) NOT NULL,
  `resumen` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) /*!50100 TABLESPACE `innodb_system` */ ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `obra` (`id`, `nombre`, `genero`, `primerActor`, `segundoActor`, `precioBoleto`, `duracion`, `resumen`) VALUES
(1, 'Blanca Nieves y los 8 enanitos', 'Cuento', 'Blanca Nieves', 'Malefica', 50.00, 16, 'Blanca Nieves y los 7 enanitos');
INSERT INTO `obra` (`id`, `nombre`, `genero`, `primerActor`, `segundoActor`, `precioBoleto`, `duracion`, `resumen`) VALUES
(2, 'Romeo y Julieta', 'Tragedia', 'Julieta', 'Romeo', 50.50, 100, 'Rome y Julieta');
INSERT INTO `obra` (`id`, `nombre`, `genero`, `primerActor`, `segundoActor`, `precioBoleto`, `duracion`, `resumen`) VALUES
(3, 'Narnia', 'Fantasía', 'Yo', 'Tú', 100.52, 60, 'Narnia, el león, la bruja y el ropero');
