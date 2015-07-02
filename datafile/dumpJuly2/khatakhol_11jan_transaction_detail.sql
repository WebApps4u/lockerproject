CREATE DATABASE  IF NOT EXISTS `khatakhol_11jan` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `khatakhol_11jan`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: khatakhol_11jan
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
-- Table structure for table `transaction_detail`
--

DROP TABLE IF EXISTS `transaction_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_detail` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_createdBy` int(11) DEFAULT NULL,
  `t_subject` varchar(50) DEFAULT NULL,
  `t_desc` varchar(500) DEFAULT NULL,
  `t_totalAmount` decimal(10,0) DEFAULT NULL,
  `t_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `t_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `t_type_id` (`t_type_id`),
  KEY `t_createdBy` (`t_createdBy`),
  CONSTRAINT `transaction_detail_ibfk_1` FOREIGN KEY (`t_type_id`) REFERENCES `transaction_type` (`t_type_id`),
  CONSTRAINT `transaction_detail_ibfk_2` FOREIGN KEY (`t_createdBy`) REFERENCES `user_details` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_detail`
--

LOCK TABLES `transaction_detail` WRITE;
/*!40000 ALTER TABLE `transaction_detail` DISABLE KEYS */;
INSERT INTO `transaction_detail` VALUES (1,1,'testing first transaction','this should be detail given',340,'2012-05-16 06:41:21',1),(2,4,'test i','testing nidhi module',400,'2012-12-14 18:30:00',1),(3,1,'test 2','iokyt gfrsg bnhg',350,'2012-12-14 18:30:00',1),(4,4,'by deepansh','ytrrtr hgyt juyu',500,'2012-12-14 18:30:00',1),(5,1,'Testing 5','Testing without myself',50,'2012-12-14 18:30:00',1),(6,1,'For mulitple friends','Checking with myself, 4 others, and 2 more friends',800,'2012-12-14 18:30:00',1),(7,5,'Food expenses','garlic bread from dominoz',100,'2012-12-14 18:30:00',1),(8,4,'testing day2','adding to test pool in',150,'2012-12-14 18:30:00',1),(9,18,'Testing with Rahul','Spent while travelling to nashik',300,'2013-02-19 14:20:20',1);
/*!40000 ALTER TABLE `transaction_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:29:02
