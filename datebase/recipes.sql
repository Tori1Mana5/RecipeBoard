# ************************************************************
# Sequel Pro SQL dump
# バージョン 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# ホスト: 127.0.0.1 (MySQL 5.7.28)
# データベース: recipes
# 作成時刻: 2021-09-22 06:43:46 +0000
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
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  `delete_flag` int(1) NOT NULL COMMENT '有効フラグ',
  `report_date` date DEFAULT NULL COMMENT '記事作成日時',
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '記事ID',
  `user_id` varchar(11) DEFAULT '' COMMENT 'アカウントユーザーID',
  `article_title` varchar(30) DEFAULT NULL COMMENT '記事タイトル',
  `article_body` varchar(200) DEFAULT NULL COMMENT '記事本文',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;

INSERT INTO `articles` (`create_time`, `update_time`, `delete_flag`, `report_date`, `article_id`, `user_id`, `article_title`, `article_body`)
VALUES
  ('2021-08-08 16:20:21','2021-08-22 21:36:26',1,'2021-08-08',1,'tori','昨日の晩御飯','いつも通りの冷食のお弁当を食べました！'),
  ('2021-08-08 19:57:57','2021-09-07 17:46:55',1,'2021-08-08',2,'usagi','きょうの料理でーーす！','炊き込みご飯と味噌汁の献立です。'),
  ('2021-08-08 20:08:41','2021-08-22 14:58:32',1,'2021-08-08',3,'wanko','テスト投稿','今日はマグロのハンバーグを作りました'),
  ('2021-08-08 20:09:10','2021-08-22 14:58:35',1,'2021-08-08',4,'neko','きょうの料理！','たらこパスタ、サラダ'),
  ('2021-08-08 20:10:29','2021-09-17 13:37:22',1,'2021-08-08',5,'manako','冷食って美味しくて侮れない','肉じゃがを作成しました。圧力鍋で作ると美味しい'),
  ('2021-08-08 20:18:11','2021-08-23 09:53:01',1,'2021-08-08',6,'neko','今日の気づき','落ち着いて一つずつやることを考えたらできるということ'),
  ('2021-08-18 12:37:21','2021-09-17 13:37:25',1,'2021-08-08',7,'manako','今日の昼ごはん','冷凍しておいた、豚キムチ、小松菜のナムル、卵と小松菜の炒め物'),
  ('2021-08-22 14:28:48','2021-09-17 13:37:27',1,'2021-08-22',8,'manako','2021/08/22の昼メニュー','コメダ珈琲でミートソース、ミルクカフェオレを食べました。'),
  ('2021-08-31 23:15:13','2021-09-18 15:33:20',1,'2021-08-31',9,'manako','今日','豚丼を作りました！にんじんも入れると彩Good'),
  ('2021-09-11 13:44:02','2021-09-18 15:33:18',1,'2021-09-11',10,'wanko','びっくりドンキーでご馳走','いつもよりもいいハンバーグを頼みました'),
  ('2021-09-11 15:30:38','2021-09-18 15:33:25',1,'2021-09-11',11,'usagi','びっくりドンキー美味しいよね','いろどりセット初めて食べてみたけど美味しかった'),
  ('2021-09-12 23:58:53','2021-09-22 15:43:29',1,'2021-09-12',12,'neko','こっちも家でハンバーグ作ったわ','ハンバーグにはごぼう入りでシャキシャキの感触を楽しめるようにした'),
  ('2021-09-18 15:32:49','2021-09-18 15:32:49',1,'2021-09-18',13,'wanko','カップラーメンの日もある','シーフードヌードルとカレーヌードルのコラボを食べたよ！');

/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;


# テーブルのダンプ user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT 'アカウント名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT 'パスワード',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `name`, `password`)
VALUES
  (1,'manako','$2a$10$kz.cwf1Yz5QrN1cKtOaPz.QLOwgxVHqCIjeG391VExgBTqEJgquXe'),
  (5,'admin','$2a$10$HgHxSyJ0fjVzH8l.iDZbrOSlR/lRlu3kxT71M5gZTU4Vr6YTc7fKm'),
  (7,'testadmin','$2a$10$knlgLJGHlxkGCD31NM3THeGG8QJtKaQ5L0C59dCtZ4lJy2Hyfzwz2'),
  (8,'admin','$2a$10$r1fBoPLLEbsUOYCVlUpiE.VFWuTJhBLmHd.Ix53C0.JKQteg5eyVO'),
  (9,'admin','$2a$10$1QYjshakyjAkyxVjCFoumOqQ2j15eBlKFk7mgIz9kOmgohaEHnG7S'),
  (10,'wanko','$2a$10$tVbDSO/DVOc6iI.MkmrGIOfc8YALdLHbf9.CBEUTLbuuH291Gbqlm');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
