/*
 Navicat Premium Data Transfer

 Source Server         : local_mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : testing

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 17/08/2020 19:44:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_active` int(0) NULL DEFAULT 0 COMMENT '0 - 账号未激活\r\n1 - 账号已激活',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_count` int(0) NULL DEFAULT 0 COMMENT '记录登录次数',
  `access_count` int(0) NULL DEFAULT 0 COMMENT '记录访问页面次数',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'josen', 'a45599848665e29e1ca59cea04cb0902', 0, 'jo@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (2, 'Testing', '098f6bcd4621d373cade4e832627b4f6', 0, 'test@touchfish.cn', 0, 0);
INSERT INTO `t_user` VALUES (3, 'Curry', '5933baf90dcec96ffa6ecdaf30299550', 0, 'curry@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (4, 'ggg', 'ba248c985ace94863880921d8900c53f', 0, 'szby1023@163.com', 0, 0);
INSERT INTO `t_user` VALUES (5, 'Jimi', '33e2b0e2fbfc13ad9a6dd664fe7bc084', 0, 'jiaosenli@163.com', 0, 0);
INSERT INTO `t_user` VALUES (6, 'Tom', '34b7da764b21d298ef307d04d8152dc5', 0, 'jiaosenli@163.com', 0, 0);
INSERT INTO `t_user` VALUES (7, 'Jerry', 'dbaf60f3a397e1d27630a459c1700ea7', 1, 'jiaosenli@163.com', 0, 0);
INSERT INTO `t_user` VALUES (8, 'Jeery', 'a45599848665e29e1ca59cea04cb0902', 0, 'josen', 0, 0);
INSERT INTO `t_user` VALUES (10, 'Mary', 'b8e7be5dfa2ce0714d21dcfc7d72382c', 1, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (12, 'James', 'b4cc344d25a2efe540adbf2678e2304c', 1, 'josen_lii@126.com', 1, 13);
INSERT INTO `t_user` VALUES (13, 'Rose', 'fcdc7b4207660a1372d0cd5491ad856e', 1, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (14, 'Mike', '18126e7bd3f84b3f3e4df094def5b7de', 1, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (15, 'Harry', '3b87c97d15e8eb11e51aa25e9a5770e9', 1, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (16, 'Siri', '91c731a8ad504c4dde932f79104f992c', 1, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (17, 'DeRozan', '6998ad82b121f8324ab204f83805004a', 1, 'jiaosenli@163.com', 0, 0);
INSERT INTO `t_user` VALUES (18, 'Paul', 'c13e13da2073260c2194c15d782e86a9', 1, 'jiaosenli@163.com', 0, 0);
INSERT INTO `t_user` VALUES (19, 'Monster', '8bf4e6addd72a9c4c4714708d2941528', 1, 'lijiaosen@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (23, 'TestMybatis', '63a9f0ea7bb98050796b649e85481845', 0, 'lijiaosen@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (24, 'Mongodb', '685a5f7cc75b4796f6c6e00ccd384f01', 1, 'lijiaosen@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (25, 'Popovich', 'ec6f58f081c0f35c5f4c57d2c2bd39ca', 1, 'lijiaosen@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (26, 'Lillard', '775cb2ae3f2806b8d681fdec0b270d7a', 1, 'lijiaosen@gmail.com', 0, 0);
INSERT INTO `t_user` VALUES (27, 'Irving', '94802d6366294c46caed219950f69865', 0, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (28, 'Thompson', 'e6892602a984c21896885135147205c6', 0, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (29, 'Doncic', 'e0f5b5a814016775cebd782efb8ac95f', 0, 'josen_lii@126.com', 0, 0);
INSERT INTO `t_user` VALUES (30, 'Test01', '098f6bcd4621d373cade4e832627b4f6', 0, 'josen_lii@126.com', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
