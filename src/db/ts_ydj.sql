/*
Navicat MySQL Data Transfer

Source Server         : 爱巢帮
Source Server Version : 50720
Source Host           : rm-2zei6ci0h7k289359ro.mysql.rds.aliyuncs.com:3306
Source Database       : ts_ydj

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-08-24 11:30:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ydj_add_order_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_add_order_record`;
CREATE TABLE `ydj_add_order_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `add_order_no` varchar(64) DEFAULT NULL COMMENT '附加单编号',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `salesman_id` bigint(11) DEFAULT NULL COMMENT '业务员id',
  `user_coupon_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户优惠券id',
  `real_pay_amount` bigint(11) DEFAULT NULL COMMENT '实际支付金额',
  `amount` bigint(11) DEFAULT NULL COMMENT '应支付金额',
  `member_amount` bigint(11) DEFAULT NULL COMMENT '会员金额',
  `pay_channel` tinyint(4) DEFAULT NULL COMMENT '支付方式  0余额支付 1微信 2支付宝',
  `create_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '100' COMMENT '订单状态：100：待付款 |101：支付成功|102：支付失败',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `add_order_no` (`add_order_no`) USING BTREE,
  KEY `order_id` (`order_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `salesman_id` (`salesman_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_allot_order_rule
-- ----------------------------
DROP TABLE IF EXISTS `ydj_allot_order_rule`;
CREATE TABLE `ydj_allot_order_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '规则描述',
  `rule_key` varchar(50) DEFAULT NULL COMMENT '规则key',
  `rule_value` varchar(10) DEFAULT NULL,
  `sort` int(2) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0：单个值、1：选择值（随机分配，按照距离由近及远）',
  `status` int(1) DEFAULT NULL COMMENT '启用状态：0：未启用、1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='派单规则表';

-- ----------------------------
-- Table structure for ydj_app_version
-- ----------------------------
DROP TABLE IF EXISTS `ydj_app_version`;
CREATE TABLE `ydj_app_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal` int(2) DEFAULT NULL COMMENT '终端(1#用户端;2#业务员端;)',
  `app_type` int(11) DEFAULT NULL COMMENT 'app类型(0#android,1#ios)',
  `app_name` varchar(20) DEFAULT NULL COMMENT 'app类型名称',
  `down_url` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `app_version` varchar(10) DEFAULT NULL COMMENT '版本号',
  `version_code` int(11) DEFAULT NULL COMMENT '版本代码(android)',
  `force_update` int(11) DEFAULT '0' COMMENT '是否强制更新(0#否;1#是)',
  `remark` varchar(800) DEFAULT NULL COMMENT '备注更新内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`app_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='app版本控制表';

-- ----------------------------
-- Table structure for ydj_baidu_api_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_baidu_api_record`;
CREATE TABLE `ydj_baidu_api_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `lng` varchar(32) DEFAULT NULL COMMENT '经度',
  `lat` varchar(32) DEFAULT NULL COMMENT '维度',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `city_code` varchar(32) NOT NULL DEFAULT '0',
  `address` varchar(255) DEFAULT NULL COMMENT '地址信息',
  `request_data` varchar(1000) DEFAULT NULL COMMENT '请求内容',
  `response_data` text COMMENT '百度api返回结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `lng_idx` (`lng`,`lat`) USING BTREE,
  KEY `addr` (`address`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1769 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_banner
-- ----------------------------
DROP TABLE IF EXISTS `ydj_banner`;
CREATE TABLE `ydj_banner` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `src` varchar(1000) DEFAULT NULL COMMENT 'banner路径',
  `href` varchar(1000) DEFAULT NULL COMMENT '链接url',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT '1' COMMENT '图片类型(1#首页banner,2#资讯轮播图片)',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `del_flg` int(2) DEFAULT '0' COMMENT '删除状态（0未删除，1已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='banner表';

-- ----------------------------
-- Table structure for ydj_city
-- ----------------------------
DROP TABLE IF EXISTS `ydj_city`;
CREATE TABLE `ydj_city` (
  `id` bigint(6) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT '省市名称',
  `parent_id` int(11) NOT NULL COMMENT '上级id',
  `short_name` varchar(30) NOT NULL COMMENT '简称',
  `level_type` char(1) NOT NULL COMMENT '城市等级(0#国家;1#省;2#市;3#区)',
  `city_code` varchar(4) NOT NULL COMMENT '区号',
  `zip_code` varchar(6) NOT NULL COMMENT '邮编',
  `merge_name` varchar(50) NOT NULL COMMENT '上级合并连续名称',
  `lng` varchar(15) NOT NULL COMMENT '经度',
  `lat` varchar(15) NOT NULL COMMENT '纬度',
  `pinyin` varchar(30) NOT NULL COMMENT '拼音',
  `status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '状态 1:开通 2:关闭',
  `is_hot` tinyint(4) DEFAULT '2' COMMENT '1 热门城市 2非热门',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `parentid` (`parent_id`,`level_type`) USING BTREE,
  KEY `level` (`level_type`) USING BTREE,
  KEY `name` (`short_name`) USING BTREE,
  KEY `city_code` (`city_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_city_category
-- ----------------------------
DROP TABLE IF EXISTS `ydj_city_category`;
CREATE TABLE `ydj_city_category` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `city_id` bigint(11) NOT NULL COMMENT '城市id',
  `category_id` bigint(11) NOT NULL COMMENT 'type字段对应的各表主键id',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '产品类型 0 服务类型 1产品分组 2产品类型 3产品型号',
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`) USING BTREE,
  KEY `category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='城市所提供的服务';

-- ----------------------------
-- Table structure for ydj_coupon
-- ----------------------------
DROP TABLE IF EXISTS `ydj_coupon`;
CREATE TABLE `ydj_coupon` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_no` varchar(64) DEFAULT NULL COMMENT '优惠券编号',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '优惠券标题',
  `explanation` varchar(300) NOT NULL DEFAULT '' COMMENT '使用说明',
  `pic_url` varchar(300) NOT NULL DEFAULT '' COMMENT '图片',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '金额',
  `condition` int(11) NOT NULL DEFAULT '0' COMMENT '满多少可用',
  `del_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0:未删除 1:删除',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0优惠券 1红包  2现金',
  `valid_days` int(11) NOT NULL DEFAULT '7' COMMENT '有效天数',
  `create_time` datetime DEFAULT NULL,
  `update_tiime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `coupon_no` (`coupon_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='优惠券';

-- ----------------------------
-- Table structure for ydj_coupon_card
-- ----------------------------
DROP TABLE IF EXISTS `ydj_coupon_card`;
CREATE TABLE `ydj_coupon_card` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(11) NOT NULL COMMENT '优惠券id',
  `member_card_id` bigint(11) NOT NULL COMMENT '会员卡id',
  PRIMARY KEY (`id`,`coupon_id`),
  KEY `coupon_id` (`coupon_id`) USING BTREE,
  KEY `member_card_id` (`member_card_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_coupon_product
-- ----------------------------
DROP TABLE IF EXISTS `ydj_coupon_product`;
CREATE TABLE `ydj_coupon_product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(11) DEFAULT NULL,
  `product_id` bigint(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_deal_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_deal_record`;
CREATE TABLE `ydj_deal_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '金额',
  `reward_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '充值奖励金额',
  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '此次交易后余额',
  `deal_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '交易类型  0支出 1收入',
  `request_no` varchar(64) DEFAULT NULL COMMENT '交易流水号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='用户账户交易记录表';

-- ----------------------------
-- Table structure for ydj_feedback
-- ----------------------------
DROP TABLE IF EXISTS `ydj_feedback`;
CREATE TABLE `ydj_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for ydj_member_card
-- ----------------------------
DROP TABLE IF EXISTS `ydj_member_card`;
CREATE TABLE `ydj_member_card` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(64) DEFAULT NULL COMMENT '会员卡号',
  `card_name` varchar(64) DEFAULT NULL COMMENT '会员名称',
  `card_amount` bigint(11) NOT NULL DEFAULT '0' COMMENT '会员卡金额',
  `remark` varchar(100) DEFAULT NULL COMMENT '会员卡说明',
  `level` tinyint(4) DEFAULT NULL COMMENT '会员卡等级  10 一级  20二级 30三级',
  `sale` tinyint(4) DEFAULT NULL COMMENT '折扣',
  `del_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除状态 0未删除 1删除',
  `img_url` varchar(255) DEFAULT NULL COMMENT '会员卡图片',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 会员卡 2 充值卡',
  `is_active` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否参与活动 0不参与 1参与',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_no` (`card_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='会员卡表';

-- ----------------------------
-- Table structure for ydj_message
-- ----------------------------
DROP TABLE IF EXISTS `ydj_message`;
CREATE TABLE `ydj_message` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `user_type` tinyint(4) DEFAULT NULL COMMENT '消息类型 1 用户消息 2业务员消息',
  `send_type` tinyint(4) DEFAULT NULL COMMENT '发送类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息表';

-- ----------------------------
-- Table structure for ydj_message_user
-- ----------------------------
DROP TABLE IF EXISTS `ydj_message_user`;
CREATE TABLE `ydj_message_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `message_id` bigint(11) NOT NULL COMMENT '消息id',
  `salseman_id` bigint(11) NOT NULL COMMENT '用户id',
  `user_type` tinyint(4) NOT NULL COMMENT '1 用户 2业务员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息-用户、业务员关联表';

-- ----------------------------
-- Table structure for ydj_order
-- ----------------------------
DROP TABLE IF EXISTS `ydj_order`;
CREATE TABLE `ydj_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(64) NOT NULL COMMENT '订单编号',
  `user_id` bigint(20) NOT NULL COMMENT '关联用户id',
  `product_id` bigint(20) DEFAULT NULL,
  `user_coupon_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户优惠券id',
  `amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付金额',
  `real_pay_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '实际付款金额',
  `member_amount` bigint(20) DEFAULT NULL COMMENT '会员价格',
  `comments` varchar(255) DEFAULT NULL COMMENT '用户备注',
  `pay_channel` tinyint(4) DEFAULT NULL COMMENT '支付方式  0余额支付 1微信 2支付宝',
  `status` int(4) NOT NULL DEFAULT '100' COMMENT '订单状态-1：订单取消 |0退款 |1：订单逻辑删除 |100：待付款 |101：支付成功|102：支付失败|200：待出发 |201：已出发|202：开始工作|203：工作完成|300：待确认|400：已完成 ',
  `address_id` bigint(11) NOT NULL COMMENT '地址id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `user_name` varchar(50) DEFAULT NULL COMMENT '下单用户昵称',
  `service_time` varchar(20) NOT NULL COMMENT '服务时间',
  `create_time` datetime DEFAULT NULL,
  `service_time_id` int(20) DEFAULT NULL,
  `lng` varchar(32) DEFAULT NULL COMMENT '经度',
  `lat` varchar(32) DEFAULT NULL COMMENT '维度',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(11) NOT NULL COMMENT '创建人id',
  `create_user_type` tinyint(4) NOT NULL COMMENT '创建人类型  1用户 2业务员',
  `area` varchar(10) NOT NULL DEFAULT '0' COMMENT '面积',
  `refund_order_no` varchar(64) DEFAULT NULL COMMENT '退款编号',
  `city_id` bigint(11) DEFAULT NULL COMMENT '城市id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `address_id` (`address_id`) USING BTREE,
  KEY `user_coupon_id` (`user_coupon_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE,
  KEY `service_time` (`service_time`) USING BTREE,
  KEY `create_user_id` (`create_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=122921 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for ydj_order_complain
-- ----------------------------
DROP TABLE IF EXISTS `ydj_order_complain`;
CREATE TABLE `ydj_order_complain` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `phone` char(11) NOT NULL DEFAULT '' COMMENT '联系电话',
  `content` varchar(300) NOT NULL DEFAULT '' COMMENT '投诉内容',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `status` tinyint(4) DEFAULT NULL COMMENT '投诉状态 0正常 1撤销',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单投诉';

-- ----------------------------
-- Table structure for ydj_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `ydj_order_detail`;
CREATE TABLE `ydj_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '关联订单id',
  `product_id` bigint(11) NOT NULL COMMENT '产品类型id',
  `product_model_id` bigint(11) NOT NULL COMMENT '产品型号id',
  `count` double(11,2) DEFAULT NULL COMMENT '产品数量',
  `amount` bigint(20) DEFAULT NULL COMMENT '价格',
  `member_amount` bigint(20) DEFAULT NULL COMMENT '会员价格',
  `status` int(6) NOT NULL DEFAULT '100' COMMENT '子订单状态 100：待付款 |101：支付成功|102：支付失败',
  `type` tinyint(4) DEFAULT NULL COMMENT '下单类型 1用户下单 2业务员加单',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=123637 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_order_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_order_record`;
CREATE TABLE `ydj_order_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=478 DEFAULT CHARSET=utf8 COMMENT='订单流程记录表';

-- ----------------------------
-- Table structure for ydj_order_salesman
-- ----------------------------
DROP TABLE IF EXISTS `ydj_order_salesman`;
CREATE TABLE `ydj_order_salesman` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(11) NOT NULL COMMENT '订单id',
  `salesman_id` bigint(11) NOT NULL COMMENT '业务员id',
  `service_time` varchar(30) DEFAULT NULL COMMENT '服务时间',
  `points` double(6,2) NOT NULL DEFAULT '0.00' COMMENT '奖励积分',
  `reward` int(11) NOT NULL DEFAULT '0' COMMENT '奖励金额',
  `is_reward` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 未奖励  1已奖励  2奖励为0',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`) USING BTREE,
  KEY `salesman_id` (`salesman_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=528 DEFAULT CHARSET=utf8 COMMENT='订单业务员关联表';

-- ----------------------------
-- Table structure for ydj_order_salesman_change
-- ----------------------------
DROP TABLE IF EXISTS `ydj_order_salesman_change`;
CREATE TABLE `ydj_order_salesman_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `old_salesman_id` bigint(11) DEFAULT NULL COMMENT '原业务员id',
  `new_salesman_id` bigint(11) DEFAULT NULL COMMENT '新业务员id',
  `order_salesman_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '调单人的id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`) USING BTREE,
  KEY `old_salesman_id` (`old_salesman_id`) USING BTREE,
  KEY `new_salesman_id` (`new_salesman_id`) USING BTREE,
  KEY `order_salesman_id` (`order_salesman_id`) USING BTREE,
  KEY `create_user_id` (`create_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='调单记录表';

-- ----------------------------
-- Table structure for ydj_org
-- ----------------------------
DROP TABLE IF EXISTS `ydj_org`;
CREATE TABLE `ydj_org` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '组织岗位名称',
  `org_code` varchar(64) DEFAULT NULL COMMENT '组织、岗位编号',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '父id',
  `org_type` tinyint(4) DEFAULT NULL COMMENT '类型  1组织 2岗位',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务员组织岗位表';

-- ----------------------------
-- Table structure for ydj_package
-- ----------------------------
DROP TABLE IF EXISTS `ydj_package`;
CREATE TABLE `ydj_package` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '套餐名称',
  `price` bigint(20) DEFAULT NULL COMMENT '套餐价格',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT '跳转链接url',
  `picture_url` varchar(200) NOT NULL COMMENT '图片',
  `package_type` tinyint(4) DEFAULT NULL COMMENT '套餐类型： 1：组合套装、2：推荐套餐',
  `package_des` varchar(200) DEFAULT NULL COMMENT '套餐描述',
  `del_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除  0未删除 1已删除',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 上架 2下架',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='套餐类型表';

-- ----------------------------
-- Table structure for ydj_package_product
-- ----------------------------
DROP TABLE IF EXISTS `ydj_package_product`;
CREATE TABLE `ydj_package_product` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `package_id` bigint(11) NOT NULL COMMENT '套餐id',
  `product_id` bigint(11) NOT NULL COMMENT '所属产品id',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '产品类型 0 服务类型 1产品分组 2产品类型 3产品型号',
  PRIMARY KEY (`id`),
  KEY `package_id` (`package_id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='套餐产品关系表';

-- ----------------------------
-- Table structure for ydj_phone_code
-- ----------------------------
DROP TABLE IF EXISTS `ydj_phone_code`;
CREATE TABLE `ydj_phone_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `code` varchar(10) DEFAULT NULL COMMENT '验证码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ip` varchar(64) DEFAULT NULL COMMENT '客户端IP',
  `status` int(11) DEFAULT NULL COMMENT '1#发送中| 2#成功| 3#失败',
  PRIMARY KEY (`id`),
  KEY `idx_phone` (`phone`),
  KEY `idx_status` (`status`),
  KEY `idx_send_time` (`send_time`),
  KEY `idx_ip` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_phone_msg
-- ----------------------------
DROP TABLE IF EXISTS `ydj_phone_msg`;
CREATE TABLE `ydj_phone_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `type` int(4) DEFAULT NULL COMMENT '短信类型（2#预约成功短信;3#服务完成短信;）',
  `content` varchar(255) DEFAULT NULL COMMENT '短信内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '1#发送中| 2#成功| 3#失败',
  PRIMARY KEY (`id`),
  KEY `idx_phone` (`phone`),
  KEY `idx_type` (`type`),
  KEY `idx_status` (`status`),
  KEY `idx_send_time` (`send_time`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_phone_msg_template
-- ----------------------------
DROP TABLE IF EXISTS `ydj_phone_msg_template`;
CREATE TABLE `ydj_phone_msg_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(2) DEFAULT NULL COMMENT '类型（1#验证码;2#预约成功短信;3#服务完成短信;）',
  `content` varchar(500) DEFAULT NULL COMMENT '短信内容',
  `status` int(11) DEFAULT NULL COMMENT '状态(1#启用;0#禁用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_points_config
-- ----------------------------
DROP TABLE IF EXISTS `ydj_points_config`;
CREATE TABLE `ydj_points_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) DEFAULT NULL COMMENT '关联产品类型id',
  `product_type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '产品类型 0 服务类型 1产品分组 2产品类型 3产品型号',
  `amount_count` int(6) NOT NULL DEFAULT '0' COMMENT '数量 个数或金额',
  `points` double(6,2) NOT NULL DEFAULT '0.00' COMMENT '奖励积分',
  `use_condition` varchar(10) DEFAULT NULL COMMENT '满足条件 >,>=,,=,each(每个)',
  `type` tinyint(4) DEFAULT NULL COMMENT '1 按照个数统计 2按照金额统计',
  `second_amount` int(6) NOT NULL DEFAULT '0' COMMENT '第二金额条件  金额不满足amount时  判断此字段',
  `second_points` double(6,2) NOT NULL DEFAULT '0.00' COMMENT '满足第二金额条件时 奖励积分',
  `point_double` int(2) NOT NULL DEFAULT '2' COMMENT '业务员发单 积分奖励倍数',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_product
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product`;
CREATE TABLE `ydj_product` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '产品名称',
  `introduce` varchar(1000) NOT NULL DEFAULT '' COMMENT '服务介绍',
  `standard` varchar(1000) NOT NULL COMMENT '服务标准',
  `direction` mediumtext COMMENT '价格说明',
  `discount_des` varchar(100) DEFAULT NULL COMMENT '最大优惠',
  `icon_url` varchar(200) NOT NULL DEFAULT '' COMMENT '列表页小头图地址',
  `active_img_url` varchar(200) DEFAULT NULL COMMENT '活动图片地址',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:上架  2：下架',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `group_id` bigint(11) NOT NULL COMMENT '产品分组id',
  `show_count` int(11) NOT NULL DEFAULT '4' COMMENT 'app一行显示几个',
  `level` tinyint(4) DEFAULT NULL COMMENT '在app中显示的位置',
  `show_type` int(11) NOT NULL DEFAULT '100' COMMENT '返回类型  100 单个服务  999 全部',
  `sort` int(5) DEFAULT NULL COMMENT '排序字段',
  `number` varchar(10) NOT NULL COMMENT '编号（用于生成流水号）',
  `show_index` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否在首页产品列表显示  0不显示 1显示',
  `is_active` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否参与活动 0不参与 1参与',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='服务的产品类型表  如 ：冰箱清洗  空调清洗';

-- ----------------------------
-- Table structure for ydj_product_category
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product_category`;
CREATE TABLE `ydj_product_category` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '服务类型名称',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '类别描述',
  `icon_url` varchar(200) NOT NULL DEFAULT '' COMMENT 'icon url地址',
  `active_img_url` varchar(200) DEFAULT NULL COMMENT '活动图片地址',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '在app中展示的位置',
  `introduce` tinytext COMMENT '服务介绍',
  `standard` tinytext COMMENT '服务标准',
  `show_count` int(11) NOT NULL DEFAULT '4' COMMENT 'app一行显示几个',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 上架  2  下架',
  `sort` int(5) DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='服务大类  如：清洗服务  维修服务';

-- ----------------------------
-- Table structure for ydj_product_group
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product_group`;
CREATE TABLE `ydj_product_group` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(11) NOT NULL COMMENT '关联服务id',
  `name` varchar(32) DEFAULT NULL COMMENT '产品分组名称',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `icon_url` varchar(200) DEFAULT NULL COMMENT 'icon url地址',
  `active_img_url` varchar(200) DEFAULT NULL COMMENT '活动图片地址',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '在app显示的位置',
  `introduce` tinytext COMMENT '服务介绍',
  `standard` tinytext COMMENT '服务标准',
  `show_count` int(11) NOT NULL DEFAULT '4' COMMENT 'app端一行显示几个',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 上架 2 下架',
  `sort` int(5) DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='产品分组表  如：家电清洗  衣物清洗';

-- ----------------------------
-- Table structure for ydj_product_model
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product_model`;
CREATE TABLE `ydj_product_model` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '型号名称',
  `product_id` bigint(11) NOT NULL COMMENT '产品id',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '型号基本信息',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `member_price` int(11) DEFAULT NULL COMMENT '会员价格',
  `activity_price` int(11) DEFAULT '0' COMMENT '活动价',
  `specification` varchar(4) NOT NULL DEFAULT '' COMMENT '规格',
  `units` tinyint(4) DEFAULT NULL COMMENT '数量单位：1|台；',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:上架  2：下架',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `introduce` tinytext COMMENT '服务介绍',
  `standard` tinytext COMMENT '服务标准',
  `level` tinyint(4) DEFAULT NULL COMMENT '在app中的位置',
  `show_type` int(11) NOT NULL DEFAULT '100' COMMENT '返回类型  100 单个服务  999 全部',
  `sort` int(5) DEFAULT NULL COMMENT '排序字段',
  `fee_way` tinyint(4) NOT NULL DEFAULT '1' COMMENT '收费方式 1按个数 2按平米',
  `model_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '产品详细类型  1产品型号  2产品附加项目',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='产品的型号表  如：柜式空调  立式空调';

-- ----------------------------
-- Table structure for ydj_product_pic
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product_pic`;
CREATE TABLE `ydj_product_pic` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) NOT NULL COMMENT '产品id',
  `picture_url` varchar(200) NOT NULL DEFAULT '' COMMENT '图片url',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '产品类型 0 服务类型 1产品分组 2产品类型 3产品型号',
  `big_picture_url` varchar(200) DEFAULT NULL COMMENT '产品大图图片url',
  `active_picture_url` varchar(200) DEFAULT NULL COMMENT '活动图片url',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='产品详细图片';

-- ----------------------------
-- Table structure for ydj_product_skill
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product_skill`;
CREATE TABLE `ydj_product_skill` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `skill_id` bigint(11) NOT NULL COMMENT '技能id',
  `product_id` bigint(11) NOT NULL COMMENT '产品id',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '产品类型 0 服务类型 1产品分组 2产品类型 3产品型号',
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='产品-技能关联表';

-- ----------------------------
-- Table structure for ydj_product_tag
-- ----------------------------
DROP TABLE IF EXISTS `ydj_product_tag`;
CREATE TABLE `ydj_product_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(11) NOT NULL COMMENT '标签id',
  `product_id` bigint(11) NOT NULL COMMENT '所属产品id',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '类型 0产品服务 1产品分组 2产品类型 3产品型号',
  PRIMARY KEY (`id`),
  KEY `tag_id` (`tag_id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Table structure for ydj_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_recharge_record`;
CREATE TABLE `ydj_recharge_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `member_card_id` bigint(11) NOT NULL COMMENT '会员卡id',
  `order_no` varchar(64) DEFAULT NULL COMMENT '交易流水号',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型： 1：微信、 2：支付宝、 3：H5微信',
  `amount` bigint(20) DEFAULT NULL COMMENT '充值金额',
  `card_no` varchar(64) DEFAULT NULL COMMENT '会员卡号',
  `status` int(4) DEFAULT NULL COMMENT '支付状态：1：支付成功、0：支付处理中、-1：支付失败',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 COMMENT='充值记录表';

-- ----------------------------
-- Table structure for ydj_reward_config
-- ----------------------------
DROP TABLE IF EXISTS `ydj_reward_config`;
CREATE TABLE `ydj_reward_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) DEFAULT NULL COMMENT '关联产品类型id',
  `product_type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '产品类型 0 服务类型 1产品分组 2产品类型 3产品型号',
  `reward_amount_down` int(6) NOT NULL DEFAULT '0' COMMENT '奖励金额',
  `reward_amount_up` int(6) NOT NULL DEFAULT '0' COMMENT '大于金额预设值奖励金额',
  `total_amount` int(6) NOT NULL DEFAULT '0' COMMENT '额外奖励  总金额限制',
  `each_over_amount` int(6) NOT NULL DEFAULT '0' COMMENT '每超过总金额 n元 ',
  `each_add_amount` int(6) NOT NULL DEFAULT '0' COMMENT '每超过n元 奖励n元',
  `type` int(4) DEFAULT NULL COMMENT '-1 按类型(不考虑个数) 1一个 2两个...  100按总金额奖励',
  `use_condition` varchar(10) DEFAULT NULL COMMENT '满足条件 >,>=,,=,each(每个)',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_safeguard
-- ----------------------------
DROP TABLE IF EXISTS `ydj_safeguard`;
CREATE TABLE `ydj_safeguard` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL COMMENT '标题',
  `description` varchar(200) NOT NULL COMMENT '描述',
  `picture_url` varchar(200) NOT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户保障表';

-- ----------------------------
-- Table structure for ydj_safeguard_product
-- ----------------------------
DROP TABLE IF EXISTS `ydj_safeguard_product`;
CREATE TABLE `ydj_safeguard_product` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `safeguard_id` bigint(11) NOT NULL COMMENT '保障id',
  `product_id` bigint(11) NOT NULL COMMENT '产品id',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '类型 0产品服务  1产品分组 2产品类型 3产品型号',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`) USING BTREE,
  KEY `safeguard_id` (`safeguard_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='产品用户保障表';

-- ----------------------------
-- Table structure for ydj_salesman
-- ----------------------------
DROP TABLE IF EXISTS `ydj_salesman`;
CREATE TABLE `ydj_salesman` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '业务员手机号',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `number_code` varchar(64) DEFAULT NULL COMMENT '员工编号',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `education` tinyint(4) DEFAULT NULL COMMENT '学历  0小学 1初中 2 高中 3大专  4本科 5硕士 6博士',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '业务员状态：1：正常，2：休息，3：离职，4：正在工作',
  `city_id` bigint(6) DEFAULT NULL,
  `org_id` bigint(11) DEFAULT NULL COMMENT '业务员的岗位id',
  `balance` int(11) NOT NULL DEFAULT '0' COMMENT '余额',
  `reward` int(11) NOT NULL DEFAULT '0' COMMENT '奖励',
  `points` double(6,2) NOT NULL DEFAULT '0.00' COMMENT '积分',
  `level` varchar(10) DEFAULT NULL COMMENT '员工等级',
  `portrait` varchar(200) DEFAULT NULL COMMENT '头像',
  `token` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `group_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户组id',
  `type` tinyint(4) NOT NULL DEFAULT '2' COMMENT '1 城市经理  2业务员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  KEY `password` (`password`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='业务员表';

-- ----------------------------
-- Table structure for ydj_salesman_city
-- ----------------------------
DROP TABLE IF EXISTS `ydj_salesman_city`;
CREATE TABLE `ydj_salesman_city` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `salesman_id` bigint(11) NOT NULL,
  `city_id` bigint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `salesman_id` (`salesman_id`) USING BTREE,
  KEY `city_id` (`city_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_salesman_group
-- ----------------------------
DROP TABLE IF EXISTS `ydj_salesman_group`;
CREATE TABLE `ydj_salesman_group` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) DEFAULT NULL COMMENT '业务员组名称',
  `group_code` varchar(64) DEFAULT NULL COMMENT '业务员组编号',
  `lat` varchar(15) DEFAULT NULL COMMENT '维度',
  `lng` varchar(15) DEFAULT NULL COMMENT '经度',
  `city_id` bigint(11) DEFAULT NULL COMMENT '所在城市id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注描述',
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_salesman_sign
-- ----------------------------
DROP TABLE IF EXISTS `ydj_salesman_sign`;
CREATE TABLE `ydj_salesman_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign_time` varchar(20) DEFAULT '' COMMENT '签到日期',
  `salesman_id` bigint(11) DEFAULT NULL COMMENT '业务员id',
  `lat` varchar(32) DEFAULT NULL COMMENT '维度',
  `lng` varchar(32) DEFAULT NULL COMMENT '经度',
  `address` varchar(200) DEFAULT NULL COMMENT '签到地点',
  `del_flg` tinyint(4) DEFAULT NULL COMMENT '删除状态',
  `type` varchar(10) DEFAULT NULL COMMENT 'am:上午   pm:下午',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `salesman_id` (`salesman_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=900 DEFAULT CHARSET=utf8 COMMENT='业务员签到表';

-- ----------------------------
-- Table structure for ydj_salesman_skill
-- ----------------------------
DROP TABLE IF EXISTS `ydj_salesman_skill`;
CREATE TABLE `ydj_salesman_skill` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `skill_id` bigint(11) NOT NULL,
  `salesman_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_id` (`skill_id`) USING BTREE,
  KEY `salesman_id` (`salesman_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='业务员-技能关联表';

-- ----------------------------
-- Table structure for ydj_service_time_info
-- ----------------------------
DROP TABLE IF EXISTS `ydj_service_time_info`;
CREATE TABLE `ydj_service_time_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT 's时段id',
  `show_time` varchar(20) DEFAULT NULL COMMENT '可选择的服务时间',
  `del_flg` tinyint(4) DEFAULT '0' COMMENT '0未删除 1删除',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_skill
-- ----------------------------
DROP TABLE IF EXISTS `ydj_skill`;
CREATE TABLE `ydj_skill` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(64) DEFAULT NULL COMMENT '技能名称',
  `skill_remark` varchar(255) DEFAULT NULL COMMENT '技能描述',
  `skill_code` varchar(32) DEFAULT NULL COMMENT '技能编号',
  `del_flg` tinyint(4) DEFAULT NULL COMMENT '0未删除 1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='服务技能表';

-- ----------------------------
-- Table structure for ydj_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `ydj_sys_config`;
CREATE TABLE `ydj_sys_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sys_name` varchar(64) DEFAULT NULL,
  `sys_value` varchar(64) DEFAULT NULL,
  `del_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未删除 1删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_tag
-- ----------------------------
DROP TABLE IF EXISTS `ydj_tag`;
CREATE TABLE `ydj_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '标签名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:可用  2：删除',
  `font_color` varchar(10) DEFAULT NULL COMMENT '字体颜色',
  `background_color` varchar(10) DEFAULT NULL COMMENT '背景颜色',
  `border_color` varchar(10) DEFAULT NULL COMMENT '边框颜色',
  `del_flg` tinyint(4) DEFAULT NULL COMMENT '0 未删除 1已删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='产品标签表';

-- ----------------------------
-- Table structure for ydj_user
-- ----------------------------
DROP TABLE IF EXISTS `ydj_user`;
CREATE TABLE `ydj_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1  正常   2冻结',
  `password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `balance` bigint(20) DEFAULT '0' COMMENT '账户余额',
  `points` int(11) DEFAULT '0' COMMENT '积分',
  `token` varchar(40) DEFAULT NULL COMMENT '登录token',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `open_id` varchar(128) DEFAULT NULL,
  `del_flg` tinyint(2) DEFAULT '0' COMMENT '是否删除(0#未删除;1#已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `member_level` tinyint(4) DEFAULT '0' COMMENT '会员等级 0不是会员 10 一级 20 二级  30 三级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE,
  KEY `password` (`password`) USING BTREE,
  KEY `open_id` (`open_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for ydj_user_address
-- ----------------------------
DROP TABLE IF EXISTS `ydj_user_address`;
CREATE TABLE `ydj_user_address` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '联系电话',
  `real_name` varchar(10) NOT NULL DEFAULT '' COMMENT '联系人姓名',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '详细地址',
  `localtion_addr` varchar(255) DEFAULT '' COMMENT '定位地址',
  `lat` varchar(32) DEFAULT NULL COMMENT '纬度',
  `lng` varchar(32) DEFAULT NULL COMMENT '经度',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省份编号',
  `city_code` varchar(50) DEFAULT NULL COMMENT '城市编号',
  `area_code` varchar(50) DEFAULT NULL COMMENT '区域编号',
  `del_flg` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '0:可用 1：删除',
  `is_default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认  0否 1是',
  `area` varchar(10) NOT NULL DEFAULT '0' COMMENT '面积',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8 COMMENT='用户常用地址信息表';

-- ----------------------------
-- Table structure for ydj_user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `ydj_user_coupon`;
CREATE TABLE `ydj_user_coupon` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `coupon_id` bigint(11) NOT NULL COMMENT '优惠券id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0：正常 1：已使用 2：已过期',
  `start_time` date DEFAULT NULL COMMENT '有效开始时间',
  `end_time` date DEFAULT NULL COMMENT '有效结束时间',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `coupon_id` (`coupon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8 COMMENT='用户优惠券表';

-- ----------------------------
-- Table structure for ydj_wx_activity
-- ----------------------------
DROP TABLE IF EXISTS `ydj_wx_activity`;
CREATE TABLE `ydj_wx_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL DEFAULT '',
  `desc_ription` varchar(256) NOT NULL DEFAULT '' COMMENT '活动描述',
  `picUrl` varchar(256) NOT NULL DEFAULT '' COMMENT '活动图片地址url',
  `url` varchar(256) NOT NULL DEFAULT '' COMMENT '点击跳转url',
  `sort_col` int(10) DEFAULT NULL COMMENT '活动排序',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除 1=删除 0=未删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='微信活动表';

-- ----------------------------
-- Table structure for ydj_wx_pay_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_wx_pay_record`;
CREATE TABLE `ydj_wx_pay_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(128) DEFAULT NULL,
  `order_no` varchar(64) NOT NULL COMMENT '订单编号',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1  申请   2支付成功',
  `prepay_id` varchar(64) DEFAULT NULL COMMENT '预支付交易会话标识  该值有效期为2小时',
  `call_method` varchar(256) DEFAULT NULL COMMENT '调用函数或类型',
  `return_code` varchar(32) NOT NULL DEFAULT '-' COMMENT '请求放回结果',
  `total_fee` int(11) NOT NULL DEFAULT '0' COMMENT '当status=1时为订单总金额 当status=2时为应结订单金额，单位为分',
  `request_data` text COMMENT '请求参数',
  `response_data` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '返回数据',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `open_id` (`open_id`) USING BTREE,
  KEY `order_no` (`order_no`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=778 DEFAULT CHARSET=utf8 COMMENT='微信申请支付记录表';

-- ----------------------------
-- Table structure for ydj_wx_request_record
-- ----------------------------
DROP TABLE IF EXISTS `ydj_wx_request_record`;
CREATE TABLE `ydj_wx_request_record` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `request_no` varchar(32) DEFAULT NULL,
  `order_no` varchar(32) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1订单号 ，2加单号，3充值记录号  ',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '0：余额支付、 1：微信、 2：支付宝、 3：H5微信、',
  `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '支付状态 1：成功、0：处理中、-1：失败',
  `request_data` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `request_no` (`request_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ydj_wx_user_info
-- ----------------------------
DROP TABLE IF EXISTS `ydj_wx_user_info`;
CREATE TABLE `ydj_wx_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subscribe` int(2) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息',
  `openid` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '用户的标识，对当前公众号唯一',
  `nickname` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户的昵称',
  `sex` int(2) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户所在城市',
  `country` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户所在国家',
  `province` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户所在省份',
  `language` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `headimgurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效',
  `subscribe_time` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `unionid` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `remark` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `groupid` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  `tagid_list` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户被打上的标签ID列表',
  `subscribe_scene` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他',
  `qr_scene` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '二维码扫码场景（开发者自定义）',
  `qr_scene_str` varchar(50) CHARACTER SET utf8 DEFAULT '3' COMMENT '二维码扫码场景描述（开发者自定义）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;
