/*
 Navicat Premium Data Transfer

 Source Server         : 降尘数据管理平台生产服务器
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.101.41.233:3306
 Source Schema         : dust

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/06/2019 22:26:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dust_trouble
-- ----------------------------
DROP TABLE IF EXISTS `dust_trouble`;
CREATE TABLE `dust_trouble`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `happen_time` datetime(0) NOT NULL,
  `mn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trouble_code` int(255) NULL DEFAULT NULL,
  `trouble_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `solved` tinyint(1) NULL DEFAULT NULL,
  `solved_time` datetime(0) NULL DEFAULT NULL,
  `solved_method` int(255) NULL DEFAULT NULL,
  `trouble_shooter` int(255) NULL DEFAULT NULL COMMENT '故障解决人员（0：表示前端系统自己恢复）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
