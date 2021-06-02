/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : shaolin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-06-01 13:58:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT ' 主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '@PID|父节点id',
  `name` varchar(32) NOT NULL COMMENT ' 菜单名称',
  `project_id` varchar(32) DEFAULT NULL COMMENT '产品Id',
  `icon` varchar(64) DEFAULT NULL COMMENT ' 图标地址',
  `url` varchar(64) DEFAULT NULL COMMENT ' 请求地址',
  `orders` bigint(20) NOT NULL,
  `disable` int(4) DEFAULT '1',
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime NOT NULL DEFAULT '1970-01-01 23:59:59' COMMENT ' 创建时间',
  `utime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1173239515974184961', '0', '系统配置', '1,2', '', 'www.baidu.com', '0', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:17:53');
INSERT INTO `sys_menu` VALUES ('1173239582923665409', '0', '组织机构', '1', '', 'www.baidu.com', '1', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:18:08');
INSERT INTO `sys_menu` VALUES ('1173249204539555842', '0', '1', '1', '1', '1', '2', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:56:22');
INSERT INTO `sys_menu` VALUES ('1173251541903507457', '0', '1', '1', '1', '1', '1', '1', '1', null, '2019-09-15 23:05:40', '2019-09-15 23:05:40');
INSERT INTO `sys_menu` VALUES ('1173251747818668034', '0', '1', '1', '1', '1', '1', '1', '1', null, '2019-09-15 23:06:29', '2019-09-15 23:06:29');
INSERT INTO `sys_menu` VALUES ('1173251762452594690', '0', '1', '1', '1', '1', '1', '1', '1', null, '2019-09-15 23:06:33', '2019-09-15 23:06:32');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `id` bigint(20) NOT NULL COMMENT ' 主键',
  `project_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `disable` int(4) DEFAULT '1',
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime NOT NULL DEFAULT '1970-01-01 23:59:59' COMMENT ' 创建时间',
  `utime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单、权限表';

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('1173072688483078145', '1', '2', '1173072688441135106', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 11:14:58');
INSERT INTO `sys_menu_role` VALUES ('1173238455473770497', '1', '1', '1173238455385690114', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:13:40');
INSERT INTO `sys_menu_role` VALUES ('1173238607492124673', '1', '1', '1173238607458570241', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:14:16');
INSERT INTO `sys_menu_role` VALUES ('1173238607513096194', '2', '1', '1173238607458570241', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:14:16');
INSERT INTO `sys_menu_role` VALUES ('1173238607542456322', '3', '1', '1173238607458570241', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:14:16');
INSERT INTO `sys_menu_role` VALUES ('1173239516028710913', '1', '1', '1173239515974184961', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:17:53');
INSERT INTO `sys_menu_role` VALUES ('1173239516053876737', '2', '1', '1173239515974184961', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:17:53');
INSERT INTO `sys_menu_role` VALUES ('1173239582973997057', '1', '1', '1173239582923665409', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:18:08');
INSERT INTO `sys_menu_role` VALUES ('1173249204602470402', '1', '1', '1173249204539555842', '1', null, null, '1970-01-01 23:59:59', '2019-09-15 22:56:22');
INSERT INTO `sys_menu_role` VALUES ('1173251546227834881', '1', '1', '1173251541903507457', '1', '1', null, '2019-09-15 23:05:41', '2019-09-15 23:05:41');
INSERT INTO `sys_menu_role` VALUES ('1173251747889971202', '1', '1', '1173251747818668034', '1', '1', null, '2019-09-15 23:06:29', '2019-09-15 23:06:29');
INSERT INTO `sys_menu_role` VALUES ('1173251762515509249', '1', '1', '1173251762452594690', '1', '1', null, '2019-09-15 23:06:33', '2019-09-15 23:06:32');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `descritpion` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `pid` bigint(32) DEFAULT NULL,
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'admin:add', 'add', '/admin', null, null, null, null, null);
INSERT INTO `sys_permission` VALUES ('2', 'other:add', 'add', '/other', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(32) DEFAULT NULL,
  `permission_id` bigint(32) DEFAULT NULL,
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
INSERT INTO `sys_permission_role` VALUES ('1', '1', '1', null, null, null, null);
INSERT INTO `sys_permission_role` VALUES ('2', '1', '2', null, null, null, null);
INSERT INTO `sys_permission_role` VALUES ('3', '2', '1', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', 'ROLE_OTHER', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(32) DEFAULT NULL,
  `sys_role_id` bigint(32) DEFAULT NULL,
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1', null, null, null, null);
INSERT INTO `sys_role_user` VALUES ('2', '2', '2', null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(32) NOT NULL,
  `username` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `cuser` varchar(255) DEFAULT NULL,
  `uuser` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO', 'shaolin', 'shaolin', null, null);
INSERT INTO `sys_user` VALUES ('1195225351496667137', 'admin1', '123456', 'shaolin', 'shaolin', null, null);
INSERT INTO `sys_user` VALUES ('1195225354021638145', 'blackduanwei', '$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO', 'shaolin', 'shaolin', null, null);
INSERT INTO `sys_user` VALUES ('1195225356584357890', 'blackduanwei', '$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO', 'shaolin', 'shaolin', null, null);
INSERT INTO `sys_user` VALUES ('1195225358736035842', 'blackduanwei', '$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO', 'shaolin', 'shaolin', null, null);
INSERT INTO `sys_user` VALUES ('1195225362192142338', 'blackduanwei', '$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO', 'shaolin', 'shaolin', null, null);
