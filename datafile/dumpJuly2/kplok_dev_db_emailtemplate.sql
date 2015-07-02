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
-- Table structure for table `emailtemplate`
--

DROP TABLE IF EXISTS `emailtemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailtemplate` (
  `EMAILTEMPLATEID` int(11) NOT NULL,
  `FROMEMAIL` varchar(100) DEFAULT NULL,
  `TOLIST` varchar(255) DEFAULT NULL,
  `CCLIST` varchar(255) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `BODY` varchar(4000) DEFAULT NULL COMMENT 'Html formatted body. It should be having replacement variable as ${} only those variables would be replaced by the value.',
  `DATECREATED` date DEFAULT NULL,
  PRIMARY KEY (`EMAILTEMPLATEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores all the templates for which email is to be generated. The set up template cannot be changed.\nFor every new page or template, develoer has to add the template. Supports one template for each table.\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailtemplate`
--

LOCK TABLES `emailtemplate` WRITE;
/*!40000 ALTER TABLE `emailtemplate` DISABLE KEYS */;
INSERT INTO `emailtemplate` VALUES (1001,'deepansh1987@gamil.com','deepansh1987@gmail.com','bumbumjay@gmail.com','DSD Bill# ${BNO}','<p>Key Number ${KNO}</p><p>Loker No. ${LSNO}</p><p>&nbsp;${PNM1}</p><p>First Name:-&nbsp; ${PNM2}</p><p>The details of bill# ${BNO} is given below:<br /><br />Locker Rent: ${LAMT}</p><p>Service Tax: ${LSTXA}</p><p>Advance Paid: ${LADV}</p><p>Outstanding amount ${LOUT}<br />total payable: ${LPYBA}</p><p>Thank you for being with <strong>DSD</strong></p>',NULL);
/*!40000 ALTER TABLE `emailtemplate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:28:41
