-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: base
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `NUM` int NOT NULL,
  `VOPROS` varchar(900) NOT NULL,
  PRIMARY KEY (`NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Классификации социальных взаимодействий, по какому признаку не существует?'),(2,'Верно ли утверждение “Существует лишь два основных типа социальных взаимодействий: сотрудничество и соперничество”?'),(3,'Какой формы социального взаимодействия не существует?'),(4,'Ключевыми критериями эффективности деятельности команды проекта являются:'),(5,'О каком критерии идет речь в описании “Этот критерий отражает энергетическую согласованность командных действий, менеджер проекта может относиться к этому критерию как к своего рода простому и быстрому тесту. Если кто-то из членов команды регулярно опаздывает, это знак того, что в будущем возможны проблемы с соблюдением сроков выполнения работ этим сотрудником. Менеджер проекта должен постоянно помнить, что дисциплина крайне важна.”?'),(6,'Важно ли для команды иметь единое представление о целях и задачах?'),(7,'Выберите правильно определение термина «команда»:'),(8,'Управление командой проекта включает в себя:'),(9,'Какие типы управления командой существуют:'),(10,'Выберите неверное определение термина «конфликт»:'),(11,'Как нельзя действовать при возникновении конфликта'),(12,'Можно ли предупредить конфликт с помощью увольнения одного из конфликтующих?'),(13,'Какого вида мотивации членов команды не существует?'),(14,'Выберите верное определение термина «мотивация»:'),(15,'Является ли благоприятный климат в команде мотивационным фактором?');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-20  6:48:32
