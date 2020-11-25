CREATE TABLE `user_info` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `telephone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


CREATE TABLE `commodity_info` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(200) NOT NULL COMMENT '商品名称',
  `title` varchar(200) NOT NULL COMMENT '商品标题',
  `url` varchar(200) DEFAULT NULL COMMENT '商品图片地址',
  `detail` text  COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格',
  `stock` int(11) NOT NULL COMMENT '库存',
  `status` int(6) DEFAULT '1' COMMENT '商品状态 1-在售 2-下架 3-删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='商品表';


CREATE TABLE `order_info` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` varchar(200) DEFAULT NULL COMMENT '订单号',
  `user_id` varchar(200) DEFAULT NULL COMMENT '用户id',
  `commodity_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `order_price`  decimal(20,2) DEFAULT NULL COMMENT '订单金额',
  `payement_price` decimal(20,2) DEFAULT NULLL COMMENT '实际支付金额',
  `pament_type` int(11) DEFAULT NULL COMMENT '支付类型 1-在线支付 2-虚拟支付',
  `status` int(6) DEFAULT NULL COMMENT '订单状态 0-已取消 1-未支付 2-已支付 3-已发货 5 交易完成 6 退款处理中 7-已退款',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `payment_finish_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='订单表';