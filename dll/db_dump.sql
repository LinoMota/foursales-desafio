-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: 0.0.0.0    Database: foursales
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','esquema inicial','SQL','V1__esquema_inicial.sql',48114839,'root','2025-03-31 05:39:40',100,1),(2,'2','usuarios novos','SQL','V2__usuarios_novos.sql',1229690407,'root','2025-03-31 05:39:40',3,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,1,46,2,599.98),(2,1,24,1,1999.99),(3,1,9,8,15999.92),(4,2,11,5,44999.95),(5,2,14,7,23099.93),(6,2,3,1,599.99),(7,3,39,8,7199.92),(8,3,38,9,719.10),(9,3,40,8,23999.92),(10,4,30,9,71999.91),(11,4,39,6,5399.94),(12,4,15,4,2799.96),(13,5,12,8,67999.92),(14,5,38,2,159.80),(15,5,40,10,29999.90),(16,6,26,9,3599.91),(17,6,45,6,7799.94),(18,6,16,1,799.99),(19,7,8,5,3999.95),(20,7,36,9,6299.91),(21,7,21,3,10799.97),(22,8,5,7,17499.93),(23,8,16,1,799.99),(24,8,28,5,5499.95),(25,9,44,3,2999.97),(26,9,50,9,22499.91),(27,9,38,1,79.90),(28,10,41,10,27999.90),(29,10,13,7,9099.93),(30,10,1,8,18399.92),(31,11,44,4,3999.96),(32,11,38,10,799.00),(33,11,6,7,3499.93),(34,12,7,10,159999.90),(35,12,4,9,7199.91),(36,12,28,2,2199.98),(37,13,11,6,53999.94),(38,13,30,9,71999.91),(39,13,26,3,1199.97),(40,14,43,4,1799.96),(41,14,24,8,15999.92),(42,14,32,4,39999.96),(43,15,11,7,62999.93),(44,15,31,1,6999.99),(45,15,14,3,9899.97),(46,16,34,6,35999.94),(47,16,21,8,28799.92),(48,16,38,10,799.00),(49,17,29,4,11999.96),(50,17,21,1,3599.99),(51,17,22,10,4599.90),(52,18,23,7,5599.93),(53,18,30,4,31999.96),(54,18,13,4,5199.96),(55,19,29,4,11999.96),(56,19,26,5,1999.95),(57,19,38,9,719.10),(58,20,28,10,10999.90),(59,20,27,5,94999.95),(60,20,3,2,1199.98),(61,21,45,3,3899.97),(62,21,14,10,32999.90),(63,21,35,5,6499.95),(64,22,1,9,20699.91),(65,22,5,6,14999.94),(66,22,20,8,39999.92),(67,23,12,10,84999.90),(68,23,22,8,3679.92),(69,23,44,1,999.99),(70,24,32,1,9999.99),(71,24,44,8,7999.92),(72,24,1,6,13799.94),(73,25,45,7,9099.93),(74,25,35,4,5199.96),(75,25,27,10,189999.90),(76,26,19,9,22499.91),(77,26,23,3,2399.97),(78,26,20,1,4999.99),(79,27,23,9,7199.91),(80,27,41,2,5599.98),(81,27,31,9,62999.91),(82,28,44,7,6999.93),(83,28,45,1,1299.99),(84,28,23,1,799.99),(85,29,9,5,9999.95),(86,29,43,10,4499.90),(87,29,36,5,3499.95),(88,30,30,9,71999.91),(89,30,40,6,17999.94),(90,30,13,7,9099.93),(91,31,46,9,2699.91),(92,31,36,8,5599.92),(93,31,17,10,45999.90),(94,32,11,3,26999.97),(95,32,24,1,1999.99),(96,32,17,3,13799.97),(97,33,33,9,10799.91),(98,33,21,10,35999.90),(99,33,49,8,15199.92),(100,34,17,9,41399.91),(101,34,14,10,32999.90),(102,34,31,2,13999.98),(103,35,30,2,15999.98),(104,35,32,7,69999.93),(105,35,50,10,24999.90),(106,36,39,4,3599.96),(107,36,5,8,19999.92),(108,36,27,3,56999.97),(109,37,14,6,19799.94),(110,37,40,9,26999.91),(111,37,44,8,7999.92),(112,38,28,1,1099.99),(113,38,7,7,111999.93),(114,38,15,4,2799.96),(115,39,43,4,1799.96),(116,39,8,9,7199.91),(117,39,13,8,10399.92),(118,40,4,3,2399.97),(119,40,21,7,25199.93),(120,40,43,7,3149.93),(121,41,11,8,71999.92),(122,41,13,10,12999.90),(123,41,43,8,3599.92),(124,42,6,10,4999.90),(125,42,17,1,4599.99),(126,42,37,10,45999.90);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `status` enum('PENDING','PAID','CANCELLED') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `final_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'PAID','2025-03-31 01:41:31',18599.89),(2,3,'PAID','2025-03-31 01:41:31',68699.87),(3,4,'PAID','2025-03-31 01:41:32',31918.94),(4,5,'PAID','2025-03-31 01:41:32',80199.81),(5,6,'PAID','2025-03-31 01:41:32',98159.62),(6,7,'PAID','2025-03-31 01:41:32',12199.84),(7,8,'PAID','2025-03-31 01:41:32',21099.83),(8,1,'PAID','2025-03-31 01:42:48',23799.87),(9,3,'PAID','2025-03-31 01:42:48',25579.78),(10,4,'PAID','2025-03-31 01:42:48',55499.75),(11,5,'PAID','2025-03-31 01:42:48',8298.89),(12,6,'CANCELLED','2025-03-31 01:42:48',169399.79),(13,7,'CANCELLED','2025-03-31 01:42:48',127199.82),(14,8,'PAID','2025-03-31 01:42:48',57799.84),(15,1,'PAID','2025-03-31 01:42:50',79899.89),(16,3,'PAID','2025-03-31 01:42:50',65598.86),(17,4,'PAID','2025-03-31 01:42:51',20199.85),(18,5,'CANCELLED','2025-03-31 01:42:51',42799.85),(19,6,'PAID','2025-03-31 01:42:51',14719.01),(20,7,'CANCELLED','2025-03-31 01:42:51',107199.83),(21,8,'CANCELLED','2025-03-31 01:42:51',43399.82),(22,1,'PAID','2025-03-31 01:42:52',75699.77),(23,3,'PAID','2025-03-31 01:42:52',89679.81),(24,4,'PAID','2025-03-31 01:42:52',31799.85),(25,5,'PAID','2025-03-31 01:42:52',204299.79),(26,6,'PAID','2025-03-31 01:42:52',29899.87),(27,7,'PAID','2025-03-31 01:42:52',75799.80),(28,8,'PAID','2025-03-31 01:42:52',9099.91),(29,1,'PAID','2025-03-31 01:42:53',17999.80),(30,3,'PAID','2025-03-31 01:42:53',99099.78),(31,4,'PAID','2025-03-31 01:42:53',54299.73),(32,5,'CANCELLED','2025-03-31 01:42:54',42799.93),(33,6,'PAID','2025-03-31 01:42:54',61999.73),(34,7,'CANCELLED','2025-03-31 01:42:54',88399.79),(35,8,'PAID','2025-03-31 01:42:54',110999.81),(36,1,'CANCELLED','2025-03-31 01:42:55',80599.85),(37,3,'CANCELLED','2025-03-31 01:42:55',54799.77),(38,4,'PAID','2025-03-31 01:42:55',115899.88),(39,5,'PAID','2025-03-31 01:42:55',19399.79),(40,6,'PAID','2025-03-31 01:42:55',30749.83),(41,7,'PAID','2025-03-31 01:42:55',88599.74),(42,8,'CANCELLED','2025-03-31 01:42:55',55599.79);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` enum('PERIFERICOS','AUDIO','MOVEIS','INFORMATICA','ELETRONICOS','MODA','ELETRODOMESTICOS','LIVROS','ACESSORIOS','GAMES','ESPORTES','AUTOMOTIVO','BRINQUEDOS','BELEZA','SAUDE','PETSHOP','FERRAMENTAS','PAPELARIA','ALIMENTOS') NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Monitor LG Ultragear 27','PERIFERICOS',2299.99,2),(2,'Teclado Mecânico Corsair K95','PERIFERICOS',1399.99,40),(3,'Mouse Razer DeathAdder V2','PERIFERICOS',599.99,99),(4,'Headset Logitech G Pro X','AUDIO',799.99,57),(5,'Cadeira Gamer Noblechairs Hero','MOVEIS',2499.99,7),(6,'Controle DualSense PS5','PERIFERICOS',499.99,73),(7,'Laptop Dell Alienware m15','INFORMATICA',15999.99,8),(8,'Gamepad Razer Wolverine V2','PERIFERICOS',799.99,36),(9,'Cadeira Gamer AKRacing','MOVEIS',1999.99,17),(10,'Placa de Vídeo AMD Radeon RX 6900 XT','INFORMATICA',14999.99,10),(11,'Smartphone iPhone 13 Pro','ELETRONICOS',8999.99,10),(12,'Notebook Lenovo Legion 5','INFORMATICA',8499.99,22),(13,'Fone de Ouvido Bose QuietComfort 35','AUDIO',1299.99,18),(14,'Cadeira Gamer SecretLab Omega','MOVEIS',3299.99,0),(15,'Teclado Mecânico Logitech G413','PERIFERICOS',699.99,67),(16,'Tênis Adidas Ultraboost','MODA',799.99,148),(17,'Geladeira Samsung Frost Free','ELETRODOMESTICOS',4599.99,0),(18,'Livro O Código Limpo','LIVROS',59.90,450),(19,'Relógio Garmin Forerunner 945','ACESSORIOS',2499.99,21),(20,'Console Xbox Series X','GAMES',4999.99,11),(21,'Monitor Samsung Odyssey G7','PERIFERICOS',3599.99,6),(22,'Teclado Mecânico HyperX Alloy FPS','PERIFERICOS',459.99,102),(23,'Mouse Logitech G Pro Wireless','PERIFERICOS',799.99,72),(24,'Fone de Ouvido Sony WH-1000XM4','AUDIO',1999.99,16),(25,'Cadeira Gamer DXRacer Racing','MOVEIS',2499.99,15),(26,'Controle Nintendo Switch Pro','PERIFERICOS',399.99,46),(27,'Laptop Apple MacBook Pro 16','INFORMATICA',18999.99,2),(28,'Gamepad Xbox Elite Series 2','PERIFERICOS',1099.99,49),(29,'Cadeira Gamer Noblechairs ICON','MOVEIS',2999.99,17),(30,'Placa de Vídeo ASUS ROG Strix RTX 3070','INFORMATICA',7999.99,0),(31,'Smartphone OnePlus 9 Pro','ELETRONICOS',6999.99,35),(32,'Notebook Acer Predator Helios','INFORMATICA',9999.99,6),(33,'Fone de Ouvido Jabra Elite 85h','AUDIO',1199.99,31),(34,'Cadeira Gamer Playseat Evolution','MOVEIS',5999.99,1),(35,'Teclado Mecânico Razer Huntsman Elite','PERIFERICOS',1299.99,56),(36,'Tênis Puma RS-X','MODA',699.99,128),(37,'Geladeira Electrolux Frost Free','ELETRODOMESTICOS',4599.99,10),(38,'Livro A Arte de Programar Computadores','LIVROS',79.90,259),(39,'Relógio Fossil Hybrid HR','ACESSORIOS',899.99,16),(40,'Console Nintendo Switch','GAMES',2999.99,1),(41,'Monitor BenQ Zowie XL2546','PERIFERICOS',2799.99,18),(42,'Teclado Mecânico SteelSeries Apex Pro','PERIFERICOS',1799.99,50),(43,'Mouse Corsair Dark Core RGB','PERIFERICOS',449.99,57),(44,'Fone de Ouvido JBL Quantum 800','AUDIO',999.99,27),(45,'Cadeira Gamer Respawn 110','MOVEIS',1299.99,26),(46,'Controle PS4 DualShock 4','PERIFERICOS',299.99,69),(47,'Laptop MSI GE66 Raider','INFORMATICA',15999.99,8),(48,'Gamepad PDP Afterglow','PERIFERICOS',199.99,100),(49,'Cadeira Gamer GT Omega Racing','MOVEIS',1899.99,7),(50,'Placa de Vídeo MSI GeForce GTX 1660','INFORMATICA',2499.99,16);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'normaluser','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER'),(2,'admin','$2a$10$zJmCDmCV/xfFKevUQfkA4uLY1kaq/QmBsIHYe4zYxs6NHAoYvMISO','ADMIN'),(3,'joaosilva','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER'),(4,'mariaoliveira','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER'),(5,'pedroalves','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER'),(6,'lucasrodrigues','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER'),(7,'carolcastro','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER'),(8,'andrebatista','$2a$10$uYmcOinJOofKcuPCJI4/oueFwDRrsqRXldQQuxQfhRVcCdGp4vQTG','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'foursales'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-31  2:26:31
