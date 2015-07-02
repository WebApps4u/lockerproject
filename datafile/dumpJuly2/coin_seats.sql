CREATE DATABASE  IF NOT EXISTS `coin` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `coin`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: coin
-- ------------------------------------------------------
-- Server version	5.5.24

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
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seats` (
  `ROW_NUMBER` char(1) DEFAULT NULL,
  `SEAT_NUMBER` int(11) DEFAULT NULL,
  `CLASS_TYPE` char(1) DEFAULT NULL,
  `SEAT_TYPE` char(1) DEFAULT NULL,
  `BOOKING_STATUS` varchar(10) DEFAULT NULL,
  `BOOKED_BY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES ('A',1,'F','W','BOOKED',1),('A',2,'F','A','BOOKED',1),('A',3,'F','A','BOOKED',2),('A',4,'F','W','BOOKED',3),('B',1,'F','W','AVAILABLE',NULL),('B',2,'F','A','AVAILABLE',NULL),('B',3,'F','A','AVAILABLE',NULL),('B',4,'F','W','AVAILABLE',NULL),('C',1,'F','W','AVAILABLE',NULL),('C',2,'F','A','AVAILABLE',NULL),('C',3,'F','A','AVAILABLE',NULL),('C',4,'F','W','AVAILABLE',NULL),('D',1,'F','W','AVAILABLE',NULL),('D',2,'F','A','AVAILABLE',NULL),('D',3,'F','A','AVAILABLE',NULL),('D',4,'F','W','AVAILABLE',NULL),('E',1,'F','W','AVAILABLE',NULL),('E',2,'F','A','AVAILABLE',NULL),('E',3,'F','A','AVAILABLE',NULL),('E',4,'F','W','AVAILABLE',NULL),('F',1,'E','W','AVAILABLE',NULL),('F',2,'E','M','BOOKED',4),('F',3,'E','A','AVAILABLE',NULL),('F',4,'E','A','AVAILABLE',NULL),('F',5,'E','M','BOOKED',5),('F',6,'E','W','AVAILABLE',NULL),('G',1,'E','W','AVAILABLE',NULL),('G',2,'E','M','AVAILABLE',NULL),('G',3,'E','A','AVAILABLE',NULL),('G',4,'E','A','AVAILABLE',NULL),('G',5,'E','M','AVAILABLE',NULL),('G',6,'E','W','AVAILABLE',NULL),('H',1,'E','W','AVAILABLE',NULL),('H',2,'E','M','AVAILABLE',NULL),('H',3,'E','A','AVAILABLE',NULL),('H',4,'E','A','AVAILABLE',NULL),('H',5,'E','M','AVAILABLE',NULL),('H',6,'E','W','AVAILABLE',NULL),('I',1,'E','W','AVAILABLE',NULL),('I',2,'E','M','AVAILABLE',NULL),('I',3,'E','A','AVAILABLE',NULL),('I',4,'E','A','AVAILABLE',NULL),('I',5,'E','M','AVAILABLE',NULL),('I',6,'E','W','AVAILABLE',NULL),('J',1,'E','W','AVAILABLE',NULL),('J',2,'E','M','AVAILABLE',NULL),('J',3,'E','A','AVAILABLE',NULL),('J',4,'E','A','AVAILABLE',NULL),('J',5,'E','M','AVAILABLE',NULL),('J',6,'E','W','AVAILABLE',NULL),('K',1,'E','W','AVAILABLE',NULL),('K',2,'E','M','AVAILABLE',NULL),('K',3,'E','A','AVAILABLE',NULL),('K',4,'E','A','AVAILABLE',NULL),('K',5,'E','M','AVAILABLE',NULL),('K',6,'E','W','AVAILABLE',NULL),('L',1,'E','W','AVAILABLE',NULL),('L',2,'E','M','AVAILABLE',NULL),('L',3,'E','A','AVAILABLE',NULL),('L',4,'E','A','AVAILABLE',NULL),('L',5,'E','M','AVAILABLE',NULL),('L',6,'E','W','AVAILABLE',NULL),('M',1,'E','W','AVAILABLE',NULL),('M',2,'E','M','AVAILABLE',NULL),('M',3,'E','A','AVAILABLE',NULL),('M',4,'E','A','AVAILABLE',NULL),('M',5,'E','M','AVAILABLE',NULL),('M',6,'E','W','AVAILABLE',NULL),('N',1,'E','W','AVAILABLE',NULL),('N',2,'E','M','AVAILABLE',NULL),('N',3,'E','A','AVAILABLE',NULL),('N',4,'E','A','AVAILABLE',NULL),('N',5,'E','M','AVAILABLE',NULL),('N',6,'E','W','AVAILABLE',NULL),('O',1,'E','W','AVAILABLE',NULL),('O',2,'E','M','AVAILABLE',NULL),('O',3,'E','A','AVAILABLE',NULL),('O',4,'E','A','AVAILABLE',NULL),('O',5,'E','M','AVAILABLE',NULL),('O',6,'E','W','AVAILABLE',NULL),('P',1,'E','W','AVAILABLE',NULL),('P',2,'E','M','AVAILABLE',NULL),('P',3,'E','A','AVAILABLE',NULL),('P',4,'E','A','AVAILABLE',NULL),('P',5,'E','M','AVAILABLE',NULL),('P',6,'E','W','AVAILABLE',NULL),('Q',1,'E','W','AVAILABLE',NULL),('Q',2,'E','M','AVAILABLE',NULL),('Q',3,'E','A','AVAILABLE',NULL),('Q',4,'E','A','AVAILABLE',NULL),('Q',5,'E','M','AVAILABLE',NULL),('Q',6,'E','W','AVAILABLE',NULL),('R',1,'E','W','AVAILABLE',NULL),('R',2,'E','M','AVAILABLE',NULL),('R',3,'E','A','AVAILABLE',NULL),('R',4,'E','A','AVAILABLE',NULL),('R',5,'E','M','AVAILABLE',NULL),('R',6,'E','W','AVAILABLE',NULL),('S',1,'E','W','AVAILABLE',NULL),('S',2,'E','M','AVAILABLE',NULL),('S',3,'E','A','AVAILABLE',NULL),('S',4,'E','A','AVAILABLE',NULL),('S',5,'E','M','AVAILABLE',NULL),('S',6,'E','W','AVAILABLE',NULL),('T',1,'E','W','AVAILABLE',NULL),('T',2,'E','M','AVAILABLE',NULL),('T',3,'E','A','AVAILABLE',NULL),('T',4,'E','A','AVAILABLE',NULL),('T',5,'E','M','AVAILABLE',NULL),('T',6,'E','W','AVAILABLE',NULL);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:29:18
