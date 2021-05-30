/*
 Navicat Premium Data Transfer

 Source Server         : luna-local
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : post-bar

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 30/05/2021 14:53:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_app
-- ----------------------------
DROP TABLE IF EXISTS `tb_app`;
CREATE TABLE `tb_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(32) NOT NULL COMMENT '应用编号',
  `api_key` varchar(32) NOT NULL COMMENT '应用key',
  `secret_key` varchar(32) NOT NULL COMMENT '应用密钥',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='百度ApiKey';

-- ----------------------------
-- Records of tb_app
-- ----------------------------
BEGIN;
INSERT INTO `tb_app` VALUES (1, '15436824', '6kkP0eWEu5rGss7IwW0DQr8s', 'jz0XbDKln7DO2Bep5c9rzDVtFgq3Xjq9', '2021-05-27 14:27:57', '2021-05-27 14:27:59', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_audio
-- ----------------------------
DROP TABLE IF EXISTS `tb_audio`;
CREATE TABLE `tb_audio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `audio_spd` int(1) NOT NULL DEFAULT '5' COMMENT '0-9',
  `audio_pit` int(1) NOT NULL DEFAULT '5' COMMENT '0-9',
  `audio_vol` int(2) NOT NULL DEFAULT '5' COMMENT '0-15',
  `audio_voi_per` int(1) NOT NULL DEFAULT '0' COMMENT '0-4',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='音频配置表';

-- ----------------------------
-- Records of tb_audio
-- ----------------------------
BEGIN;
INSERT INTO `tb_audio` VALUES (2, 1, 5, 5, 5, 3, '2021-05-28 17:46:50', '2021-05-29 17:17:45', 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `post_id` bigint(20) NOT NULL COMMENT '文章编号',
  `user_id` varchar(20) NOT NULL COMMENT '用户编号',
  `content` text NOT NULL COMMENT '评论内容',
  `audio` varchar(255) DEFAULT NULL COMMENT '评论音频',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='评论\n';

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
BEGIN;
INSERT INTO `tb_comment` VALUES (11, 25, '1', '你好中国', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/28/0c88701c31224ce6bff92e162c46daaa.mp3', '2021-05-28 22:01:06', '2021-05-28 22:01:06', 0);
INSERT INTO `tb_comment` VALUES (13, 25, '1', '你好中国', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/28/0c88701c31224ce6bff92e162c46daaa.mp3', '2021-05-28 22:01:06', '2021-05-28 22:01:06', 0);
INSERT INTO `tb_comment` VALUES (14, 25, '1', '你好中国', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/28/0c88701c31224ce6bff92e162c46daaa.mp3', '2021-05-29 10:57:10', '2021-05-29 10:57:10', 0);
INSERT INTO `tb_comment` VALUES (15, 25, '1', '不跟我们打王者，我觉得也是', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/23513aaf85254dab97188d29630191af.mp3', '2021-05-29 17:13:49', '2021-05-29 17:13:49', 0);
INSERT INTO `tb_comment` VALUES (16, 27, '1', '吃香锅呀', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/83b0310d65fa41998dc42c25529113bb.mp3', '2021-05-29 17:18:58', '2021-05-29 17:18:58', 0);
INSERT INTO `tb_comment` VALUES (17, 26, '1', '就在今天，罗杰不打王者', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/6ca418912f9b47a682d5afc0d5d6e368.mp3', '2021-05-29 17:24:38', '2021-05-29 17:24:38', 0);
INSERT INTO `tb_comment` VALUES (18, 24, '1', '哦哟！', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/9500fedbf13a4824a2fafbd3d9cf5172.mp3', '2021-05-29 17:25:36', '2021-05-29 17:25:36', 0);
INSERT INTO `tb_comment` VALUES (22, 23, '1', '我也爱中国', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/1133fab3a51145a2a618feae3b071e74.mp3', '2021-05-29 17:33:53', '2021-05-29 17:33:53', 0);
INSERT INTO `tb_comment` VALUES (24, 29, '1', '今日头条呀', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/f31c488ff1cd4b59a6f715d917917cc6.mp3', '2021-05-29 23:58:59', '2021-05-29 23:58:59', 0);
INSERT INTO `tb_comment` VALUES (25, 29, '1', '今日头条，明天的头条', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/658a0e21db594fbfb9aefe96dd073f0b.mp3', '2021-05-29 23:59:17', '2021-05-29 23:59:17', 0);
INSERT INTO `tb_comment` VALUES (26, 23, '1', '我爱中国', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/30/ab17df0d79e549139eb6d68d8672f219.mp3', '2021-05-30 13:57:58', '2021-05-30 13:57:58', 0);
INSERT INTO `tb_comment` VALUES (27, 30, '1', '祝福建党100周年', 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/30/acc772bb20904f798c76818a8831abae.mp3', '2021-05-30 14:00:42', '2021-05-30 14:00:42', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_comment_praise
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment_praise`;
CREATE TABLE `tb_comment_praise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `praise` int(11) DEFAULT NULL COMMENT '赞的数目?',
  `post_id` bigint(20) DEFAULT NULL COMMENT '文章编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `comment_id` bigint(20) DEFAULT NULL COMMENT '评论编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='评论扩展表';

-- ----------------------------
-- Records of tb_comment_praise
-- ----------------------------
BEGIN;
INSERT INTO `tb_comment_praise` VALUES (1, 10, 25, 1, 0, '2021-05-28 22:39:32', '2021-05-28 22:39:33', 0);
INSERT INTO `tb_comment_praise` VALUES (2, 39, 23, 1, 0, '2021-05-29 09:59:41', '2021-05-30 13:57:48', 39);
INSERT INTO `tb_comment_praise` VALUES (3, 1, 24, 1, 0, '2021-05-29 09:59:41', '2021-05-29 09:59:41', 0);
INSERT INTO `tb_comment_praise` VALUES (4, 5, 25, 1, 12, '2021-05-29 10:24:41', '2021-05-29 10:24:42', 0);
INSERT INTO `tb_comment_praise` VALUES (5, 6, 25, 1, 13, '2021-05-29 10:24:41', '2021-05-29 10:24:42', 0);
INSERT INTO `tb_comment_praise` VALUES (7, 0, 25, 1, 14, '2021-05-29 10:57:18', '2021-05-29 10:57:18', 0);
INSERT INTO `tb_comment_praise` VALUES (8, 12, 25, 1, 11, '2021-05-29 15:27:37', '2021-05-29 15:27:37', 0);
INSERT INTO `tb_comment_praise` VALUES (9, 0, 25, 1, 15, '2021-05-29 17:13:50', '2021-05-29 17:13:50', 0);
INSERT INTO `tb_comment_praise` VALUES (10, 0, 26, 1, 0, '2021-05-29 17:14:28', '2021-05-29 17:14:28', 0);
INSERT INTO `tb_comment_praise` VALUES (11, 33, 27, 1, 0, '2021-05-29 17:17:16', '2021-05-30 14:48:57', 33);
INSERT INTO `tb_comment_praise` VALUES (12, 0, 28, 1, 0, '2021-05-29 17:17:56', '2021-05-29 17:17:56', 0);
INSERT INTO `tb_comment_praise` VALUES (13, 9, 27, 1, 16, '2021-05-29 17:18:58', '2021-05-30 14:48:56', 9);
INSERT INTO `tb_comment_praise` VALUES (14, 0, 26, 1, 1, '2021-05-29 17:24:38', '2021-05-29 17:24:38', 0);
INSERT INTO `tb_comment_praise` VALUES (15, 7, 26, 1, 17, '2021-05-29 17:24:39', '2021-05-29 23:57:35', 7);
INSERT INTO `tb_comment_praise` VALUES (16, 0, 24, 1, 1, '2021-05-29 17:25:36', '2021-05-29 17:25:36', 0);
INSERT INTO `tb_comment_praise` VALUES (17, 0, 24, 1, 18, '2021-05-29 17:25:37', '2021-05-29 17:25:37', 0);
INSERT INTO `tb_comment_praise` VALUES (20, 0, 23, 1, 19, '2021-05-29 17:28:11', '2021-05-29 17:28:11', 0);
INSERT INTO `tb_comment_praise` VALUES (21, 5, 23, 1, 20, '2021-05-29 17:28:27', '2021-05-29 17:28:27', 0);
INSERT INTO `tb_comment_praise` VALUES (22, 0, 23, 1, 21, '2021-05-29 17:29:49', '2021-05-29 17:29:49', 0);
INSERT INTO `tb_comment_praise` VALUES (23, 56, 23, 1, 22, '2021-05-29 17:33:53', '2021-05-30 13:57:43', 56);
INSERT INTO `tb_comment_praise` VALUES (24, 27, 23, 1, 23, '2021-05-29 17:46:12', '2021-05-30 13:57:46', 27);
INSERT INTO `tb_comment_praise` VALUES (25, 10, 29, 1, 0, '2021-05-29 23:58:09', '2021-05-29 23:59:36', 10);
INSERT INTO `tb_comment_praise` VALUES (26, 8, 29, 1, 24, '2021-05-29 23:58:59', '2021-05-29 23:59:04', 8);
INSERT INTO `tb_comment_praise` VALUES (27, 0, 29, 1, 25, '2021-05-29 23:59:17', '2021-05-29 23:59:17', 0);
INSERT INTO `tb_comment_praise` VALUES (28, 6, 23, 1, 26, '2021-05-30 13:57:58', '2021-05-30 13:58:05', 6);
INSERT INTO `tb_comment_praise` VALUES (29, 0, 30, 1, 0, '2021-05-30 13:59:57', '2021-05-30 13:59:57', 0);
INSERT INTO `tb_comment_praise` VALUES (30, 8, 30, 1, 27, '2021-05-30 14:00:42', '2021-05-30 14:00:45', 8);
COMMIT;

-- ----------------------------
-- Table structure for tb_post
-- ----------------------------
DROP TABLE IF EXISTS `tb_post`;
CREATE TABLE `tb_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `post_title` varchar(16) NOT NULL COMMENT '文章标题',
  `post_text` text NOT NULL COMMENT '文章内容',
  `post_page_views` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文章阅读数',
  `post_audio` varchar(255) DEFAULT NULL COMMENT '文章音频',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='文章帖子\n';

-- ----------------------------
-- Records of tb_post
-- ----------------------------
BEGIN;
INSERT INTO `tb_post` VALUES (23, 1, '你好中国', '你好中国', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/28/0c88701c31224ce6bff92e162c46daaa.mp3', '2021-05-28 21:04:59', '2021-05-28 21:04:59', 0);
INSERT INTO `tb_post` VALUES (24, 1, '我爱赵文军', '我爱赵文军', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/28/68a06b79ec5f4accabfc44718a96f794.mp3', '2021-05-28 21:06:21', '2021-05-28 21:06:21', 0);
INSERT INTO `tb_post` VALUES (25, 1, '傻逼罗杰', '傻逼罗杰', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/28/c197b43e58ca4bfca50b6198f48ec986.mp3', '2021-05-28 21:07:25', '2021-05-28 21:07:25', 0);
INSERT INTO `tb_post` VALUES (26, 1, '日记', '今天是5-29号，放假的一天', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/644a71ce56d642139ac99feb0ba9e8d4.mp3', '2021-05-29 17:14:23', '2021-05-29 17:14:23', 0);
INSERT INTO `tb_post` VALUES (27, 1, '晚上吃什么', '马上吃完饭了，我已经点好了', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/568554425b1b4e9b8b4095f5f85f59e9.mp3', '2021-05-29 17:16:07', '2021-05-29 17:16:07', 0);
INSERT INTO `tb_post` VALUES (29, 1, '今日头条', '今日头条，生活还的继续', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/29/9ef547ecce49482e95a9d82e751439b4.mp3', '2021-05-29 23:57:58', '2021-05-29 23:57:58', 0);
INSERT INTO `tb_post` VALUES (30, 1, '建党百年', '读党史学中文爱中华，春雷一声红旗展，照亮地，照亮天。建党一百周年，祝福送给我们亲爱的党。祝党生日快乐，愿党以后的每一天都顺利、平安。', 0, 'http://127.0.0.1:8081//Users/luna/Document/project/post/2021/05/30/d51ef78f663143898b9c1ede895702b7.mp3', '2021-05-30 13:59:50', '2021-05-30 13:59:50', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_post_praise
-- ----------------------------
DROP TABLE IF EXISTS `tb_post_praise`;
CREATE TABLE `tb_post_praise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '创建编号',
  `post_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '文章编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章扩展表\n';

-- ----------------------------
-- Records of tb_post_praise
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_register
-- ----------------------------
DROP TABLE IF EXISTS `tb_register`;
CREATE TABLE `tb_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注册信息编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `sex` varchar(1) NOT NULL COMMENT '性别',
  `age` int(3) NOT NULL COMMENT '年龄',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='注册表';

-- ----------------------------
-- Records of tb_register
-- ----------------------------
BEGIN;
INSERT INTO `tb_register` VALUES (1, 1, '0', 29, 'luna_nov@163.com', 'http://127.0.0.1:8081/Users/luna/Document/project/post/2021/05/28/2606092767b340109a2d9f41e871f1bc.png', '2021-05-27 21:15:01', '2021-05-28 16:08:04', 3);
INSERT INTO `tb_register` VALUES (2, 14, '0', 14, 'luna_nov@163.com', NULL, '2021-05-27 21:15:33', '2021-05-27 21:15:33', 0);
INSERT INTO `tb_register` VALUES (3, 15, '0', 14, 'luna_nov@163.com', NULL, '2021-05-27 21:17:55', '2021-05-27 21:17:55', 0);
INSERT INTO `tb_register` VALUES (4, 16, '0', 14, 'luna_nov@163.com', NULL, '2021-05-27 21:19:02', '2021-05-27 21:19:02', 0);
INSERT INTO `tb_register` VALUES (5, 17, '0', 14, 'luna_nov@163.com', NULL, '2021-05-27 21:23:18', '2021-05-27 21:23:18', 0);
INSERT INTO `tb_register` VALUES (6, 18, '0', 14, 'luna_nov@163.com', NULL, '2021-05-27 21:23:18', '2021-05-27 21:23:18', 0);
INSERT INTO `tb_register` VALUES (7, 27, '1', 14, 'luna_nov@163.com', NULL, '2021-05-28 11:41:50', '2021-05-28 15:17:53', 1);
INSERT INTO `tb_register` VALUES (8, 28, '1', 14, 'luna_nov@163.com', NULL, '2021-05-28 11:42:18', '2021-05-28 11:43:30', 3);
INSERT INTO `tb_register` VALUES (9, 30, '1', 14, 'luna_nov@163.com', NULL, '2021-05-28 11:44:19', '2021-05-28 11:44:19', 0);
INSERT INTO `tb_register` VALUES (10, 29, '0', 14, 'luna_nov@163.com', NULL, '2021-05-28 11:45:18', '2021-05-28 11:45:18', 0);
INSERT INTO `tb_register` VALUES (11, 31, '1', 14, 'luna_nov@163.com', NULL, '2021-05-28 15:18:07', '2021-05-28 15:18:13', 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `admin` varchar(20) DEFAULT '0' COMMENT '是否管理员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'admin123', '8b283e8957f744ae5a1a6add05fc354f', '2021-05-30 13:57:24', '1', '2021-05-27 16:01:58', '2021-05-30 13:57:24', 12);
INSERT INTO `tb_user` VALUES (27, 'demoData1231', '8b283e8957f744ae5a1a6add05fc354f', NULL, '0', '2021-05-28 10:48:19', '2021-05-28 15:17:53', 5);
INSERT INTO `tb_user` VALUES (28, 'demoData', '8b283e8957f744ae5a1a6add05fc354f', NULL, '1', '2021-05-28 10:48:19', '2021-05-28 11:43:30', 4);
INSERT INTO `tb_user` VALUES (29, 'demoData', '8b283e8957f744ae5a1a6add05fc354f', NULL, '0', '2021-05-28 10:48:20', '2021-05-28 11:45:18', 1);
INSERT INTO `tb_user` VALUES (30, 'demoData', '74be16979710d4c4e7c6647856088456', NULL, '0', '2021-05-28 10:48:22', '2021-05-28 11:44:19', 1);
INSERT INTO `tb_user` VALUES (31, 'demoData', '74be16979710d4c4e7c6647856088456', NULL, '1', '2021-05-28 10:48:22', '2021-05-28 15:18:13', 2);
INSERT INTO `tb_user` VALUES (32, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:23', '2021-05-28 10:48:23', 0);
INSERT INTO `tb_user` VALUES (33, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:23', '2021-05-28 10:48:23', 0);
INSERT INTO `tb_user` VALUES (34, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:23', '2021-05-28 10:48:23', 0);
INSERT INTO `tb_user` VALUES (35, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:24', '2021-05-28 10:48:24', 0);
INSERT INTO `tb_user` VALUES (36, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:24', '2021-05-28 10:48:24', 0);
INSERT INTO `tb_user` VALUES (37, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:25', '2021-05-28 10:48:25', 0);
INSERT INTO `tb_user` VALUES (38, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:25', '2021-05-28 10:48:25', 0);
INSERT INTO `tb_user` VALUES (39, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:25', '2021-05-28 10:48:25', 0);
INSERT INTO `tb_user` VALUES (40, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:26', '2021-05-28 10:48:26', 0);
INSERT INTO `tb_user` VALUES (41, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:26', '2021-05-28 10:48:26', 0);
INSERT INTO `tb_user` VALUES (42, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:27', '2021-05-28 10:48:27', 0);
INSERT INTO `tb_user` VALUES (43, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:28', '2021-05-28 10:48:28', 0);
INSERT INTO `tb_user` VALUES (44, 'demoData', 'demoData', NULL, '1', '2021-05-28 10:48:29', '2021-05-28 10:48:29', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
