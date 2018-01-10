CREATE TABLE `test` (
  `display` enum('0','1') COLLATE utf8mb4_unicode_ci DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2089 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

