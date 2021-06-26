-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cengonline
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcements` (
  `idAnnouncements` int NOT NULL AUTO_INCREMENT,
  `idCourse` int NOT NULL,
  `announcementsText` text,
  `announcementDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idAnnouncements`,`idCourse`),
  UNIQUE KEY `idAnnouncements_UNIQUE` (`idAnnouncements`),
  KEY `fk_idCourse` (`idCourse`),
  CONSTRAINT `fk_idCourse` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
INSERT INTO `announcements` VALUES (19,1,'Geçirmekte olduğum rahatsızlık nedeniyle 11.03.20 Çarşamba için planlanan dersler iptal edilmiştir. İyi çalışmalar,','2020-02-13 21:00:00'),(20,1,'Merhaba aradaşlar, Bu süreçte uygulama dersleri ve ödevlerle ilgili bilgileri buradan sizinle paylaşıyor olacağım.Her hafta takip ederseniz iyi olacaktır.Bu haftaki uygulama dersi cuma sabah saat 11.00 de google meet üzerinden senkron olarak yapılacak.Derse ait sunumu da öncesinde sizinle paylaşıyor olacağım.Sağlıkla kalmanız dileğiyle','2020-02-14 21:00:00'),(21,1,'Merhaba, Bu şekilde durumlar olacak. Bu konuda tüm hocalarımız gibi, ben de elbette esneklik göstereceğim. Tek ricamız; iyi niyetle derslere devam etmeye çalışmanız, Derslerin (tam üst üste çakışmadığı sürece) bir miktar çakışmaları, internet bağlantısı sorunları, vb çeşitli nedenlerle derslerden anlık olarak düşmeniz ve benzeri sizin elinizde olmayan sorunlara biz esneklik göstereceğiz. İyi çalışmalar,','2020-02-15 21:00:00'),(22,1,'Bugün yaptığımız ders ile ilgili (ve varsa, önceki ders konuları ile ilgili) yarın (26.03.2020) 12:00 - 13:00 arasında Ofis Saati (Google Meet) açıyorum.','2020-02-16 21:00:00'),(23,1,'Merhaba arkadaslar bugünkü lab uyglaması için Google Meet bağlantısını paylaşıyorum.Lütfen bağlanmadan önce mikrofon ve kameralarınızı kapatın. https://meet.google.com/uot-juqb-dkb','2020-02-17 21:00:00'),(24,2,'Şu anda SAKAI ÖDEVLER tarafında YÜKLEME ALANI AÇILMIŞ OLMALI. ','2020-02-18 21:00:00'),(25,2,'BİTİRDİĞİNİZDE SAKAI YÜKLEYEMİYORSANIZ, ulas@cs.deu.edu.tr ADRESİNE GÖNDEREBİLİRSİNİZ... Duyurudaki gibi devam ediyoruz...','2020-02-18 21:00:00'),(26,2,'Please pay attention, Afternoon session schedule has been changed!!!! 13:15-14:15  > 1st session  14:15-14:30  > Break 14:30-15:30  > 2nd session','2020-02-19 21:00:00'),(27,2,'For section B, the lab session will be held tomorrow at 8.45, and then the quiz will starts at 10.00. For A, the quiz will starts at 10.30 and the lab sessions will be held after the quiz. For students in A, you shouldnt be late. Because section A will start as soon as branch B ends.','2020-02-20 21:00:00'),(28,3,'Sesimi duyan varsa.. Google Meet uygulamasıyla, online ders denemesine katılır mısınız?','2020-02-21 21:00:00'),(29,3,'İnternet hatlarındaki sıkışıklık nedeniyle, başarız bir denemeydi. Uygun bir zamanda dersin tekrarı için yeniden sizlere duyuru yapacağım. ','2020-02-22 21:00:00'),(30,3,'Sevgili gençler, 2019-2020 Bahar döneminde uzaktan öğretim yolu ile yapılan derslerin ara sınavlarının nasıl yapılacağı ve akademik takvimin güncellenmiş halini içeren DEÜ Senatosu karası aşağıdaki bağlantıdan erişebilirsiniz. Biz de bu dersimizde  Midterm Exam yerine yerine ilave bir \"HomeWork Assignment\" yapacağız. Ayrıntıları daha sonra duyuracağız. ','2020-02-23 21:00:00'),(31,3,'Arkadaşlar, Sınavlarlar ilgili olarak sizlerin göndermiş olduğunuz görüşlerinizi Akademik Kurul üyeleri ile paylaştım. Alınan karar ile, her dersin yapısına uygun olarak, vize sınavı yapılıp yapılmamasına dersi veren öğretim üyesinin kendisinin karar vermesi uygun bulundu. ','2020-02-24 21:00:00'),(32,4,'Arkadaşlar,Bölümde internet bağlantısı geldi. Saat 11:00de ikinci kısım için başlıyoruz. ','2020-02-25 21:00:00'),(33,4,'Lab#11 session will be held on 12 May Tuesday at 11.00 with Lab meeting URL. In this Friday, no lab session.','2020-01-31 21:00:00'),(34,4,'I will answer your questions about 3rd assignment in this Friday (1st May) between 18.00 and 18.45 on Lab meeting URL.','2020-02-10 21:00:00'),(35,4,'2019-2020 Bahar dönemi \"CME 2202 Data Organization and Management\" dersinin dönem sonu değerlendirme anketi ile geri bildirimlerinizi, düşüncelerinizi bizlere iletmenizi rica ediyoruz.','2020-02-11 21:00:00'),(36,5,'Final notları ektedir arkadaşlar. Dönem içi notlarınızı da DEBİSe girmiştik.Dönem sonu değerlendirme anketini doldurmayan arkadaşları geri bildirimlerini yapmaya davet ediyoruz :)','2020-02-12 21:00:00'),(37,5,'Arkadaşlar Merhaba,Bazı arkadaşlarımız özelden hangi sorularda yanlış yaptığını soruyor ya da cevapları yayımlamamızı istiyor. Bütünleme sınavı da benzer şekilde olacağı için soruları ve cevapları paylaşmayacağız. Başarı ortalamasının çok çok yüksek olduğu bir sınav oldu. Açıkçası, kendi aranızda cevaplarınızı karşılaştırıp, dersin sunumlarını da tekrar incelerseniz yanlışlarınızı bulabilirsiniz.Kolay gelsin.','2020-02-13 21:00:00'),(38,5,'Merhaba arkadaşlar,2019-2020 dönem arasında yapılan stajların değerlendirme sonuçları ektedir. Dönem arasında staj yapıp bu listede ismi olmayan ve değerlendirme notunda eksiklik belirtilen öğrenciler bizimle iletişime geçmelidir.','2020-02-14 21:00:00'),(39,5,'Pandemi koşullarında Staj Süreciyle ilgili Sorular ve Yanıtlara ekteki dosyadan erişebilirsiniz. Burada olmayan sorularınızı Staj dersine kaydolarak sorabilirsiniz.Ders kodu: 7i4p2se','2020-02-15 21:00:00'),(40,6,'Staj Yapma Zorunluluğu Bulunan ve Staj Yapmak İsteyen Öğrenciler,E-posta ile göndermek yerine/yanında stajınız ile ilgili sorularınızı Classroomda’ki Staj Dersi sayfasının \"7i4p2se\" şifresini girerek  yöneltebilirsiniz. Bu kanaldan daha hızlı yanıt alabilirsiniz.Ancak öncelikle, Staj Uygulama Esasları yönergesini ve yayınlanan son kararları okumanız gerekmektedir.','2020-02-16 21:00:00'),(41,6,'Değerli arkadaşlar, Birden fazla benzer soru geldiği için buradan paylaşıyorum.Bildiğimiz üzere şehirlerarası seyahat yasağı devam etmektedir. Eğer bu ve diğer yasaklar devam ederse Dekanlığın(veya Rektörlüğün) sınav takvimini erteleyeceğini düşünüyorum. Bu şahsi fikrim elbette, önümüzde bir ay var ve sürecin nasıl ilerleyeceğine bağlı olarak size duyurular yapılacaktır. ','2020-02-17 21:00:00'),(43,1,'duyuru','2020-06-24 18:47:39');
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `idAssignments` int NOT NULL AUTO_INCREMENT,
  `idCourse` int NOT NULL,
  `assignmentTitle` varchar(150) DEFAULT NULL,
  `assignmentsInstr` text,
  `assignmentsDue` text,
  PRIMARY KEY (`idAssignments`,`idCourse`),
  UNIQUE KEY `idAssignments_UNIQUE` (`idAssignments`),
  KEY `idCourse_fk_assngment` (`idCourse`),
  CONSTRAINT `idCourse_fk_assngment` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (2,1,'Packing Game 2','deneme','2020-07-16 00:00:00'),(4,2,'Homework 1','Write a program that takes three values (day, month, year) from the user and prints the followings if it is valid.','2020-05-05 00:00:00'),(8,2,'Homework 2','Write a program that takes an equation as string and the values of the variables x and y from the user and prints whether','2020-06-24 23:59:00'),(9,14,'MAN DESIGN','you should examine a packet sniffer tool in Cisco Packet Tracer','2020-06-12 00:00:00'),(10,8,'Airport Management System','The aim of the project is to design an airport management system in Java.','2020-04-8 12:59:00'),(11,15,'DHT11 TEMPERATURE AND','In this lab, you are required to complete only one experiment, using an Arduino',' 2020-05-9 00:00:00'),(14,1,'yeni assn','assn','2020-5-5 12:12');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachments` (
  `idStudent` int NOT NULL,
  `idAssignment` int NOT NULL,
  `attachAssignment` text,
  `grade` int DEFAULT '0',
  PRIMARY KEY (`idStudent`,`idAssignment`),
  KEY `fk_idAssignment` (`idAssignment`),
  CONSTRAINT `fk_idAssignment` FOREIGN KEY (`idAssignment`) REFERENCES `assignments` (`idAssignments`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_studentId` FOREIGN KEY (`idStudent`) REFERENCES `students` (`idStudent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
INSERT INTO `attachments` VALUES (1,9,'Ödev6',70),(1,10,'Ödev7',25),(2,2,'Ödev',85),(2,11,'Ödev8',87),(3,4,'Ödev3',75),(3,8,'Ödev5',22),(5,8,'Ödev4',65);
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `idComment` bigint NOT NULL AUTO_INCREMENT,
  `idPost` int unsigned NOT NULL,
  `sender` text,
  `commentText` text,
  `commentDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idComment`),
  KEY `idpost` (`idPost`),
  CONSTRAINT `idpost` FOREIGN KEY (`idPost`) REFERENCES `posts` (`idPost`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (18,14,'sukaplan@email.com','Merhaba hocam.','2020-07-08 11:14:15'),(19,15,'cagatayonal@email.com','Ödevde bazı örneklerde input’taki ay’ı yazıyla (ocak şubat gibi) bazılarında rakamla (1-12 ) yazılmış, bizde mi öyle tanımlayacağız hocam, birde hangi yıllar arasını tanımlamamız gerekiyor.','2020-06-08 19:45:07'),(20,15,'derya.birant@ceng.deu.edu.tr','Evet','2020-06-08 21:00:00'),(21,13,'sukaplan@email.com','deneme','2020-06-08 21:00:00'),(22,13,'sukaplan@email.com','deneme2','2020-06-08 21:00:00'),(23,13,'derya.birant@ceng.deu.edu.tr','yorum','2020-06-08 21:00:00');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_teacher`
--

DROP TABLE IF EXISTS `course_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_teacher` (
  `idCourse` int NOT NULL,
  `idTeacher` int NOT NULL,
  PRIMARY KEY (`idCourse`,`idTeacher`),
  KEY `idTeacher_fk_ct` (`idTeacher`),
  CONSTRAINT `idCourse_fk_ct` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idTeacher_fk_ct` FOREIGN KEY (`idTeacher`) REFERENCES `teachers` (`idTeachers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_teacher`
--

LOCK TABLES `course_teacher` WRITE;
/*!40000 ALTER TABLE `course_teacher` DISABLE KEYS */;
INSERT INTO `course_teacher` VALUES (1,1),(9,1),(16,1),(1,2),(14,2),(1,3),(2,3),(11,3),(5,4),(15,4),(6,5),(12,5),(8,6),(17,6),(4,7),(7,7),(4,8),(13,8),(17,8),(6,9),(11,9),(8,10),(15,10),(3,11),(10,11);
/*!40000 ALTER TABLE `course_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `idCourse` int NOT NULL AUTO_INCREMENT,
  `courseNameShort` varchar(45) DEFAULT NULL,
  `courseName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCourse`),
  UNIQUE KEY `courseNameShort_UNIQUE` (`courseNameShort`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'CME1252','Project Based  Learning'),(2,'CME1201','Algorithms and Programming'),(3,'CME1205','Discrete Computational Structures'),(4,'CME1203','Introduction to Computer Science'),(5,'CME1206','Technical English'),(6,'CME2205','Probability and Statictics'),(7,'CME2208','Numerical Analysis'),(8,'CME2210','Object Oriented Analysis '),(9,'CME2202','Data Organization and Management'),(10,'CME3205','Operating Systems'),(11,'CME3201','Database Management Systems'),(12,'CME3207','Signals and Systems for Computer Engineering'),(13,'CME3203','Theory of Computation'),(14,'CME3204','Data Communication and Network Design'),(15,'CME3208','Principles of Embedded Systems'),(16,'CME3206','Software Engineering'),(17,'CME3202','Concepts of Programming Languages');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `idCourse` int NOT NULL,
  `idStudent` int NOT NULL,
  PRIMARY KEY (`idCourse`,`idStudent`),
  KEY `enrollment_student_fk` (`idStudent`),
  CONSTRAINT `enrollment_course_fk` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `enrollment_student_fk` FOREIGN KEY (`idStudent`) REFERENCES `students` (`idStudent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (1,1),(2,1),(3,1),(6,1),(8,1),(9,1),(11,1),(13,1),(16,1),(1,2),(3,2),(4,2),(6,2),(8,2),(11,2),(13,2),(16,2),(1,3),(2,3),(6,3),(8,3),(11,3),(13,3),(16,3),(1,4),(3,4),(6,4),(11,4),(13,4),(16,4),(1,5),(6,5),(11,5),(13,5),(14,5),(16,5),(1,6),(6,6),(11,6),(13,6),(14,6),(16,6),(1,7),(3,7),(5,7),(11,7),(13,7),(14,7),(16,7),(1,8),(3,8),(5,8),(8,8),(14,8),(1,9),(3,9),(5,9),(8,9),(5,10),(8,10),(2,11),(5,11),(8,11),(10,11),(2,12),(3,12),(5,12),(7,12),(8,12),(10,12),(3,13),(5,13),(10,13),(3,14),(5,14),(10,14),(9,15),(10,15),(15,15),(2,16),(9,16),(10,16),(15,16),(17,16),(9,17),(15,17),(9,18),(15,18),(17,18),(15,19),(17,19),(9,20),(9,21),(9,22),(17,22),(12,24),(12,25),(14,25),(16,25),(4,26),(12,26),(14,26),(4,27),(12,27),(14,27),(4,28),(7,28),(12,28),(14,28),(4,29),(7,29),(14,29),(16,29),(4,30),(7,30),(14,30),(17,30),(7,31),(17,31),(2,32),(7,32),(17,32);
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `senderMail` text,
  `receiverMail` text,
  `title` text,
  `messageText` text,
  `date` timestamp NULL DEFAULT NULL,
  `messageID` bigint unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`messageID`),
  UNIQUE KEY `messageID` (`messageID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('sukaplan@email.com','farukozkan@email.com','Deneme Mail','Merhaba','2020-06-24 00:04:02',1),('berkaysert@email.com','cagatayonal@email.com','Test','123','2020-06-12 08:35:03',2),('sukaplan@email.com','berkaysert@email.com','Mail2','kdsjkfjg','2020-06-23 00:27:49',5),('sukaplan@email.com','derya.birant@ceng.deu.edu.tr','merhaba','merhaba su.','2020-06-23 00:30:08',6),('sukaplan@email.com','berkaysert@email.com','Yeni Mail','Merhaba\n','2020-06-23 16:06:20',7),('sukaplan@email.com','cagatayonal@email.com','Merhaba','Yeni Mail','2020-06-23 16:10:04',8),('derya.birant@ceng.deu.edu.tr','sukaplan@email.com',' merhaba','test12','2020-06-24 16:02:30',9),('sukaplan@email.com','berkaysert@email.com','konu','mesaj','2020-06-24 18:50:26',10);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `idPost` int unsigned NOT NULL AUTO_INCREMENT,
  `idCourse` int NOT NULL,
  `sender_mail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `postText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `postDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  KEY `posts_ibfk_1` (`idCourse`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (2,2,'ulasbirant@ceng.deu.edu.tr','Dönem sonu anketimizi paylaşıyorum, arkadaşlar...','2020-06-19 21:00:00'),(13,1,'derya.birant@ceng.deu.edu.tr','Term Project Final Grades (%25R1, %25R2, %20R3, %30R4)','2020-01-04 21:00:00'),(14,1,'gokhandalkılıc@ceng.deu.edu.tr','Ödevler başlığı altında göremeyenler: DERS İÇERİKLERİ altına da yüklendi... AYRICA ŞİMDİ TÜM ÖĞRENCİLERE DUYURU EKİ OLARAK DA GÖNDERİLDİ...','2020-06-15 12:35:22'),(15,1,'ulasbirant@ceng.deu.edu.tr','Vizenin uygulaması ile ilgili olarak SAKAI üzerine Duyuru yayınlandı. Okumuşsunuzdur ancak hala SAKAI\'yi takip etmeyen arkadaşlar varsa diye hatırlatmak istedim. İletişimimizi SAKAI üzerinden sürdürüyoruz','2020-05-07 09:14:33');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `idStudent` int NOT NULL AUTO_INCREMENT,
  `studentName` text NOT NULL,
  `studentSurname` text NOT NULL,
  `studentEmail` varchar(100) DEFAULT NULL,
  `studentPassword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStudent`),
  UNIQUE KEY `studentEmail_UNIQUE` (`studentEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Su','Kaplan','sukaplan@email.com','12345'),(2,'Berkay','Sert','berkaysert@email.com','12345'),(3,'Cagatay','Onal','cagatayonal@email.com','1234'),(4,'Faruk','Ozkan','farukozkan@email.com','1234'),(5,'Sinan','Ozel','sinanozel@email.com','1234'),(6,'İnci','Tastan','incitastan@email.com','1234'),(7,'Mehmet','Uslu','mehmetuslu@email.com','1234'),(8,'Berke','Kadam','berkekadam@email.com','1234'),(9,'Cem','Sinan','cemsinan@email.com','1234'),(10,'Fatih','Ersoy','fatihersoy@email.com','1234'),(11,'Deniz','Yıldırım','denizyıldırım@email.com','1234'),(12,'Kagan','Esbah','kaganesbah@email.com','1234'),(13,'Cagatay','Balıkcı','jonnkonstantin@email.com','1234'),(14,'Kalia','Cooper','est.congue.a@Loremipsumdolor.com','NSU72TMH3TG'),(15,'Basil','Ramsey','non@liberoInteger.ca','JDJ22BWK4ZF'),(16,'Xena','Pate','In.nec.orci@lacuspede.net','YPK16UKO9QY'),(17,'Jordan','Acosta','mi.felis.adipiscing@pedemalesuada.net','NWT00SOG5FP'),(18,'Ivor','Beck','velit.eu.sem@purusNullam.co.uk','BBJ82VRS8LG'),(19,'Kimberly','Cook','vulputate.mauris@nondapibus.org','FAH44VKM5NQ'),(20,'Jameson','Tanner','vitae.posuere@Integer.co.uk','CCW35CDX9UY'),(21,'Rashad','Mcpherson','sit.amet.consectetuer@Vivamussitamet.ca','FUJ04FPJ1BK'),(22,'Unity','Peck','luctus@Donecdignissim.edu','ECI35WLY5UY'),(23,'Sybil','Mccormick','vel@libero.ca','FPY03UDD2TH'),(24,'Moana','Holt','eu.erat@antedictummi.org','HMQ94LDC0UV'),(25,'Marshall','Cooper','Duis.sit@Donecfeugiat.edu','XIF96HBE7HC'),(26,'William','Richard','metus@accumsansed.co.uk','HIY20EOI0LZ'),(27,'Perry','Pratt','Vivamus.molestie.dapibus@Maurismolestiepharetra.org','UUG41WCC4PB'),(28,'Mariam','Parsons','amet@risus.net','QEY31NGQ8BR'),(29,'Katelyn','Byrd','ornare@vel.edu','PQX81CPP2HA'),(30,'Preston','Moreno','facilisis@utipsum.ca','DPE66FZK5MO'),(31,'Chelsea','Davidson','diam.Pellentesque@liberoIntegerin.org','RIL40MBW4VW'),(32,'Francis','Bonner','amet@sapiengravidanon.edu','HHG42TPR5ZD');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `idTeachers` int NOT NULL AUTO_INCREMENT,
  `TeachersName` varchar(45) NOT NULL,
  `TeachersSurname` varchar(45) NOT NULL,
  `TeachersEmail` varchar(100) NOT NULL,
  `TeachersPassword` varchar(45) NOT NULL,
  PRIMARY KEY (`idTeachers`),
  UNIQUE KEY `TeachersEmail_UNIQUE` (`TeachersEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'Derya','Birant','derya.birant@ceng.deu.edu.tr','123'),(2,'Ulas','Birant','ulasbirant@ceng.deu.edu.tr','123'),(3,'Gokhan','Dalkılıc','gokhandalkılıc@ceng.deu.edu.tr','123'),(4,'Adil','Alpkocak','adilalpkocak@ceng.deu.edu.tr','123'),(5,'Ali','Cüvitoglu','alicuvitoglu@ceng.deu.edu.tr','123'),(6,'Feriştah','Dalkılıc','feristahdalkılıc@ceng.deu.edu.tr','123'),(7,'Göksu','Tüysüzoglu','goksutuysuzoglu@ceng.deu.edu.tr','123'),(8,'İlker','Kalaycı','ilkerkalaycı@ceng.deu.edu.tr','123'),(9,'Semih','Utku','semihutku@ceng.deu.edu.tr','123'),(10,'Zerrin','Isık','zerrinisik@ceng.deu.edu.tr','123'),(11,'Özlem','Yerlikaya','özlemyerlikaya@ceng.deu.edu.tr','123'),(12,'Hilal','Özcanhan','hilalözcanhan@email.com','1234');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cengonline'
--

--
-- Dumping routines for database 'cengonline'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 22:31:51
