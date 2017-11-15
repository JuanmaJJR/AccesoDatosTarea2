-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2017 at 11:51 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `accesodatos2`
--

-- --------------------------------------------------------

--
-- Table structure for table `equipos`
--

CREATE TABLE `equipos` (
  `ID` int(5) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `Descripcion` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipos`
--

INSERT INTO `equipos` (`ID`, `nombre`, `Descripcion`) VALUES
(1, 'Real Madrid', 'El mejor equipo '),
(2, 'Barcelona', 'El peor equipo'),
(3, 'Oporto', 'Un equipo sin mas');

-- --------------------------------------------------------

--
-- Table structure for table `jugadores`
--

CREATE TABLE `jugadores` (
  `ID` int(4) NOT NULL,
  `Nombre` varchar(80) NOT NULL,
  `Apellido` varchar(80) NOT NULL,
  `Posicion` varchar(80) NOT NULL,
  `ID_Equipo` int(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores`
--

INSERT INTO `jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `ID_Equipo`) VALUES
(1, 'Guillermo', 'Tuvilla', 'Mid', 1),
(2, 'Cristiano', 'Ronaldo', 'Delantero', 1),
(3, 'Leonardo', 'Messi', 'Delantero', 2),
(4, 'Guillermo', 'Tuvilla', 'Mid', 1),
(5, 'Guillermo', 'Tuvilla', 'Mid', 1),
(6, 'Guillermo', 'Tuvilla', 'Mid', 1),
(7, 'Eduardo', 'Lafoz', 'Mid', 1),
(8, 'Guillermo', 'Tuvilla', 'Mid', 1),
(9, 'Cristiano', 'Ronaldo', 'Delantero', 1),
(10, 'Leonardo', 'Messi', 'Delantero', 2),
(11, 'Guillermo', 'Tuvilla', 'Mid', 1),
(12, 'Guillermo', 'Tuvilla', 'Mid', 1),
(13, 'Guillermo', 'Tuvilla', 'Mid', 1),
(14, 'Eduardo', 'Lafoz', 'Mid', 1),
(15, 'Guillermo', 'Tuvilla', 'Mid', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `equipos`
--
ALTER TABLE `equipos`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `jugadores`
--
ALTER TABLE `jugadores`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Equipo` (`ID_Equipo`),
  ADD KEY `ID_Equipo` (`ID_Equipo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `equipos`
--
ALTER TABLE `equipos`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `jugadores`
--
ALTER TABLE `jugadores`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `jugadores`
--
ALTER TABLE `jugadores`
  ADD CONSTRAINT `jugadores_ibfk_1` FOREIGN KEY (`ID_Equipo`) REFERENCES `equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
