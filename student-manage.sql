/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : student-manage

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-06 15:05:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `student_basic_info`;
CREATE TABLE `student_basic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xuehao` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '学号',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `minzu` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '民族',
  `zhengzhi` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '政治面貌',
  `id_number` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `jiguan` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '籍贯',
  `xueyuan` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学院',
  `zhuanye` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '专业',
  `luqupici` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '录取批次',
  `banji` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '班级',
  `xiaoqu` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '校区',
  `gongyu` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '公寓',
  `qinshihao` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '寝室号',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '家庭地址',
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `qq` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'QQ',
  `jiazhang1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '家长1',
  `jiazhang1_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '家长1电话',
  `jiazhang2` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '家长2',
  `jiazhang2_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '家长2电话',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `nianji` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '年级',
  `sex` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xuehao` (`xuehao`)
) ENGINE=InnoDB AUTO_INCREMENT=612 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_chengji
-- ----------------------------
DROP TABLE IF EXISTS `student_chengji`;
CREATE TABLE `student_chengji` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(15) NOT NULL COMMENT '学生编号',
  `xueqi` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学期',
  `zhuanye_paiming` int(11) DEFAULT NULL COMMENT '专业成绩排名',
  `zonghe_paiming` int(11) DEFAULT NULL COMMENT '综合成绩排名',
  `bukaokemu` int(11) DEFAULT NULL COMMENT '补考科目',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1025 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_pingjiang
-- ----------------------------
DROP TABLE IF EXISTS `student_pingjiang`;
CREATE TABLE `student_pingjiang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(15) NOT NULL COMMENT '学生编号',
  `xueqi` varchar(5) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '学期',
  `jiangxuejin` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '奖学金',
  `danxiangjiangxuejin` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '单项奖学金',
  `xueyou` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学优',
  `tuanyou` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '团优',
  `yxdxbys` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '优秀大学毕业生',
  `dxxx` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '党校学习',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=814 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_qitahuojiang
-- ----------------------------
DROP TABLE IF EXISTS `student_qitahuojiang`;
CREATE TABLE `student_qitahuojiang` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生编号',
  `xueqi` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '获奖时间',
  `otherhuojianginfo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '获奖信息',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_remark
-- ----------------------------
DROP TABLE IF EXISTS `student_remark`;
CREATE TABLE `student_remark` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `xueqi` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '学期',
  `remark` text COLLATE utf8_bin COMMENT '备注',
  `remark_time` datetime DEFAULT NULL COMMENT '备注时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_weiji
-- ----------------------------
DROP TABLE IF EXISTS `student_weiji`;
CREATE TABLE `student_weiji` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `student_id` int(11) NOT NULL COMMENT '学生编号',
  `xueqi` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '学期',
  `weiji_time` datetime DEFAULT NULL COMMENT '违纪时间',
  `weiji_info` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '违纪情况',
  `result` text COLLATE utf8_bin COMMENT '违纪备注',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_xueye
-- ----------------------------
DROP TABLE IF EXISTS `student_xueye`;
CREATE TABLE `student_xueye` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(15) NOT NULL COMMENT '学生编号',
  `cet4` float(255,2) DEFAULT NULL,
  `sanbizi` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '三笔字',
  `putonghua` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '普通话',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=613 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student_zizhu
-- ----------------------------
DROP TABLE IF EXISTS `student_zizhu`;
CREATE TABLE `student_zizhu` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `xueqi` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '学期',
  `gjjxj` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '国家奖学金',
  `gjlzjxj` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '国家励志奖学金',
  `gjzxj` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '国家助学金',
  `other` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '其他社会资助',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=813 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
