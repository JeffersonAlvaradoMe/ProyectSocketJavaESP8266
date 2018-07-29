-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-07-2018 a las 20:45:06
-- Versión del servidor: 10.1.24-MariaDB
-- Versión de PHP: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pulsos_corazon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_corazon`
--

CREATE TABLE `datos_corazon` (
  `cod_corazon` int(11) NOT NULL,
  `datos_x` int(10) NOT NULL,
  `datos_y` int(10) NOT NULL,
  `cod_paciente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `datos_corazon`
--

INSERT INTO `datos_corazon` (`cod_corazon`, `datos_x`, `datos_y`, `cod_paciente`) VALUES
(10, 1, 26, 1),
(11, 2, 56, 1),
(12, 3, 126, 1),
(13, 4, 36, 1),
(14, 5, 56, 1),
(15, 6, 96, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `cod_paciente` int(11) NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `cod_sexo` varchar(2) NOT NULL,
  `fecha_nacimiento` varchar(15) NOT NULL,
  `lugar_nacimiento` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`cod_paciente`, `nombres`, `apellidos`, `cedula`, `cod_sexo`, `fecha_nacimiento`, `lugar_nacimiento`) VALUES
(1, 'Mishell', 'Garcia', '0956021398', 'F', '2000-06-12', 'Guayaquil'),
(2, 'Joel', 'Mantuano', '0982691893', 'M', '1998-02-16', 'Florida');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sexo`
--

CREATE TABLE `sexo` (
  `cod_sexo` varchar(2) NOT NULL,
  `tipo_sexo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sexo`
--

INSERT INTO `sexo` (`cod_sexo`, `tipo_sexo`) VALUES
('F', 'Femenino'),
('M', 'Masculino');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `datos_corazon`
--
ALTER TABLE `datos_corazon`
  ADD PRIMARY KEY (`cod_corazon`),
  ADD KEY `cod_paciente` (`cod_paciente`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`cod_paciente`),
  ADD KEY `cod_sexo` (`cod_sexo`);

--
-- Indices de la tabla `sexo`
--
ALTER TABLE `sexo`
  ADD PRIMARY KEY (`cod_sexo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `datos_corazon`
--
ALTER TABLE `datos_corazon`
  MODIFY `cod_corazon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `cod_paciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datos_corazon`
--
ALTER TABLE `datos_corazon`
  ADD CONSTRAINT `datos_corazon_ibfk_1` FOREIGN KEY (`cod_paciente`) REFERENCES `pacientes` (`cod_paciente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD CONSTRAINT `pacientes_ibfk_1` FOREIGN KEY (`cod_sexo`) REFERENCES `sexo` (`cod_sexo`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
