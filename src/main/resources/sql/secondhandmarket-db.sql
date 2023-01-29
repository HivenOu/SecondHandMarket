-- create tables

-- project.admin definition

CREATE TABLE `admin` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(25) DEFAULT NULL COMMENT '账号',
                         `phone` bigint NOT NULL,
                         `password` varchar(25) NOT NULL COMMENT '密码',
                         `userRole` varchar(25) DEFAULT NULL COMMENT '角色',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;


-- project.catelog definition

CREATE TABLE `catelog` (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(30) DEFAULT NULL COMMENT '分类名',
                           `number` int DEFAULT '0' COMMENT '该分类下的商品数量',
                           `status` tinyint DEFAULT '0' COMMENT '分类状态，0正常，1暂用',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;


-- project.comments definition

CREATE TABLE `comments` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '评论主键',
                            `user_id` int DEFAULT NULL COMMENT '用户，外键',
                            `goods_id` int DEFAULT NULL COMMENT '商品，外键',
                            `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
                            `create_at` varchar(250) DEFAULT NULL COMMENT '评论时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb3;


-- project.focus definition

CREATE TABLE `focus` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `goods_id` int DEFAULT NULL COMMENT '外键 商品id',
                         `user_id` int DEFAULT NULL COMMENT '外键 用户id',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;


-- project.goods definition

CREATE TABLE `goods` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '商品主键',
                         `catelog_id` int DEFAULT NULL COMMENT '商品分类，外键',
                         `user_id` int DEFAULT NULL COMMENT '用户外键',
                         `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
                         `price` float(11,2) DEFAULT NULL COMMENT '出售价格',
  `real_price` float(11,2) DEFAULT NULL COMMENT '真实价格',
  `start_time` varchar(25) DEFAULT NULL COMMENT '发布时间',
  `polish_time` varchar(30) DEFAULT NULL COMMENT '擦亮时间，按该时间进行查询，精确到时分秒',
  `end_time` varchar(25) DEFAULT NULL COMMENT '下架时间',
  `describle` text COMMENT '详细信息',
  `status` int DEFAULT '1' COMMENT '状态 上架1 下架0',
  `isdel` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `catelog_id` (`catelog_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb3;


-- project.image definition

CREATE TABLE `image` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '图片主键',
                         `goods_id` int NOT NULL COMMENT '商品外键',
                         `img_url` text NOT NULL COMMENT '图片链接',
                         PRIMARY KEY (`id`),
                         KEY `goods_id` (`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb3;


-- project.notice definition

CREATE TABLE `notice` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '系统信息主键',
                          `user_id` int DEFAULT NULL COMMENT '用户，外键',
                          `context` text COMMENT '信息内容',
                          `create_at` varchar(25) DEFAULT NULL COMMENT '推送信息时间',
                          `status` tinyint DEFAULT NULL COMMENT '状态，0未读，1已读',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;


-- project.orders definition

CREATE TABLE `orders` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '订单表id',
                          `user_id` int NOT NULL COMMENT '用户id',
                          `goods_id` int NOT NULL COMMENT '商品id',
                          `order_num` bigint DEFAULT NULL COMMENT '订单编号',
                          `order_price` float(11,0) DEFAULT NULL COMMENT '订单价格',
  `order_state` int DEFAULT '1' COMMENT '订单状态 1待发货 2待收货 3已完成',
  `order_information` varchar(50) DEFAULT NULL,
  `order_date` varchar(50) DEFAULT NULL COMMENT '下单时间',
  `seller_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;


-- project.purse definition

CREATE TABLE `purse` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '钱包id',
                         `user_id` int NOT NULL COMMENT '用户id',
                         `balance` float(11,0) unsigned zerofill DEFAULT '00000000000' COMMENT '总钱数',
  `recharge` float(11,0) DEFAULT NULL COMMENT '充值钱数',
  `withdrawals` float(11,0) DEFAULT NULL COMMENT '提现钱数',
  `state` int DEFAULT NULL COMMENT '状态 0未审核   已审核（1不通过 2通过）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;


-- project.reply definition

CREATE TABLE `reply` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '评论回复主键',
                         `user_id` int DEFAULT NULL COMMENT '用户，外键',
                         `atuser_id` int DEFAULT NULL,
                         `commet_id` int DEFAULT NULL COMMENT '评论，外键',
                         `content` text COMMENT '回复内容',
                         `create_at` varchar(25) DEFAULT NULL COMMENT '回复时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- project.`user` definition

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `phone` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '登录使用的手机号',
                        `username` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户名',
                        `password` char(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '密码',
                        `QQ` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '即时通讯',
                        `create_at` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建时间',
                        `goods_num` int DEFAULT '0' COMMENT '发布过的物品数量',
                        `power` int(10) unsigned zerofill DEFAULT '0000000100' COMMENT '信用分，普通用户默认为100',
                        `last_login` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '最近一次登陆时间',
                        `status` tinyint DEFAULT '0' COMMENT '账号是否冻结，默认0未冻结',
                        `isdel` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- create data

