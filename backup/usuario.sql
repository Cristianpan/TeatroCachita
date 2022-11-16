CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apellido` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nombreUsuario` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tipo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contrasena` varbinary(255) NOT NULL,
  `curp` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) /*!50100 TABLESPACE `innodb_system` */ ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `nombreUsuario`, `tipo`, `contrasena`, `curp`) VALUES
(1, 'Admi', 'Admi', 'Admi', 'Administrador', 'r0qPt6YXDKc=', 'Admi');