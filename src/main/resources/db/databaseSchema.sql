-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: testdb.cbyg3qvb8wxh.us-east-2.rds.amazonaws.com    Database: am_flight_finder
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flightQuery`
--

DROP TABLE IF EXISTS `flightQuery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flightQuery` (
  `idFlightQuery` int(11) NOT NULL AUTO_INCREMENT,
  `queryDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `origin` varchar(10) NOT NULL,
  `destination` varchar(10) NOT NULL,
  `dateFrom` datetime NOT NULL,
  `dateTo` datetime NOT NULL,
  `gds` varchar(45) NOT NULL,
  PRIMARY KEY (`idFlightQuery`)
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flightResult`
--

DROP TABLE IF EXISTS `flightResult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flightResult` (
  `idFlightResult` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `airline` varchar(255) NOT NULL,
  `resultDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idFlightQuery` int(11) NOT NULL,
  PRIMARY KEY (`idFlightResult`),
  KEY `flightResult_flightQuery_idx` (`idFlightQuery`),
  CONSTRAINT `flightResult_flightQuery` FOREIGN KEY (`idFlightQuery`) REFERENCES `flightQuery` (`idFlightQuery`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=888 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flightTime`
--

DROP TABLE IF EXISTS `flightTime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flightTime` (
  `idFlightTime` int(11) NOT NULL AUTO_INCREMENT,
  `idTimeCombination` int(11) NOT NULL,
  `departure` datetime NOT NULL,
  `arrival` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`idFlightTime`),
  KEY `flightTime_timeCombination_idx` (`idTimeCombination`),
  CONSTRAINT `flightTime_timeCombination` FOREIGN KEY (`idTimeCombination`) REFERENCES `timeCombination` (`idTimeCombination`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3706 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `timeCombination`
--

DROP TABLE IF EXISTS `timeCombination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timeCombination` (
  `idTimeCombination` int(11) NOT NULL AUTO_INCREMENT,
  `idFlightResult` int(11) NOT NULL,
  PRIMARY KEY (`idTimeCombination`),
  KEY `timeCombination_flightResult_idx` (`idFlightResult`),
  CONSTRAINT `timeCombination_flightResult` FOREIGN KEY (`idFlightResult`) REFERENCES `flightResult` (`idFlightResult`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2397 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-21 21:24:43
