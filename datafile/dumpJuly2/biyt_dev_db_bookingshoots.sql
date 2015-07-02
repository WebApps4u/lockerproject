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
-- Table structure for table `bookingshoots`
--

DROP TABLE IF EXISTS `bookingshoots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookingshoots` (
  `bookingID` bigint(20) NOT NULL AUTO_INCREMENT,
  `advancePaid` bigint(20) DEFAULT NULL,
  `bookingDate` datetime DEFAULT NULL,
  `bookingMadeBy` varchar(255) DEFAULT NULL,
  `bookingStatus` varchar(255) DEFAULT NULL,
  `customerEmail` varchar(255) DEFAULT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `immediateBooking` varchar(255) DEFAULT NULL,
  `numberOfDays` int(11) DEFAULT NULL,
  `numberOfPhotos` int(11) DEFAULT NULL,
  `paymentType` varchar(255) DEFAULT NULL,
  `planID` varchar(255) DEFAULT NULL,
  `priceRatePerPhoto` bigint(20) DEFAULT NULL,
  `selectedLocation` varchar(255) DEFAULT NULL,
  `shootDate` datetime DEFAULT NULL,
  `customerRecords_customerID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingshoots`
--

LOCK TABLES `bookingshoots` WRITE;
/*!40000 ALTER TABLE `bookingshoots` DISABLE KEYS */;
INSERT INTO `bookingshoots` VALUES (1,NULL,'2014-10-11 10:25:49','System','NEW','deepansh1987@gmail.com','deepansh','False',NULL,5,'pay_advance','Recommended',1000,'delhi','2014-01-27 00:12:00',4),(2,NULL,'2014-10-11 10:29:48','System','NEW','deepansh1987@gmail.com','deepansh','False',NULL,5,'pay_advance','Recommended',1000,'delhi','2014-01-27 00:12:00',4),(3,NULL,'2014-10-11 10:35:29','System','NEW','deepansh1987@gmail.com','deepansh','False',NULL,5,'pay_advance','Recommended',1000,'delhi','2014-01-27 00:12:00',4),(4,NULL,'2014-10-11 10:42:58','System','NEW','deepansh1987@gmail.com','deepansh','False',NULL,6,'pay_now','Recommended',1000,'delhi','2014-01-27 00:12:00',4),(5,NULL,'2014-10-11 10:43:52','System','NEW','deepansh1987@gmail.com','deepansh','False',NULL,6,'pay_now','Recommended',1000,'delhi','2014-01-27 00:12:00',4),(6,NULL,'2014-10-11 10:47:24','System','NEW','deepansh1987@gmail.com','deepansh','False',NULL,2,'pay_now','started',1000,'delhi','2014-01-27 00:12:00',4),(7,NULL,'2014-10-28 11:22:50','System','NEW','rahulcrick.goyal@gmail.com','Rahul','False',NULL,20,'pay_later','started',1000,'delhi and bangkok','0015-07-07 00:12:00',5);
/*!40000 ALTER TABLE `bookingshoots` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:28:08
