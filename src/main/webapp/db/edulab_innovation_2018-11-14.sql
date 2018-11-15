/*
 Navicat Premium Data Transfer

 Source Server         : Yank
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : edulab_innovation

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 14/11/2018 20:17:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_menu
-- ----------------------------
DROP TABLE IF EXISTS `edu_menu`;
CREATE TABLE `edu_menu`  (
  `menu_id` tinyint(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单描述',
  `menu_parent_id` int(5) NOT NULL COMMENT '菜单父菜单ID，如果是顶级菜单ID，其父菜单ID为0',
  `menu_create_time` datetime NULL DEFAULT NULL COMMENT '菜单创建时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for edu_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `edu_menu_role`;
CREATE TABLE `edu_menu_role`  (
  `id` tinyint(2) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '角色菜单流水号',
  `role_id` tinyint(2) NOT NULL COMMENT '角色唯一标识',
  `menu_id` tinyint(2) NOT NULL COMMENT '菜单唯一标识',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色菜单说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for edu_right
-- ----------------------------
DROP TABLE IF EXISTS `edu_right`;
CREATE TABLE `edu_right`  (
  `right_id` mediumint(3) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '权限唯一标识，主键',
  `right_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`right_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for edu_role
-- ----------------------------
DROP TABLE IF EXISTS `edu_role`;
CREATE TABLE `edu_role`  (
  `role_id` tinyint(2) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '角色唯一标识，主键',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `gen_time` datetime NOT NULL COMMENT '角色创建时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for edu_role_right
-- ----------------------------
DROP TABLE IF EXISTS `edu_role_right`;
CREATE TABLE `edu_role_right`  (
  `id` mediumint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '角色权限流水号',
  `role_id` tinyint(2) NOT NULL COMMENT '角色唯一标识',
  `right_id` mediumint(3) NOT NULL COMMENT '权限唯一标识',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `right_id`(`right_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for edu_user
-- ----------------------------
DROP TABLE IF EXISTS `edu_user`;
CREATE TABLE `edu_user`  (
  `user_id` mediumint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户的唯一标识，主键',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/upload/avatar/default_avatar.png' COMMENT '用户头像',
  `realname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户真实姓名',
  `motto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户个性签名',
  `birth` date NULL DEFAULT NULL COMMENT '用户生日',
  `gender` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '0-保密 1-男 2-女',
  `country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `academy` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院',
  `classes` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  `bankcard` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `register_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '127.0.0.1' COMMENT '注册IP',
  `register_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '注册时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近一次登录IP',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `update_time` datetime NOT NULL COMMENT '最近一次个人信息更新时间',
  `status` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '0-正常 1-禁用',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_user
-- ----------------------------
INSERT INTO `edu_user` VALUES (00000001, '/upload/avatar/default_avatar.png', '何腾洋', NULL, NULL, NULL, NULL, NULL, NULL, '计算机科学与技术学院', '软件1606', NULL, '10.10.5.130', '2018-11-08 01:58:04', '119.6.242.177', '2018-11-14 16:32:58', '2018-11-08 01:58:04', 0);

-- ----------------------------
-- Table structure for edu_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `edu_user_auth`;
CREATE TABLE `edu_user_auth`  (
  `authen_id` mediumint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户验证流水号',
  `user_id` mediumint(8) NOT NULL COMMENT '用户唯一标志，主键',
  `identity_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'stuid' COMMENT '登陆类型\r\nstuid-学号\r\nemail-邮箱\r\nphone-手机号\r\nQQ-第三方QQ\r\nwechat-第三方微信',
  `inside_login` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '0-内部登录 1-第三方登录',
  `identifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份',
  `salt` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户盐值',
  `credential` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户凭据',
  PRIMARY KEY (`authen_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_user_auth
-- ----------------------------
INSERT INTO `edu_user_auth` VALUES (00000001, 1, 'username', 0, 'admin', 'zUvF/7grGsUN5U8D5n+QVYaeB1S/ozLUCbB2uzi+kmM=', 'ACz5fuDklggXjI8VFjlqNLCpVNpT0dbeeyFKm6UkmeFOUacDviZDqXGsD40kjWbddvDKrGgxa/vdo5GFAzFkS9o=');

-- ----------------------------
-- Table structure for edu_user_role
-- ----------------------------
DROP TABLE IF EXISTS `edu_user_role`;
CREATE TABLE `edu_user_role`  (
  `id` mediumint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户角色流水号',
  `user_id` mediumint(8) NOT NULL COMMENT '用户唯一标识，主键',
  `role_id` tinyint(2) NOT NULL COMMENT '角色唯一标识，主键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

SET FOREIGN_KEY_CHECKS = 1;
