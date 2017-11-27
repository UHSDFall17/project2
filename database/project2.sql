-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: project2
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `EventInfo`
--

DROP TABLE IF EXISTS `EventInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eTitle` text,
  `eLocation` text,
  `eStart` datetime DEFAULT NULL,
  `eEnd` datetime DEFAULT NULL,
  `eDescription` text,
  `eOrgId` int(11) DEFAULT NULL,
  `ePrice` double DEFAULT NULL,
  `eAvailable` int(11) DEFAULT NULL,
  `eReserved` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventInfo`
--

LOCK TABLES `EventInfo` WRITE;
/*!40000 ALTER TABLE `EventInfo` DISABLE KEYS */;
INSERT INTO `EventInfo` VALUES (1,'event 1','uH','2017-11-24 17:18:00','2017-11-24 17:18:00','event1 description',1,7.25,20,0),(2,'event1','UH','2017-01-02 10:00:00','2017-01-03 10:00:00','this is event1',1,7,20,0),(3,'event1','UH','2017-01-02 10:00:00','2017-01-03 10:00:00','this is event1',1,7,20,0),(4,'event1','UH','2017-01-02 10:00:00','2017-01-03 10:00:00','this is event1',1,7,20,0),(5,'event3','UH','2017-10-01 10:00:00','2017-10-02 10:00:00','hello event1',1,100,20,0),(6,'event1','UH','2017-01-02 10:00:00','2017-01-03 10:00:00','this is event1',1,7,20,0),(7,'event3','UH','2017-10-01 10:00:00','2017-10-02 10:00:00','hello event1',1,100,20,0);
/*!40000 ALTER TABLE `EventInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserInfo`
--

DROP TABLE IF EXISTS `UserInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` text,
  `firstName` text,
  `lastName` text,
  `email` text,
  `password` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserInfo`
--

LOCK TABLES `UserInfo` WRITE;
/*!40000 ALTER TABLE `UserInfo` DISABLE KEYS */;
INSERT INTO `UserInfo` VALUES (1,'nour.smaoui','nour','smaoui','nour@cs.uh.edu','1000:824e6c27f6c5345c4001acd46717cd83:1641bee71cfa3f45');
/*!40000 ALTER TABLE `UserInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-26 10:03:41
