/*
Navicat MySQL Data Transfer

Source Server         : localhost_root
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : security_test

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2021-06-02 18:25:27
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`
(
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence`
VALUES ('1');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint(20) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `pid`         bigint(20) DEFAULT NULL,
    `url`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission`
VALUES ('2', 'hello url', 'hello', null, '/hello');
INSERT INTO `sys_permission`
VALUES ('1', 'admin url', 'admin', null, '/admin');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`   bigint(20) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES ('1', 'admin');
INSERT INTO `sys_role`
VALUES ('2', 'guest');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `sys_role_id`       bigint(20) NOT NULL,
    `sys_permission_id` bigint(20) NOT NULL,
    KEY                 `FKmnbc71b4040rgprkv4aeu0h5p` (`sys_permission_id`),
    KEY                 `FK31whauev046d3rg8ecubxa664` (`sys_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission`
VALUES ('1', '2');
INSERT INTO `sys_role_permission`
VALUES ('2', '2');
INSERT INTO `sys_role_permission`
VALUES ('1', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`       bigint(20) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES ('1', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin');
INSERT INTO `sys_user`
VALUES ('2', 'E10ADC3949BA59ABBE56E057F20F883E', 'weiz');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `sys_user_id` bigint(20) NOT NULL,
    `sys_role_id` bigint(20) NOT NULL,
    KEY           `FK1ef5794xnbirtsnudta6p32on` (`sys_role_id`),
    KEY           `FKsbjvgfdwwy5rfbiag1bwh9x2b` (`sys_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES ('1', '1');
INSERT INTO `sys_user_role`
VALUES ('2', '2');