-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` varchar(20) COLLATE utf8mb3_bin NOT NULL,
  `member_password` varchar(80) COLLATE utf8mb3_bin NOT NULL,
  `member_name` varchar(20) COLLATE utf8mb3_bin NOT NULL,
  `member_email_id` varchar(20) COLLATE utf8mb3_bin NOT NULL,
  `member_email_domain` varchar(20) COLLATE utf8mb3_bin NOT NULL,
  `member_birthdate` varchar(10) COLLATE utf8mb3_bin DEFAULT NULL,
  `token` varchar(1000) COLLATE utf8mb3_bin DEFAULT NULL,
  `member_image_name` text COLLATE utf8mb3_bin,
  `member_image_url` text COLLATE utf8mb3_bin,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `membercol_UNIQUE` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin','$2a$10$9tka.GQ/E6.uznLU1nQXMuNeobAboUzcVYLzF14sPgOdtuWkZF0Z2','admin','admin','ssafy.com','1900-01-01',NULL,NULL,NULL),('jaewon','$2a$10$1vu4u91sNPDwnoybiVtFR.N9.UX1Ti2OLN6fRRu1myV9O5po4CWIi','jjojjo','jjojjo','gmail.com','1998-09-26','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzE2NDY1OTg3LCJleHAiOjE3MTkwNTc5ODcsIm1lbWJlcklkIjoiamFld29uIn0.72VuJQn6BYHnZD1BFZ2Ja159H4zF2uwiMapPvTD9MK0','members/1716465921373-jaewon.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/members%2F1716465921373-jaewon.jpg?alt=media&token=61b4cd03-8aa8-4f0a-9f4e-3446bf3291f8'),('jimin','$2a$10$6BSaGbQmApmw73JwpvdpouYhw9izDa.dCsBTHzfCu0n6A1XJdFwgy','dim','sjmrabbit','naver.com','2000-11-27',NULL,'members/1716461978204-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/members%2F1716461978204-jimin.jpg?alt=media&token=296a72ad-6d23-4efa-877f-32c2b8a04a36'),('mini','$2a$10$HHXy.UofW.f9aWIeqifCcOmlV4wM95BLdqyx3CphdN9HH5Ayzm3PW','minimi','jm001127','naver.com','1990-08-10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23 21:12:10
