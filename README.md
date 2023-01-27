### Bienvenido al proyecto ecommerce con spring boot

####Presentacion del proyecto
El siguien proyecto fue basado en un sistema de ventas para usuarios donde se pusiera manera los roles de diferentes
usuarios y cada uno tuviera sus diferentes acciones restriguendo las acciones el - no del otro y manejo sesiones siendo
spring boot la tecnologia mas viable.

En el siguiente enlace podran ver mediante un diagramacomo es la relacion del proyecto con las tablas

[Diagramación del proyecto](https://drive.google.com/file/d/1aOd9vMVYgfVZJ6EPpBCpkgG5VLG1ZAHp/view?usp=sharing "Diagramación del proyecto")

### En caso de querer clonar este proyecto

#### Adjunto el script de la base de datos

```sql
-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-01-2023 a las 21:34:02
-- Versión del servidor: 5.7.36
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ecommerce`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles`
--

DROP TABLE IF EXISTS `detalles`;
CREATE TABLE IF NOT EXISTS `detalles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `total` double NOT NULL,
  `orden_id` int(11) DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdurdo71oa161lmmal7oeaor74` (`orden_id`),
  KEY `FKio4oyl8qt5jclekxp7bwws2iy` (`producto_id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalles`
--

INSERT INTO `detalles` (`id`, `cantidad`, `nombre`, `precio`, `total`, `orden_id`, `producto_id`) VALUES
(8, 1, 'Tablet', 500, 500, 12, 11),
(9, 1, 'Impresora canon', 800000, 800000, 12, 8),
(10, 1, 'Tablet', 500, 500, 15, 11),
(11, 1, 'Televisor samsung', 245423, 245423, 16, 10),
(12, 3, 'Tablet', 500, 1500, 17, 11),
(13, 5, 'Microfono yeti', 465534, 2327670, 17, 9),
(14, 1, 'Tablet', 500, 500, 19, 11),
(15, 1, 'Microfono yeti', 465534, 465534, 20, 9),
(16, 1, 'Impresora canon', 800000, 800000, 21, 8),
(17, 1, 'Televisor samsung', 245423, 245423, 22, 10),
(18, 4, 'Monitor LG', 25000, 100000, 23, 7),
(19, 2, 'Iphone', 500, 1000, 24, 14),
(20, 1, 'Impresora canon', 800000, 800000, 24, 8),
(21, 1, 'Tablet', 500, 500, 25, 11),
(22, 3, 'Tablet', 500, 500, 26, 11),
(23, 2, 'Iphone', 500, 1000, 27, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes`
--

DROP TABLE IF EXISTS `ordenes`;
CREATE TABLE IF NOT EXISTS `ordenes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `FechaCreacion` datetime DEFAULT NULL,
  `FechaRecibida` date DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsqu43gsd6mtx7b1siww96324` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ordenes`
--

INSERT INTO `ordenes` (`id`, `FechaCreacion`, `FechaRecibida`, `numero`, `total`, `usuario_id`) VALUES
(15, '2023-01-24 00:00:00', NULL, '0000000002', 500, 1),
(14, '2023-01-24 00:00:00', NULL, '0000000001', 0, 1),
(16, '2023-01-24 00:00:00', NULL, '0000000003', 245423, 1),
(17, '2023-01-24 00:00:00', NULL, '0000000004', 2329170, 1),
(18, '2023-01-24 00:00:00', NULL, '0000000005', 0, 1),
(19, '2023-01-24 00:00:00', NULL, '0000000006', 500, 1),
(20, '2023-01-24 00:00:00', NULL, '0000000007', 465534, 1),
(21, '2023-01-24 00:00:00', NULL, '0000000008', 800000, 1),
(22, '2023-01-24 00:00:00', NULL, '0000000009', 245423, 1),
(23, '2023-01-24 00:00:00', NULL, '0000000010', 100000, 1),
(24, '2023-01-24 00:00:00', NULL, '0000000011', 801000, 1),
(25, '2023-01-25 00:00:00', NULL, '0000000012', 500, 1),
(26, '2023-01-25 13:54:41', NULL, '0000000013', 500, 2),
(27, '2023-01-27 15:24:28', NULL, '0000000014', 1000, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo8g0kqq9awvgh4elqai7tdhu` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `usuario_id`) VALUES
(11, 22, ' lenovo', 'producto1.jpg', 'Tablet', 500, 1),
(7, 22, ' 22 pulgadas', 'monitor.jpg', 'Monitor LG', 25000, 1),
(8, 22, ' impresora con wifi 6', 'impresora.jpg', 'Impresora canon', 800000, 1),
(9, 33, ' microfono profesional', 'yeti.jpg', 'Microfono yeti', 465534, 1),
(10, 33, ' televisor de 60 pulgadas', 'TV.jpg', 'Televisor samsung', 245423, 1),
(14, 2, ' 13 pro max', 'D_NQ_NP_914906-CBT51552196888_092022-V.jpg', 'Iphone', 600, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `direccion`, `email`, `nombre`, `password`, `telefono`, `tipo`, `username`) VALUES
(1, 'asturias MZ T LT 23', 'gerardo@gmail.com', 'Gerardo', '123', '413521', 'ADMIN', 'Gerar'),
(2, 'nazareno', 'jua@gmail.com', 'juan guerra', '1234', NULL, 'USER', NULL),
(3, 'arjona', 'Rodriguez@gmail.com', 'Miguel david', '1234', NULL, 'USER', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

```