CREATE DATABASE  IF NOT EXISTS `kplok_dev_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `kplok_dev_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: kplok_dev_db
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
-- Table structure for table `lok_master_seq`
--

DROP TABLE IF EXISTS `lok_master_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lok_master_seq` (
  `idlok_all_seq` int(11) NOT NULL AUTO_INCREMENT,
  `object_type` varchar(45) NOT NULL COMMENT 'The table name for which sequence is to be created',
  `last_seq` int(11) NOT NULL DEFAULT '100000',
  `last_updated_by` varchar(45) DEFAULT NULL,
  `last_updated_date` date DEFAULT NULL,
  `object_intial` varchar(2) NOT NULL COMMENT 'It is the start which gets appended to the seq geneated. ',
  PRIMARY KEY (`idlok_all_seq`),
  UNIQUE KEY `object_type_UNIQUE` (`object_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='Generate sequences for all the tables. It stores last used sequence';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lok_master_seq`
--

LOCK TABLES `lok_master_seq` WRITE;
/*!40000 ALTER TABLE `lok_master_seq` DISABLE KEYS */;
INSERT INTO `lok_master_seq` VALUES (1,'billrecord',8649,NULL,NULL,'DD'),(2,'partyrecord',10058,NULL,NULL,'T-'),(3,'receiptrecord',7116,NULL,NULL,'R-'),(4,'customerdetails',1054,NULL,NULL,'C-'),(5,'securitydeposit',1004,NULL,NULL,'SD');
/*!40000 ALTER TABLE `lok_master_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:28:35
