-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 192.168.0.104    Database: project
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `phone` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '登录使用的手机号',
                        `username` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户名',
                        `password` char(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '密码',
                        `college` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                        `student_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                        `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                        `QQ` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '即时通讯',
                        `create_at` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建时间',
                        `goods_num` int DEFAULT '0' COMMENT '发布过的物品数量',
                        `power` int(10) unsigned zerofill DEFAULT '0000000100' COMMENT '信用分，普通用户默认为100',
                        `last_login` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '最近一次登陆时间',
                        `status` tinyint DEFAULT '0' COMMENT '账号是否冻结，默认0未冻结',
                        `isdel` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'17862821996','lduldj','8B119459242727E19DBE572ED4710019','421330323','2017-05-09 02:15',3,0000000100,NULL,1,0),(2,'15552201622','hly','C4CA4238A0B923820DCC509A6F75849B','782697347','2018-01-10 10:30',2,0000000100,NULL,1,0),(3,'15880330000','靓仔','C4CA4238A0B923820DCC509A6F75849B','1256972301','2017-05-17 10:35',2,0000000100,NULL,1,0),(4,'15880331111','user1','C4CA4238A0B923820DCC509A6F75849B','554625458','2018-02-14 17:35',2,0000000100,'',1,0),(5,'15880332222','user2','C4CA4238A0B923820DCC509A6F75849B','1025656543','2018-02-23 17:12',1,0000000100,NULL,1,0),(6,'15880333333','秦新敏','C4CA4238A0B923820DCC509A6F75849B','356456456','2018-02-14 17:35',1,0000000100,NULL,1,0),(7,'15880334444','user4','C4CA4238A0B923820DCC509A6F75849B','245534351','2018-02-14 17:48',3,0000000100,NULL,1,0),(8,'15880335555','user5','C4CA4238A0B923820DCC509A6F75849B','3145654664','2018-02-14 17:35',1,0000000100,NULL,1,0),(9,'15880336666','user6','C4CA4238A0B923820DCC509A6F75849B','554625458','2018-02-23 17:12',2,0000000100,NULL,1,0),(10,'15880337777','user7','C4CA4238A0B923820DCC509A6F75849B','95785588','2017-05-09 02:15',5,0000000100,NULL,1,0),(12,'15880338888','15880338888','C4CA4238A0B923820DCC509A6F75849B','2564645456','2018-04-16 17:37',1,0000000100,NULL,1,0),(23,'17853593223','小丽','202CB962AC59075B964B07152D234B70','357598890','2018-04-22 12:25',7,0000000100,NULL,1,0),(25,'13918621282','猿来入此','123456','1193284480','2019-03-13 13:41',1,0000000100,'127.0.0.1',0,0),(26,'13616565656','猿来入此1','123456',NULL,'2019-03-21 15:52',0,0000000100,NULL,1,0),(27,'17828167440','test','123456',NULL,'2023-01-29 11:29:15',0,0000000100,'127.0.0.1',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `image` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '图片主键',
                         `goods_id` int NOT NULL COMMENT '商品外键',
                         `img_url` text NOT NULL COMMENT '图片链接',
                         PRIMARY KEY (`id`),
                         KEY `goods_id` (`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'nopic1.png'),(2,2,'nopic2.png'),(3,3,'nopic3.png'),(4,4,'nopic4.png'),(5,5,'nopic5.png'),(6,6,'nopic6.png'),(7,7,'nopic7.png'),(8,8,'nopic8.png'),(9,9,'nopic9.png'),(11,11,'nopic11.png'),(12,12,'nopic12.png'),(13,13,'nopic13.png'),(16,16,'nopic16.png'),(17,17,'nopic17.png'),(18,18,'nopic18.png'),(20,20,'nopic20.png'),(21,77,'4f320b75-427a-484e-b725-a2f5d066af6f.jpg'),(78,78,'599629aa-9440-435c-8185-42e34a862470.jpg'),(79,79,'266abea1-a202-47f2-b5c4-095e96c10473.jpg'),(80,80,'4d472617-d430-4540-a510-f7606861dde0.jpg'),(81,81,'e62acc43-8122-421a-940b-a68e6aeadd30.jpg'),(82,82,'67a5f2ee-a09a-488d-9e7a-87ac6d8ab9c4.jpg'),(83,83,'110456d4-cc20-4fe2-b0a6-217b023d7dd6.jpg'),(84,84,'499afa59-53bc-4d23-99ef-5d0f9d60de69.jpg'),(85,85,'23f242e3-6dbf-4d46-a1b2-a620918908d9.jpg'),(86,86,'ae1097a5-736d-4850-9552-b5901ad6d84b.jpg'),(87,87,'95daa308-8011-4e0c-b5bd-8cd151830c32.jpg'),(88,88,'5c212d57-d8a4-4cc0-8853-dba957fba3d5.jpg'),(89,89,'ec5369f6-7c87-43db-98aa-112efa91c8c6.JPG'),(90,90,'fd680315-9ac8-4268-803e-7da7b492e8b0.JPG'),(91,91,'455e9ee5-92a7-4c7c-aa94-55f4373a16ee.JPG'),(92,92,'33434fa2-8bf3-4fbc-b1d9-d1e67202e2f2.jpg'),(93,93,'dc4f9591-bd1a-4eac-b1ea-be6412296fb6.png'),(94,94,'7eb89aaa-56c0-4283-893f-bd5718301d80.jpg'),(95,95,'6664b320-87d4-4d37-b14f-fc5dba41f529.PNG'),(96,96,'451c3aa2-529d-449d-b69f-b8771af1d806.jpg'),(97,97,'ee6e735c-6479-40bb-b45f-69888c42862e.jpg'),(98,98,'2f92136b-af5b-45f4-902d-7bb1d0ad0809.png'),(99,99,'022fe88a-5328-4b42-8333-10b782d39373.png'),(100,100,'a1f11b39-490b-42ef-a8e1-44285c4a80d4.png'),(101,101,'0f022547-b2cb-45fe-ab86-fb8b6d70adbc.jpg'),(102,102,''),(103,103,'b0933eb3-1ccb-43e1-80a4-1d50902916db.jpg'),(104,104,''),(105,105,'504d47df-32b0-4195-b63c-10d1562896cc.jpg'),(106,106,'a634ca2b-70cc-404d-95cb-fc4166fe6706.jpg'),(107,107,'1ba8f318-fbf3-42f6-b8d9-0baa7e678cec.jpg'),(108,108,'afd57674-338c-414f-98ae-78e0d25b12f8.jpg'),(109,109,'330a9a46-6434-4d04-a72b-a91a14035229.jpg'),(110,110,'0583cb1e-6acd-46da-bebc-4a9f1d6c3ac7.jpg'),(111,111,'ce18c0f4-dc10-454e-9791-e1992164a384.jpg'),(112,112,'854dff80-d049-4381-ae40-d1d23cd0e88b.jpg'),(113,113,''),(114,114,''),(115,115,'d75711a2-ce0d-487b-afc7-49fe94e75c54.jpg'),(116,116,'f0983943-2dea-4a19-a96f-989d6ee70780.png');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '订单表id',
                          `user_id` int NOT NULL COMMENT '用户id',
                          `goods_id` int NOT NULL COMMENT '商品id',
                          `order_num` bigint DEFAULT NULL COMMENT '订单编号',
                          `order_price` float(11,0) DEFAULT NULL COMMENT '订单价格',
  `order_state` int DEFAULT '1' COMMENT '订单状态 1待发货 2待收货 3已完成',
  `order_information` varchar(50) DEFAULT NULL,
  `order_date` varchar(50) DEFAULT NULL COMMENT '下单时间',
  `seller_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,11,78,85970353,10,3,'买给爸爸用的','2018-04-16 05:06:41',1),(5,1,107,70780568,40,1,'测试一下','2019-03-12 04:45:26',1),(6,25,108,70426421,10,3,'看来不错','2019-03-15 01:18:56',1),(7,26,115,28449530,128,1,'想要学习一下Java swing','2019-03-21 03:55:18',1),(8,26,116,11362720,216,2,'试试看','2019-03-21 04:43:21',1),(9,25,108,18028926,10,1,'挺便宜的！','2019-03-21 04:45:03',1),(10,25,111,306706,10,1,'这本书不错！','2019-03-21 04:45:34',1),(11,27,116,27775938,216,3,'还可以','2023-01-29 11:40:45',25);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `goods_informs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `goods_informs` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '举报表id',
                          `user_id` int NOT NULL COMMENT '举报用户id',
                          `goods_id` int NOT NULL COMMENT '商品id',
                          `inform_no` bigint DEFAULT NULL COMMENT '举报编号',
                          `inform_information` text COMMENT '举报内容',
                          `inform_time` datetime DEFAULT NULL COMMENT '举报时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `reply` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '评论回复主键',
                         `user_id` int DEFAULT NULL COMMENT '用户，外键',
                         `atuser_id` int DEFAULT NULL,
                         `commet_id` int DEFAULT NULL COMMENT '评论，外键',
                         `content` text COMMENT '回复内容',
                         `create_at` varchar(25) DEFAULT NULL COMMENT '回复时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `notice` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '系统信息主键',
                          `user_id` int DEFAULT NULL COMMENT '用户，外键',
                          `context` text COMMENT '信息内容',
                          `create_at` varchar(25) DEFAULT NULL COMMENT '推送信息时间',
                          `status` tinyint DEFAULT NULL COMMENT '状态，0未读，1已读',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (13,8,'啦啦啦啦','2018-04-20 18:05:40',0),(14,8,'可以了 交流吧 分页先不搞了','2018-04-20 18:06:04',0),(15,23,'有宿舍用的小桌子吗','2018-05-07 17:15:37',0),(16,23,'求一个USB台灯','2018-05-18 23:25:55',0),(17,25,'老夫子，我不要三寸金莲。','2019-03-15 13:40:45',0);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `goods` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '商品主键',
                         `catelog_id` int DEFAULT NULL COMMENT '商品分类，外键',
                         `user_id` int DEFAULT NULL COMMENT '用户外键',
                         `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
                         `price` float(11,2) DEFAULT NULL COMMENT '出售价格',
  `real_price` float(11,2) DEFAULT NULL COMMENT '真实价格',
  `start_time` varchar(25) DEFAULT NULL COMMENT '发布时间',
  `polish_time` varchar(30) DEFAULT NULL COMMENT '擦亮时间，按该时间进行查询，精确到时分秒',
  `end_time` varchar(25) DEFAULT NULL COMMENT '下架时间',
  `describle` text COMMENT '详细信息',
  `status` int DEFAULT '1' COMMENT '状态 上架1 下架0',
  `isdel` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `inform_num` int DEFAULT '0' COMMENT '被举报次数',
  PRIMARY KEY (`id`),
  KEY `catelog_id` (`catelog_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,6,1,'精美吉他',130.00,160.00,'2017-05-13','2017-05-13','2017-05-23','自用二手吉他，9成新，低价出售，有意者联系。',1,0),(2,2,1,'山地车',1520.00,890.00,'2017-05-13','2017-05-13','2017-05-23','八成新山地车，无损坏，喜欢Call我。',1,0),(3,3,1,'无线鼠标',23.00,48.00,'2017-05-13','2017-05-13','2017-05-23','罗技无线鼠标，自用一个月，9成新，手感好，反应灵敏。',1,0),(5,1,2,'数码相机',580.00,1350.00,'2017-05-14','2017-05-14','2017-05-24','自用的数码相机，一年前购买，非常爱好，无磕碰。可议价。',1,0),(6,1,2,'笔记本电脑',690.00,2300.00,'2017-05-14','2017-05-14','2017-05-24','7成新14寸笔记本电脑，商务本，适合办公，2G内存。',1,0),(7,3,2,'收纳盒',15.00,36.00,'2017-05-14','2017-05-14','2017-05-24','3层塑料物品收纳盒，毕业了，低价出售。',0,0),(8,3,1,'台灯',32.00,58.00,'2017-05-14','2017-05-14','2017-05-24','考研时购买的台灯，可插USB接口。',1,0),(9,5,2,'女鞋',35.00,86.00,'2017-05-14','2017-05-14','2017-05-24','学生女鞋',0,0),(11,1,3,'无线传呼机',230.00,370.00,'2017-05-14','2018-04-14','2017-05-24','一对无线传呼机，带有充电器，可以传呼2公里。',1,0),(12,1,3,'手提女包',36.00,89.00,'2017-05-14','2018-04-14','2017-05-24','手提女包，自用了几天。',1,0),(13,5,1,'手提包',15.00,23.00,'2017-05-14','2017-05-14','2017-05-24','自用手提包，8成新，便宜出。',1,0),(16,6,2,'耐克运动书包',56.00,198.00,'2017-05-14','2017-05-14','2017-05-24','去年年底购买的耐克书包，8成新，无损坏，可以装电脑，很漂亮，有意者联系。',1,0),(17,4,2,'二手小说',10.00,65.00,'2017-05-14','2017-05-14','2017-05-24','毕业季，一书架小说，便宜出售。',1,0),(18,4,2,'公务员考试资料',35.00,79.00,'2017-05-14','2017-05-14','2017-05-24','刚刚考完公务员的复习资料。淘宝购买，低价出售。',1,0),(20,1,4,'Thinkpad笔记本',1600.00,2300.00,'2017-05-14','2017-05-14','2017-05-24','京东购买的Thinkpad笔记本电脑，九成新。',1,0),(78,1,3,'大学生用计算器。',10.00,15.00,'2018-04-14','2018-04-14','2018-04-24','用一两次过，但没怎么用过。基本全新的',0,0),(79,1,3,'出HTC M9ET 3+32G',1500.00,2888.00,'2018-04-14','2018-04-14','2018-04-24','自用，自认为九成新，除了边角磕碰，其他都好，无暗病',1,0),(80,1,4,'二手苹果iphone6 6s',2488.00,4888.00,'2018-04-14','2018-04-14','2018-04-24','无锁 支持三网4G 成色9-99新 优先下单优先挑选靓成色发货 主板无限 无暗病 功能全部正常 诚信经营 以上均64G 需其他机型可私聊',1,0),(81,2,4,'168元出售全新自行车',168.00,899.00,'2018-04-14','2018-04-14','2018-04-24','要毕业了，低价出手吧 要得给我打电话',0,0),(82,2,5,'二手 电动车',1700.00,2580.00,'2018-04-14','2018-04-14','2018-04-24','羽铃电动车，购于2017年9月，个人原因想出，原价2850，现在二手出1700可刀，证牌齐全自用车，左后方车轮防护板断裂(如图)，车前头购买的时候就有划痕因为不影响使用就没叫老板换新的，一切破损不影响使用。',1,0),(83,2,6,'全新山地车特价：现价380元，原价498元，21速碟刹加厚铝合金轮子',398.00,498.00,'2018-04-14','2018-04-14','2018-04-24','自身原因起不了。全新山地车特价：现价380元，原价498元，21速碟刹加厚铝合金轮子',1,0),(84,3,7,'毕业离校，低价出售空调，',800.00,1846.00,'2018-04-14','2018-04-14','2018-04-24','13年购入的长虹空调，加了2米铜管，1匹冷暖型',1,0),(85,1,7,'头发剪了，出手吹风机',15.00,35.00,'2018-04-14','2018-04-14','2018-04-24','头发剪短了，现在不需要了，低价出售',1,0),(86,7,7,'单板吉他',688.00,1200.00,'2018-04-14','2018-04-14','2018-04-24','单板原价1200，买了两年了，弹过一个多月就搁置，毕业季出了。赠书三月通（30），赠一套弦（30）.',1,0),(87,7,8,'20出手辉柏嘉48色水溶性彩色铅笔一套',20.00,188.00,'2018-04-14','2018-04-14','2018-04-24','德国进口彩铅辉柏嘉，48色淘宝在60左右，我的买过来画了几张画就闲置了，彩票效果相当好，黑色用得比较快还剩一半多了',1,0),(88,6,9,'九成新仰卧板 便宜出售',50.00,150.00,'2018-04-14','2018-04-14','2018-04-24','没用多少次 带不走便宜卖了',1,0),(89,4,9,'艺乐杨幂刊',20.00,68.00,'2018-04-14','2018-04-14','2018-04-24','艺乐杂志 杨幂专刊 走进偶像 了解偶像背后的故事',1,0),(90,1,10,'傲慢与偏见',28.00,58.00,'2018-04-14','2018-04-18','2018-04-24','傲慢与偏见 百词斩阅读计划，全英文小说，词汇量2401-3200 正版全新 用百词斩软件扫描书内二维码可获取书籍配套全英文音频以及书籍配套词汇',0,0),(91,4,10,'刘语熙《我遇见我》',25.00,55.00,'2018-04-14','2018-04-14','2018-04-24','“乌贼刘”刘语熙自传我遇见我 讲述追梦历程',1,0),(92,5,10,'灰色短袖',12.00,30.00,'2018-04-14','2018-04-14','2018-04-24','刚买的，全新 全新 全新',1,0),(93,5,10,'帽子',10.00,25.00,'2018-04-14','2018-04-14','2018-04-24','买戴过',1,0),(94,3,1,'烧水壶',22.00,50.00,'2018-04-14','2018-04-14','2018-04-24','烧水壶功能一切都好，请放心使用',1,0),(95,3,1,'桌面吸尘器',28.00,59.00,'2018-04-14','2018-04-14','2018-04-24','桌面吸尘器，可以清洁键盘、吸纸屑、头发、灰尘，有蓝色和白色两款',1,0),(96,7,3,'纯手工绘画，来图定制，提供绘画过程图，追女友神器',108.00,NULL,'2018-04-14','2018-04-14','2018-04-24','纯手工绘画，来图定制，提供绘画过程，实木相框装裱，A4大小与12寸，64801606',1,0),(103,1,22,'排球',110.00,200.00,'2018-04-22','2018-04-22','2018-05-02','9成新',1,0),(105,1,23,'七七',1.00,1.00,'2018-04-22','2018-04-22','2018-05-02','没脑没胸',0,0),(106,5,23,'资生堂金瓶防晒霜',50.00,260.00,'2018-05-07','2018-05-18','2018-05-17','夏天必不可少的一款防晒霜，真的晒不黑哦。',1,0),(107,5,23,'露得清防晒喷雾',40.00,110.00,'2018-05-07','2018-05-07','2018-05-17','很方便的防晒喷雾，可以随身携带，随时喷一喷。而且这款喷雾很清爽，一点也不油腻。',0,0),(108,5,23,'太阳镜',10.00,30.00,'2018-05-07','2018-05-07','2018-05-17','可以用来遮阳的',0,0),(109,5,23,'傲滴 隐形眼镜护理液',30.00,60.00,'2018-05-07','2018-05-07','2018-05-17','一款超级舒服的隐形眼镜护理液。',1,0),(110,4,23,'《我们仨》杨绛',10.00,17.00,'2018-05-07','2018-05-07','2018-05-17','杨先生对他们家三人的记录。',1,0),(111,4,23,'《一切都是最好的安排》加措',10.00,20.00,'2018-05-07','2018-05-07','2018-05-17','诗人仓央嘉措的书，很美。',1,0),(112,1,23,'梅卡萨排球',200.00,300.00,'2018-05-07','2018-05-07','2018-05-17','国际专业排球比赛指定用球',0,0),(115,1,25,'Java swing 实现的汽车租赁系统',128.00,369.00,'2019-03-15','2019-03-21','2019-03-25','由【猿来入此】出品的一款Java swing实现的简单的汽车租赁管理系统源码和运行教程！',1,0),(116,4,25,'自习神器',216.00,2222.00,'2019-03-21','2019-03-21','2019-03-31','跟女朋友一起自习的神器！',1,1);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `focus`
--

DROP TABLE IF EXISTS `focus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `focus` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `goods_id` int DEFAULT NULL COMMENT '外键 商品id',
                         `user_id` int DEFAULT NULL COMMENT '外键 用户id',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `focus`
--

LOCK TABLES `focus` WRITE;
/*!40000 ALTER TABLE `focus` DISABLE KEYS */;
INSERT INTO `focus` VALUES (1,77,5),(4,94,4),(5,94,4),(6,94,4),(8,90,9),(9,90,22),(18,5,23),(19,85,23),(20,107,1),(33,115,25),(34,106,25),(35,103,25),(36,11,25),(37,116,25),(38,115,26);
/*!40000 ALTER TABLE `focus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(25) DEFAULT NULL COMMENT '账号',
                         `phone` bigint NOT NULL,
                         `password` varchar(25) NOT NULL COMMENT '密码',
                         `userRole` varchar(25) DEFAULT NULL COMMENT '角色',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'猿来入此',17828167440,'123456','超级管理员');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purse`
--

DROP TABLE IF EXISTS `purse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `purse` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '钱包id',
                         `user_id` int NOT NULL COMMENT '用户id',
                         `balance` float(11,0) unsigned zerofill DEFAULT '00000000000' COMMENT '总钱数',
  `recharge` float(11,0) DEFAULT NULL COMMENT '充值钱数',
  `withdrawals` float(11,0) DEFAULT NULL COMMENT '提现钱数',
  `state` int DEFAULT NULL COMMENT '状态 0未审核   已审核（1不通过 2通过）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purse`
--

LOCK TABLES `purse` WRITE;
/*!40000 ALTER TABLE `purse` DISABLE KEYS */;
INSERT INTO `purse` VALUES (1,1,00000000901,NULL,NULL,NULL),(2,2,00000001138,NULL,NULL,NULL),(3,3,00000002800,NULL,NULL,2),(4,4,00000007745,NULL,NULL,NULL),(5,5,00000000052,NULL,NULL,NULL),(6,11,00000001005,NULL,NULL,NULL),(13,18,00000000000,NULL,NULL,NULL),(14,19,00000000000,NULL,NULL,NULL),(15,20,00000000000,NULL,NULL,NULL),(16,21,00000001000,NULL,NULL,2),(17,22,00000000500,NULL,NULL,2),(18,23,00000001141,NULL,NULL,NULL),(19,24,00000000000,NULL,NULL,NULL),(20,25,00000001186,NULL,NULL,NULL),(21,26,00000000156,NULL,NULL,NULL),(22,27,00000199784,200000,NULL,2);
/*!40000 ALTER TABLE `purse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catelog`
--

DROP TABLE IF EXISTS `catelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `catelog` (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(30) DEFAULT NULL COMMENT '分类名',
                           `number` int DEFAULT '0' COMMENT '该分类下的商品数量',
                           `status` tinyint DEFAULT '0' COMMENT '分类状态，0正常，1暂用',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catelog`
--

LOCK TABLES `catelog` WRITE;
/*!40000 ALTER TABLE `catelog` DISABLE KEYS */;
INSERT INTO `catelog` VALUES (1,'闲置数码',10,1),(2,'校园代步',4,1),(3,'电器日用',7,1),(4,'图书教材',8,1),(5,'美妆衣物',11,1),(6,'运动棋牌',5,1),(7,'票券小物',5,1);
/*!40000 ALTER TABLE `catelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '评论主键',
                            `user_id` int DEFAULT NULL COMMENT '用户，外键',
                            `goods_id` int DEFAULT NULL COMMENT '商品，外键',
                            `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
                            `create_at` varchar(250) DEFAULT NULL COMMENT '评论时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (120,8,97,'厉害厉害~','2018-04-17 04:36:10'),(121,3,77,'1212','2018-04-18 12:02:27'),(122,6,90,'111','2018-04-20 11:37:45'),(123,23,103,'喜欢排球','2018-04-27 10:05:36'),(124,23,20,'不知道用了多久了','2018-05-16 01:11:46'),(125,23,110,'自己看过，很不错的一本书 。','2018-05-16 01:12:21'),(126,23,107,'HHH','2018-05-16 02:49:59'),(127,1,107,'sdfds','2019-03-12 04:45:11'),(128,25,106,'保质期多久呢？','2019-03-14 02:20:06'),(129,25,17,'这本书很不错','2019-03-21 05:10:56');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-29 12:33:14

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `announcement` (
                                 `today_recommend` text COMMENT '今日必看',
                                 `violation_info` text COMMENT '违规信息',
                                 `almanac` text COMMENT '每日黄历',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
