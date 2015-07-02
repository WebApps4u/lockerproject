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
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choice` (
  `ans_set` varchar(5) DEFAULT NULL,
  `choice_num` char(1) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `correct` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES ('A11','A','China','Y'),('A11','B','Australia','N'),('A11','C','USA','N'),('A11','D','Canada','N'),('A12','A','China','N'),('A12','B','Australia','Y'),('A12','C','USA','N'),('A12','D','Canada','N'),('A13','A','China','Y'),('A13','B','Australia','N'),('A13','C','USA','N'),('A13','D','Canada','N'),('A21','A','China','Y'),('A21','B','Australia','N'),('A21','C','USA','N'),('A21','D','Canada','N'),('A22','A','China','Y'),('A22','B','Australia','N'),('A22','C','USA','N'),('A22','D','Canada','N'),('A23','A','China','Y'),('A23','B','Australia','N'),('A23','C','USA','N'),('A23','D','Canada','N'),('B11','A','China','Y'),('B11','B','Australia','N'),('B11','C','USA','N'),('B11','D','Canada','N'),('B12','A','China','Y'),('B12','B','Australia','N'),('B12','C','USA','N'),('B12','D','Canada','N'),('B13','A','China','Y'),('B13','B','Australia','N'),('B13','C','USA','N'),('B13','D','Canada','N');
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:29:14
