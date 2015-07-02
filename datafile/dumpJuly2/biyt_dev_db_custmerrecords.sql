CREATE DATABASE  IF NOT EXISTS `biyt_dev_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biyt_dev_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: biyt_dev_db
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
-- Table structure for table `custmerrecords`
--

DROP TABLE IF EXISTS `custmerrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `custmerrecords` (
  `customerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerEmail` varchar(255) DEFAULT NULL,
  `customerLocation` varchar(255) DEFAULT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `customerPhoneNumber` varchar(255) DEFAULT NULL,
  `customerPhoneNumber2` varchar(255) DEFAULT NULL,
  `customerType` varchar(255) DEFAULT NULL,
  `numberOfBookings` int(11) DEFAULT NULL,
  `referredBy` varchar(255) DEFAULT NULL,
  `registerationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custmerrecords`
--

LOCK TABLES `custmerrecords` WRITE;
/*!40000 ALTER TABLE `custmerrecords` DISABLE KEYS */;
INSERT INTO `custmerrecords` VALUES (4,'deepansh1987@gmail.com',NULL,'deepansh','9891653996',NULL,'NEW',NULL,NULL,'2014-10-11 10:21:34'),(5,'rahulcrick.goyal@gmail.com',NULL,'Rahul','contact numbe09211282828r',NULL,'NEW',NULL,NULL,'2014-10-28 11:22:50');
/*!40000 ALTER TABLE `custmerrecords` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:28:09
