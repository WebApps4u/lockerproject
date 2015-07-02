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
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_userName` varchar(50) DEFAULT NULL,
  `u_password` varchar(50) NOT NULL,
  `u_dob` date DEFAULT NULL,
  `u_emailId` varchar(100) DEFAULT NULL,
  `u_createdOn` date DEFAULT NULL,
  `u_alive` char(1) DEFAULT NULL,
  `u_role` int(11) DEFAULT NULL,
  `u_contactNumber` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_userName` (`u_userName`),
  UNIQUE KEY `u_emailId` (`u_emailId`),
  KEY `u_role` (`u_role`),
  CONSTRAINT `user_details_ibfk_1` FOREIGN KEY (`u_role`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (1,'test_user','test','2012-12-13','testuser@test.com','2012-03-14','Y',2,'9891653996'),(2,'second_user','second','2012-11-13','testsecond@test.com','2012-03-14','Y',2,'8698659195'),(4,'deepansh','dee','1923-11-22','deeE',NULL,NULL,NULL,'9891653996'),(5,'Nidhi','nidhi123','1923-11-22','ndhbnsl@gmail.com',NULL,NULL,NULL,'8698655802'),(7,'Kartikay','kar123','1923-11-22','karl@gmail.com',NULL,NULL,NULL,'8698655802'),(8,'devanshu','123','1987-05-16','devanshu@gmail.com','2013-02-17',NULL,NULL,'8007401712'),(9,'Pooja','123','1987-03-21','pooja@gmail.com','2013-02-17',NULL,NULL,'8698659189'),(16,'moti','kutta','2013-01-01','moti@kutta.com','2013-02-18',NULL,NULL,'0987654321'),(18,'Rahul','123','1987-12-27','deepansh1987@gmail.com','2013-02-19',NULL,NULL,'9891653996');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:29:01
