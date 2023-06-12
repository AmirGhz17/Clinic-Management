-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: clinic
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `birthDate` varchar(45) NOT NULL,
  `personalID` bigint NOT NULL,
  `password` varchar(45) NOT NULL,
  `adminPassword` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`,`randomID`,`personalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,990107211716762493,'amir','ghz','bbs','1510',123443213,'15101382','17'),(2,12345678998765444,'changiz','under','babolsar','82',34567899,'13821382','17'),(3,1335387847942901835,'abol','alijani','neka','1382',401302120,'amir1382','17'),(3,8463638399748750173,'hossein','abedini','chalus','83',123412345,'13831383','17'),(4,7184070148252637518,'mehrbod','mh','bobs','83',1234567888,'12345678','17');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `birthDate` varchar(45) NOT NULL,
  `personalID` varchar(20) NOT NULL,
  `salary` bigint NOT NULL,
  `nightShift` tinyint(1) NOT NULL,
  `organce` tinyint NOT NULL,
  `password` varchar(45) NOT NULL,
  `profession` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`,`randomID`,`personalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,234567890090098776,'default','default','default','1510','171717171',1234,1,0,'17171717','default'),(2,234563456789987656,'amir','ghazizade','bbs','1382','912456788',100,0,0,'17171718','heart'),(4,8398466280251394518,'mehrbod','mh','babolsar','82','912398766',100,0,0,'17171719','brain'),(5,8720826144913823987,'narj','gorji','bbl','1383','921355555',100,0,0,'17171716','leg'),(6,7269648665681877033,'abol','alijani','neka','13821','912311111',100,0,0,'17171715','nose'),(7,4388723530682861238,'sana','sadeghi','amol','2831','921344444',100,0,0,'17171714','skin'),(8,135934648272580300,'saman','khanli','chalus','13822','912368768',100,0,0,'17171713','eye'),(9,8448269608635759777,'armina','yazdn','amirkola','83','921379696',100,0,0,'17171712','bone');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `birthDate` varchar(45) NOT NULL,
  `personalID` varchar(20) NOT NULL,
  `salary` bigint NOT NULL,
  `nightShift` tinyint(1) NOT NULL,
  `organce` tinyint NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`randomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (1,8307452942910572481,'asal','aliz','sari','123456789','876543211',1234,1,1,'987654321');
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `birthDate` varchar(45) NOT NULL,
  `personalID` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`,`randomID`,`personalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,172345678909876555,'default','default','default','345678','4567898087','12344321'),(2,5178451248485180552,'Amir','Ghz','bbs','821015','4013021206','15101382'),(4,7429816522690582844,'narj','goje','bbl','34567','9029029029','13831383'),(5,2850500404576195655,'mmd','poulaei','bbl','1383','123456789','12345678'),(6,3245007084898188487,'changiz','under','sari','2345678','8765432345','234567890');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserve` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `patientID` bigint NOT NULL,
  `doctorID` bigint NOT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`,`randomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (1,435425348,1,1,'2022-06-10 07:41:11'),(2,8774607200274046751,1,1,'2023-06-14 20:30:00'),(3,3295394479931800350,1,1,'2023-06-15 04:30:00'),(4,158824086112715236,1,1,'2023-06-15 04:30:00'),(5,667247379191656533,1,1,'2023-06-14 20:30:00'),(6,4316416086349682389,1,1,'2023-06-15 04:30:00'),(7,7089344712203864921,1,1,'2023-06-15 04:30:00'),(8,8396559022504043319,1,1,'2023-06-15 05:15:00'),(9,2976869105668141624,1,1,'2023-06-15 05:45:00'),(10,3511008014291251501,1,1,'2023-06-16 04:30:00'),(11,5089512757840459356,1,1,'2023-06-16 05:00:00'),(12,655911563584331262,1,1,'2023-06-16 05:30:00'),(13,6938565012589466991,1,1,'2023-06-16 06:00:00'),(14,1038034888185075697,1,1,'2023-06-15 06:30:00'),(15,1082795345065667237,1,1,'2023-06-15 07:00:00'),(16,6196983122344137172,1,1,'2023-06-14 04:30:00'),(17,8631240805684646446,1,1,'2023-06-14 05:00:00'),(18,5290254264791221400,1,1,'2023-06-14 05:45:00'),(19,622647194368532345,1,1,'2023-06-16 06:15:00'),(20,7340236192463324976,1,1,'2023-06-15 07:15:00'),(21,6068657539132376955,1,1,'2023-06-15 07:30:00'),(22,592123674466532608,1,1,'2023-06-16 06:30:00'),(23,6702545340010984835,1,1,'2023-06-16 06:45:00'),(24,5402303368339034643,3,1,'2023-06-17 04:30:00'),(25,8047136634518382359,3,1,'2023-06-17 04:45:00'),(26,3188457944895676049,1,1,'2023-06-13 04:30:00'),(27,3779702623424958816,1,1,'2023-06-13 04:45:00'),(28,6561583624225107089,1,1,'2023-06-13 05:00:00'),(29,8768693929710381865,1,1,'2023-06-13 05:15:00'),(30,4735937693586641733,1,1,'2023-06-13 05:30:00'),(31,3101708875749355515,1,1,'2023-06-13 05:45:00'),(32,9110449193972742296,1,1,'2023-06-13 06:00:00'),(33,5105294169760317175,1,1,'2023-06-13 06:15:00'),(34,4631847695027182344,1,1,'2023-06-13 06:30:00'),(35,9134157790528102318,1,1,'2023-06-13 06:45:00'),(36,8566934994525669142,1,1,'2023-06-13 07:00:00'),(37,8936636175801960550,1,1,'2023-06-13 07:15:00'),(38,7083563250397047091,1,1,'2023-06-13 07:30:00'),(39,2230512779313806204,1,1,'2023-06-13 07:45:00'),(40,195251085738618814,1,1,'2023-06-13 08:00:00'),(41,8304307081975519239,1,1,'2023-06-13 08:15:00'),(42,5970106342712022506,1,1,'2023-06-13 08:30:00'),(43,4040265850150157817,1,1,'2023-06-13 08:45:00'),(44,5326051157600749891,1,1,'2023-06-13 09:00:00'),(45,6634815956489312233,1,1,'2023-06-13 09:15:00'),(46,7060899879256437233,1,1,'2023-06-13 09:30:00'),(47,5047553287790876197,1,1,'2023-06-13 09:45:00'),(48,8354942031657586460,1,1,'2023-06-13 10:00:00'),(49,4486425278528458349,1,1,'2023-06-13 10:15:00'),(50,7087540436563731783,5,1,'2023-06-13 10:30:00'),(51,7535049723407229408,1,2,'2023-06-13 04:30:00'),(52,5767626421631071124,1,2,'2023-06-13 04:45:00'),(53,8063894706006601833,1,2,'2023-06-14 04:30:00'),(54,5816445669970961439,1,2,'2023-06-14 04:45:00'),(55,8918867168030032049,1,2,'2023-06-14 05:00:00'),(56,5570902080383050328,1,2,'2023-06-14 05:15:00'),(57,8842342486202996194,1,2,'2023-06-14 05:30:00'),(58,1372765996542214251,1,2,'2023-06-14 05:45:00'),(59,2912933039635831406,1,2,'2023-06-14 06:00:00'),(60,5818012454081029604,1,2,'2023-06-14 06:15:00'),(61,8781578776522087961,1,2,'2023-06-14 06:30:00'),(62,6416734331983140604,1,2,'2023-06-14 06:45:00'),(63,3983906181839095395,1,2,'2023-06-14 07:00:00'),(64,8770876959759146426,1,2,'2023-06-14 07:15:00'),(65,9205231932261076419,1,2,'2023-06-14 07:30:00'),(66,5909790587967843265,1,2,'2023-06-14 07:45:00'),(67,7705603214936015752,1,2,'2023-06-14 08:00:00'),(68,3048597748822567051,1,2,'2023-06-14 08:15:00'),(69,926008220155580607,1,2,'2023-06-14 08:30:00'),(70,7970075952482413985,1,2,'2023-06-14 08:45:00'),(71,1850313255781028500,1,2,'2023-06-14 09:00:00'),(72,4182845818559150010,1,2,'2023-06-14 09:15:00'),(73,5615676119973447075,1,2,'2023-06-14 09:30:00'),(74,8453311977179265880,1,2,'2023-06-14 09:45:00'),(75,7872396983711982751,1,2,'2023-06-14 10:00:00'),(76,429756524483183882,1,2,'2023-06-14 10:15:00'),(77,5661053874172862783,1,2,'2023-06-14 10:30:00');
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `birthDate` varchar(45) NOT NULL,
  `personalID` varchar(20) NOT NULL,
  `salary` bigint NOT NULL,
  `nightShift` tinyint(1) NOT NULL,
  `organce` tinyint NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`randomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,1380217637039761733,'mmd','poula','bbl','234567','555555555',1234,1,1,'123459876');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visit` (
  `ID` bigint NOT NULL,
  `randomID` bigint NOT NULL,
  `patientID` bigint NOT NULL,
  `doctorID` bigint NOT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `drugPrescription` varchar(450) NOT NULL,
  `orders` varchar(450) NOT NULL,
  `fee` bigint NOT NULL,
  `diagnose` varchar(450) NOT NULL,
  PRIMARY KEY (`ID`,`randomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
INSERT INTO `visit` VALUES (1,153485345678987654,1,1,'2023-06-09 22:06:29','painkiller','rest',500,'stomachache'),(2,2345678909876567887,1,1,'2023-06-09 22:06:29','anesthetic','sleep',17,'toothache'),(3,5616938884076805714,1,1,'2023-06-09 22:10:08','eye drops','die',20,'headache'),(4,2773998087831891951,5,1,'2023-06-11 20:17:05','antifebrile','rest',17,'noidea'),(5,3292542079575939189,4,1,'2023-06-11 22:39:12','bandage','sleep',1234,'nobrain');
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-12 10:18:34
