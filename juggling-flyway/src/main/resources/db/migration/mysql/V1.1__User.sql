CREATE TABLE `a_auth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tid` bigint(20) DEFAULT NULL,
  `employee_no` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '员工工号',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `mobile` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像地址',
  `ext_data` text COLLATE utf8_bin COMMENT '扩展数据，一般存json字符串',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_non_expired` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `account_non_locked` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `credentials_non_expired` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK4th6v77tc3l12gwix3upckrb2` (`group_id`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_email` (`email`) USING BTREE,
  KEY `idx_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `juggling_migration`.`a_auth_user`(`tid`, `employee_no`, `name`, `password`, `username`, `email`, `group_id`, `mobile`, `avatar`, `ext_data`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `deleted`) VALUES (1, '1', 'Leckie', 'password', 'leckie', 'leckie@bbdservice.com', NULL, '18888888888', NULL, NULL, 1, 1, 1, 1, 0);