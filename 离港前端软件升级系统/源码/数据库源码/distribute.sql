/*
 Navicat Premium Data Transfer

 Source Server         : distribute
 Source Server Type    : MySQL
 Source Server Version : 50520
 Source Host           : 120.25.25.117:3306
 Source Schema         : distribute

 Target Server Type    : MySQL
 Target Server Version : 50520
 File Encoding         : 65001

 Date: 15/10/2020 10:00:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `DISTRIBUTE`;
CREATE DATABASE DISTRIBUTE DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE DISTRIBUTE;
-- ----------------------------
-- Table structure for t_corp
-- ----------------------------

DROP TABLE IF EXISTS `t_corp`;
CREATE TABLE `t_corp`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CORP_ID` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CORP_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CORP_NAME_PINYIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CORP_NATIONALITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_id` bigint(20) NULL DEFAULT NULL,
  `LEVEL` int(11) NOT NULL DEFAULT 1,
  `SEARCH_KEY` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CORP_ATTR` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_DELETED` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N',
  `CREATE_TIME` datetime NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_counter
-- ----------------------------
DROP TABLE IF EXISTS `t_counter`;
CREATE TABLE `t_counter`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `C_ID` bigint(20) NOT NULL,
  `C_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `C_IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `C_ID_fk_FILE`(`C_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_counter
-- ----------------------------
INSERT INTO `t_counter` VALUES (38, 1, '上海虹桥一号柜台', '10.0.175.205');
INSERT INTO `t_counter` VALUES (39, 2, '上海虹桥二号柜台', '192.122.152.5');
INSERT INTO `t_counter` VALUES (40, 3, '上海虹桥三号柜台', '192.168.186.3');
INSERT INTO `t_counter` VALUES (41, 4, '上海虹桥四号柜台', '193.148.144.2');
INSERT INTO `t_counter` VALUES (42, 5, '南京禄口机场一号柜台', '10.203.99.228');
INSERT INTO `t_counter` VALUES (43, 6, '南京禄口机场二号柜台', '255.255.255.0');
INSERT INTO `t_counter` VALUES (44, 7, '南京禄口机场三号柜台', '255.245.245.0');
INSERT INTO `t_counter` VALUES (45, 8, '盐城机场一号柜台', '10.203.98.228');
INSERT INTO `t_counter` VALUES (46, 9, '盐城机场二号柜台', '10.203.255.255');
INSERT INTO `t_counter` VALUES (47, 10, '安庆机场5号柜台', '10.203.93.225');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `WHEN` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `WHO` bigint(20) NULL DEFAULT NULL,
  `WHAT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `LOG_fk_VERSION`(`WHO`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES (30, '2020-10-09 22:32:40', 1, '首次更新');
INSERT INTO `t_log` VALUES (31, '2020-10-09 22:32:52', 2, '修复bug');
INSERT INTO `t_log` VALUES (32, '2020-10-09 22:33:05', 3, '优化页面');
INSERT INTO `t_log` VALUES (33, '2020-10-09 22:33:18', 4, '修复bug');
INSERT INTO `t_log` VALUES (34, '2020-10-09 22:33:34', 5, '增加功能');
INSERT INTO `t_log` VALUES (35, '2020-10-09 22:33:46', 6, '页面优化');
INSERT INTO `t_log` VALUES (36, '2020-10-09 22:33:56', 7, '配置文件修改');
INSERT INTO `t_log` VALUES (37, '2020-10-09 22:34:06', 8, '登录设置');
INSERT INTO `t_log` VALUES (38, '2020-10-09 22:34:21', 9, '新版本上市');
INSERT INTO `t_log` VALUES (39, '2020-10-09 22:34:30', 10, '修复bug');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ALIAS` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_PWD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_NAME_PINYIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NICK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_IMAGE` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IM_ACCOUNT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_AGE` tinyint(4) NULL DEFAULT NULL,
  `GENDER` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_TEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_ADDRESS` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IDTYPE` tinyint(4) NOT NULL DEFAULT 1,
  `IDCODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_NATIONALITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_TYPE` smallint(6) NULL DEFAULT NULL,
  `STATUS` smallint(6) NOT NULL,
  `EXPIRE_DATE` datetime NULL DEFAULT NULL,
  `IS_DELETED` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N',
  `USER_CORP_id` bigint(20) NULL DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `CORP_fk_USERT`(`USER_CORP_id`) USING BTREE,
  CONSTRAINT `CORP_fk_USERT` FOREIGN KEY (`USER_CORP_id`) REFERENCES `t_corp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', NULL, 'S8eJzK7aYeb0c+GFA5RlXw==', 'XZD', 'XianZhenDong', 'white', NULL, NULL, 'SuperMan', NULL, 18, '1', ' ', NULL, 1, '1', '1', 0, 1, '2080-07-28 00:31:05', 'N', NULL, '2019-07-28 00:31:29', '2020-08-29 16:31:06');

-- ----------------------------
-- Table structure for t_version
-- ----------------------------
DROP TABLE IF EXISTS `t_version`;
CREATE TABLE `t_version`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `V_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `V_DESCRIBE` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `V_NUMBER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `V_UPDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `V_NUMBER_fk_FILE`(`V_NUMBER`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_version
-- ----------------------------
INSERT INTO `t_version` VALUES (53, 'No.1', '第一次版本升级', '1', '2020-10-09 22:48:33');
INSERT INTO `t_version` VALUES (54, 'No.2', '第二次版本升级', '2', '2020-10-09 22:52:08');
INSERT INTO `t_version` VALUES (55, 'No.3', '在前几次版本的基础上进行更新', '3', '2020-10-09 23:01:07');
INSERT INTO `t_version` VALUES (56, 'No.4', '修复已知bug', '4', '2020-10-09 23:08:37');
INSERT INTO `t_version` VALUES (57, 'No.5', '第五次版本升级', '5', '2020-10-09 23:08:56');
INSERT INTO `t_version` VALUES (58, 'No.6', '修复第五次版本已知缺陷', '6', '2020-10-09 23:09:14');
INSERT INTO `t_version` VALUES (59, 'No.7', '进行第七次更新', '7', '2020-10-09 23:10:40');
INSERT INTO `t_version` VALUES (60, 'No.8', '增加流程过程', '8', '2020-10-09 23:10:56');
INSERT INTO `t_version` VALUES (61, 'No.9', '优化页面布局', '9', '2020-10-09 23:11:15');
INSERT INTO `t_version` VALUES (62, 'No.10', '优化用户体验', '10', '2020-10-09 23:11:38');

-- ----------------------------
-- Table structure for t_version_file
-- ----------------------------
DROP TABLE IF EXISTS `t_version_file`;
CREATE TABLE `t_version_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION_id` bigint(11) NOT NULL,
  `F_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `F_SIZE` int(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `VERSION_fk_FILE`(`VERSION_id`) USING BTREE,
  CONSTRAINT `VERSION_fk_FILE` FOREIGN KEY (`VERSION_id`) REFERENCES `t_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 204 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_version_file
-- ----------------------------
INSERT INTO `t_version_file` VALUES (195, 54, 'start01.bat', 16);
INSERT INTO `t_version_file` VALUES (196, 54, 'cki01.jar', 6663);
INSERT INTO `t_version_file` VALUES (197, 54, 'config01.ini', 27);
INSERT INTO `t_version_file` VALUES (199, 62, 'cki01.jar', 6663);
INSERT INTO `t_version_file` VALUES (200, 62, 'config01.ini', 27);
INSERT INTO `t_version_file` VALUES (201, 62, 'start01.bat', 16);
INSERT INTO `t_version_file` VALUES (202, 62, 'start.bat', 16);
INSERT INTO `t_version_file` VALUES (203, 62, 'config.ini', 27);

SET FOREIGN_KEY_CHECKS = 1;
