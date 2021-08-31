# ************************************************************
# Sequel Pro SQL dump
# バージョン 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# ホスト: 127.0.0.1 (MySQL 5.7.28)
# データベース: recipes
# 作成時刻: 2021-08-20 01:10:07 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# テーブルのダンプ articles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `articles`;

CREATE TABLE `articles` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '記事作成日時',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  `delete_flag` int(1) NOT NULL,
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '記事ID',
  `user_id` int(11) DEFAULT NULL COMMENT 'アカウントユーザーID',
  `article_title` char(30) DEFAULT NULL COMMENT '記事タイトル',
  `article_body` char(200) DEFAULT NULL COMMENT '記事本文',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;

INSERT INTO `articles` (`create_time`, `update_time`, `delete_flag`, `article_id`, `user_id`, `article_title`, `article_body`)
VALUES
	('2021-08-08 16:20:21','2021-08-18 12:36:27',1,1,NULL,'昨日の晩御飯','いつも通りの冷食で弁当を食べました'),
	('2021-08-08 19:57:57','2021-08-11 15:23:02',1,2,NULL,'きょうの料理でーーす！','いつも'),
	('2021-08-08 20:08:41','2021-08-15 14:55:16',1,3,NULL,'テスト投稿','今日はマグロのハンバーグを作りました'),
	('2021-08-08 20:09:10','2021-08-11 15:23:01',1,4,NULL,'きょうの料理！','たらこパスタ、サラダ'),
	('2021-08-08 20:10:29','2021-08-20 10:09:30',1,5,NULL,'冷食って美味しくて侮れない','肉じゃがを作成しました。圧力鍋で作ると美味しい'),
	('2021-08-08 20:18:11','2021-08-11 14:12:42',1,6,NULL,'今日の気づき','落ち着いて一つずつやることを考えたらできるということ'),
	('2021-08-18 12:37:21','2021-08-20 10:09:10',1,7,NULL,'今日の昼ごはん','冷凍しておいた、豚キムチ、小松菜のナムル、卵と小松菜の炒め物');

/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
