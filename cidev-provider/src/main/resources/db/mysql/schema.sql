CREATE TABLE `demo` (
  `id` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `cidev_user` (
  `id` varchar(64) NOT NULL COMMENT '用户id',
  `name` varchar(64) NOT NULL COMMENT ' 用户名称',
  `email` varchar(64) NOT NULL COMMENT '用户邮箱地址',
  `phone` varchar(32) NOT NULL COMMENT '用户手机号',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开发者门户用户表';
