DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `userName` varchar(100) DEFAULT NULL,
                        `password` varchar(100) DEFAULT NULL,
                        `role` varchar(20) DEFAULT NULL,
                        `userId` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES ('huonzy','$2a$10$kNrjJ3.1wCSmSnjs1JI.RO6RMrQc.oRiJ93T2sefyCOzXb7yy.Mmm','user','1');

UNLOCK TABLES;