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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `hotplace_id` varchar(20) COLLATE utf8mb3_bin NOT NULL,
  `member_id` varchar(20) COLLATE utf8mb3_bin NOT NULL,
  `score` int DEFAULT NULL,
  `comment` text COLLATE utf8mb3_bin,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `review_image_name` text COLLATE utf8mb3_bin,
  `review_image_url` text COLLATE utf8mb3_bin,
  `helpful_count` int DEFAULT '0',
  PRIMARY KEY (`hotplace_id`,`member_id`),
  KEY `member_id_idx` (`member_id`),
  CONSTRAINT `hotplace_id` FOREIGN KEY (`hotplace_id`) REFERENCES `hotplace` (`hotplace_id`),
  CONSTRAINT `member_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES ('10592278','jimin',5,'최고로 친절하고 맛있는 잠실점이었어요!','2024-05-23 11:58:36','reviews/10592278/1716465509948-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F10592278%2F1716465509948-jimin.jpg?alt=media&token=fec9e9cd-6226-43ee-a87c-5e0d0a860ea1',0),('10952963','jimin',5,'봄이면 꼭 오는 여의도 한강 공원~~~ 한적한 곳도 꽤 많답니다!','2024-05-23 11:52:17','reviews/10952963/1716465128443-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F10952963%2F1716465128443-jimin.jpg?alt=media&token=3da5f5f7-bfb1-4a76-b6e3-c583a99c0490',0),('11520510','jimin',4,'남자친구와 나들이 갔다 왔어요! 제가 갔을때 케이블카가 고장나 장기 미운영중이라 둘러보기는 조금 힘들었네요! 참고하세요.','2024-05-23 11:25:36','reviews/11520510/1716463530722-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F11520510%2F1716463530722-jimin.jpg?alt=media&token=85e66e5c-7a77-4f83-b6be-9764a8e27779',0),('1336266407','jimin',5,'화끈하고 매운 화산 마라 전골 진짜 맛있어요! 친구들과 술먹기도 좋아요~~~ 추천추천','2024-05-23 11:23:12','reviews/1336266407/1716463385071-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F1336266407%2F1716463385071-jimin.jpg?alt=media&token=d6182474-36f8-48fb-ab09-59064aa9eb18',0),('16678341','jimin',4,'좋은 관람이었습니다. 생각보다 규모가 크니 편한 신발 추천!^!^!','2024-05-23 11:39:35','reviews/16678341/1716464367615-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F16678341%2F1716464367615-jimin.jpg?alt=media&token=3342852e-df2b-4671-9b9d-b607a300052f',0),('1677550004','jimin',5,'무한리필 돼지고기, 막창집 중에 1티어 입니다!!! 체인점이지만 어딜 가든 동일한 좋은 퀄리티를 제공해 제 단골집입니다 ㅎㅎ','2024-05-23 11:49:42','reviews/1677550004/1716464976430-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F1677550004%2F1716464976430-jimin.jpg?alt=media&token=e458a7d5-3bff-4434-985d-8c6263888c31',0),('17733090','jimin',3,'생각한것 보다 별로였어요. 수요에 비해 공급이 아쉽네요.','2024-05-23 11:26:50','reviews/17733090/1716463590963-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F17733090%2F1716463590963-jimin.jpg?alt=media&token=cb84fdfb-228b-4bb4-aa37-c4158fff6fbe',0),('1780698606','jimin',4,'감성 충만한 카페!!! 하지만 사람이 너무 많아요 ㅠㅠㅠ','2024-05-23 11:44:42','reviews/1780698606/1716464674295-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F1780698606%2F1716464674295-jimin.jpg?alt=media&token=e61d36fc-b264-473e-b421-9ae80afd72b4',0),('2017667910','jimin',5,'고도식은 제 최애 맛집이에요!!!','2024-05-23 11:20:30','reviews/2017667910/1716463302058-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F2017667910%2F1716463302058-jimin.jpg?alt=media&token=2f8a04bc-b41e-435d-9340-ead28b37751c',0),('2123663334','jaewon',5,'역시 모두가 인정하는 맛집입니다... 따봉!','2024-05-23 12:07:26','reviews/2123663334/1716466038857-jaewon.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F2123663334%2F1716466038857-jaewon.jpg?alt=media&token=372e7405-c8b0-4026-b5b1-def5e1e57ded',0),('2123663334','jimin',5,'이 집은 연돈과 맞먹습니다. 죽기 전에 꼭 먹어보세요','2024-05-23 11:35:47','reviews/2123663334/1716464139484-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F2123663334%2F1716464139484-jimin.jpg?alt=media&token=a1062520-f1f3-4a65-8691-f81d8c76dc65',0),('2123663334','mini',5,'여기서 카츠샌드를 파는데 너무 맛있어요!!! 강추','2024-05-23 12:03:28','reviews/2123663334/1716465801081-mini.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F2123663334%2F1716465801081-mini.jpg?alt=media&token=1a1436c2-f014-46cb-b2cc-b4dfa47b3463',0),('22016520','jimin',4,'볼거리는 많은데 교통편이 약간 아쉽네요 ^^;;','2024-05-23 11:24:14','reviews/22016520/1716463447154-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F22016520%2F1716463447154-jimin.jpg?alt=media&token=438522ef-2c2e-4a57-a104-5c45829a559d',0),('23578359','jimin',5,'제 최애인 \'옥탑방 고양이\'를 벌써 세번째 봤어요~ 매번 새로운 배우님들이지만 좋은 퀄리티를 보여주십니다. 앞으로도 오래오래 해주세요~~~','2024-05-23 11:42:24','reviews/23578359/1716464538060-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F23578359%2F1716464538060-jimin.jpg?alt=media&token=84a4443c-00ea-432e-a93e-9ab6f3d95d54',0),('25894201','jimin',5,'강아지와 함께한 즐거운 나들이었습니다 ^^','2024-05-23 11:50:39','reviews/25894201/1716465031767-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F25894201%2F1716465031767-jimin.jpg?alt=media&token=2cbeb7c7-b3d0-4e61-bfe9-325da45af586',0),('26822101','jimin',4,'큰 병원이라 관리가 잘 되어 있어요','2024-05-23 11:32:38','reviews/26822101/1716464017052-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F26822101%2F1716464017052-jimin.jpg?alt=media&token=25ac5e3e-7a61-4bf8-9211-a6a562214ecc',0),('27178420','jimin',5,'최고 최고. 이름값 합니다!!! (대신 인당 3~4만원 생각하고 가세요 ㅎㅎ)','2024-05-23 11:29:29','reviews/27178420/1716463762025-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F27178420%2F1716463762025-jimin.jpg?alt=media&token=c25092d4-e623-4128-a932-0dd738b2bc7f',0),('27235016','jimin',5,'역시 야구는 고척돔이지!!!','2024-05-23 11:55:08','reviews/27235016/1716465301408-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F27235016%2F1716465301408-jimin.jpg?alt=media&token=bf8dbf05-f948-44b0-98b5-4622c4e2f141',0),('399924140','jimin',5,'제가 디올을 참 좋아하는데, 좋은 경험이었어요~~~','2024-05-23 11:41:05','reviews/399924140/1716464457276-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F399924140%2F1716464457276-jimin.jpg?alt=media&token=80fbd2f3-9ccc-45a8-8710-efff7fdbb88a',0),('456860336','jimin',5,'입에서 살살 녹아요. 매일 신선한 재료로 만들어 주셔서 믿고 먹습니다!!!','2024-05-23 11:37:02','reviews/456860336/1716464215761-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F456860336%2F1716464215761-jimin.jpg?alt=media&token=1ff00131-5a0e-4c4e-9356-1f4d6ea0b326',0),('8012967','jimin',5,'멀고 먼 거제에서 몽돌 해수욕장은 또 색다르네요... 아주 아름다워요','2024-05-23 11:47:05','reviews/8012967/1716464821133-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F8012967%2F1716464821133-jimin.jpg?alt=media&token=a087343d-cbfe-4504-a028-f2bc54cb56c8',0),('8199114','jimin',4,'작년 더운 여름에 친구들과 가서 신나게 놀고 왔어요! 올해도 갈 의향 만땅입니다!!!','2024-05-23 11:48:13','reviews/8199114/1716464886262-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F8199114%2F1716464886262-jimin.jpg?alt=media&token=0b6a3f86-1041-4add-89bd-e0de79ccae2d',0),('926610061','jimin',4,'야구 보러 갔다 왔어요! 잠실이라 그런지 사람이 너무 많네요 ㅠㅠ','2024-05-23 11:27:51','reviews/926610061/1716463663337-jimin.jpg','https://firebasestorage.googleapis.com/v0/b/enjoytrip-4371c.appspot.com/o/reviews%2F926610061%2F1716463663337-jimin.jpg?alt=media&token=60c4d408-8fda-4b9a-8311-dfd288217a2c',0);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
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
