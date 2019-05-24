CREATE TABLE `optimistic_lock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(32) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_name` (`message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `pessimistic_lock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_name` (`message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;