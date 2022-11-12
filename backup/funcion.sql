CREATE TABLE `funcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `obraId` int(11) NOT NULL,
  `fechaPresentacion` date NOT NULL,
  `horaPresentacion` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `obraId` (`obraId`),
  CONSTRAINT `funcion_ibfk_1` FOREIGN KEY (`obraId`) REFERENCES `obra` (`id`) ON DELETE CASCADE
) /*!50100 TABLESPACE `innodb_system` */ ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

INSERT INTO `funcion` (`id`, `obraId`, `fechaPresentacion`, `horaPresentacion`) VALUES
(1, 1, '2022-11-11', '18:00:00');

