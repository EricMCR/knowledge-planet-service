-- user服务脚本
CREATE DATABASE IF NOT EXISTS knowledge_planet_user;

USE knowledge_planet_user;

CREATE TABLE IF NOT EXISTS `user` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
                        `username` VARCHAR(255) NOT NULL COMMENT '',
                        `password` VARCHAR(255) NOT NULL COMMENT '',
                        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                        `is_delete` INT NOT NULL DEFAULT '0' COMMENT '',
                        `user_role` INT NOT NULL DEFAULT '0' COMMENT '0 - normal user 1 - manager',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='User Table';

-- 插入访客账号 密码：12345678
INSERT INTO `user`
(id, username, password, create_time, update_time, is_delete, user_role)
VALUES(88888, 'guest', '2b60f8cbd16d9a64878950d88782ae07', '2024-09-10 17:32:32', '2024-09-10 17:32:32', 0, 0);

-- graph服务脚本
CREATE DATABASE IF NOT EXISTS knowledge_planet_node;

USE knowledge_planet_node;

CREATE TABLE IF NOT EXISTS `graph` (
                         `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
                         `user_id` BIGINT NOT NULL COMMENT '',
                         `name` VARCHAR(255) NOT NULL COMMENT '',
                         `description` TEXT COMMENT '',
                         `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                         `is_delete` TINYINT NOT NULL DEFAULT '0' COMMENT '',
                         `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                         `views` INT NOT NULL DEFAULT '0' COMMENT '',
                         PRIMARY KEY (`id`),
                         INDEX `idx_user_id` (`user_id`) -- 可选索引，根据查询需求添加
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Graph Table';

CREATE TABLE IF NOT EXISTS `node` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
                        `graph_id` BIGINT NOT NULL COMMENT '',
                        `name` VARCHAR(255) NOT NULL COMMENT '',
                        `content` TEXT COMMENT '',
                        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                        `is_delete` TINYINT NOT NULL DEFAULT '0' COMMENT '',
                        PRIMARY KEY (`id`),
                        INDEX `idx_graph_id` (`graph_id`) -- 可选索引，根据查询需求添加
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Node Table';

CREATE TABLE IF NOT EXISTS `relation` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
                            `graph_id` BIGINT NOT NULL COMMENT '',
                            `relationship` VARCHAR(255) NOT NULL COMMENT '',
                            `source` VARCHAR(255) NOT NULL COMMENT '',
                            `target` VARCHAR(255) NOT NULL COMMENT '',
                            `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                            `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
                            `is_delete` TINYINT NOT NULL DEFAULT '0' COMMENT '',
                            PRIMARY KEY (`id`),
                            INDEX `idx_graph_id` (`graph_id`) -- 可选索引，根据查询需求添加
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Relation Table';