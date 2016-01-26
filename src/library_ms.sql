# Host: 127.0.0.1  (Version: 5.5.40)
# Date: 2016-01-04 00:41:12
# Generator: MySQL-Front 5.3  (Build 4.120)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "book"
#

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '0',
  `price` float(6,2) DEFAULT '0.00',
  `collection_date` date DEFAULT NULL,
  `bookshelf` varchar(255) DEFAULT '',
  `status` int(9) DEFAULT '1',
  `kindID` int(11) DEFAULT NULL,
  `out_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

#
# Data for table "book"
#

/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'One Two Three... Infinity',20.00,'2015-12-16','A1',0,1,'2015-12-16','2015-01-16'),(2,'One Two Three... Infinity',20.00,'2015-12-16','B2',2,1,'2016-01-03','2016-02-02'),(3,'Thomas Calculus',500.00,'2016-01-01','C1',0,2,'2016-01-03','2016-02-02'),(4,'Harry Potty',200.00,'2016-01-03','B2',2,3,'2016-01-03','2016-02-02'),(5,'Harry Potty',200.00,'2016-01-03','B2',0,3,'2016-01-03','2016-01-03');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `cardID` int(10) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `studentID` int(11) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

#
# Data for table "user"
#

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,11510493,'admin',b'0',11510493,'123456'),(2,11510484,'陶子予',b'0',11510484,'68852874'),(3,11510492,'周智博',b'0',11510491,'68852874'),(4,11510491,'周智博',b'0',11510491,'68852874'),(6,11510494,'qiuyue',b'1',11510494,'123456'),(7,11510473,'赵青宇',b'0',11510473,'68852874'),(8,11510503,'Karry',b'1',11510503,'11510503');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
