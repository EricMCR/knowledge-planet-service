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

-- 预设知识图数据
INSERT INTO graph
(id, user_id, name, description, update_time, is_delete, create_time, views)
VALUES(1, 88888, '协同配置', '精斗云进销存系统协同配置模块操作指南', '2024-09-10 19:07:11', 0, '2024-09-10 19:07:11', 3);

INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(4, 1, '协同配置', '实现客户通过“精斗云协同”小程序进行要货、对账等操作，提高沟通效率', '2024-09-10 19:07:55', '2024-09-10 19:07:55', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(5, 1, '协同二维码', '1、点击【协同】-【协同二维码】，便于商家下游客户快速加入

协同公众号：商家、下游客户、供应商、送货员绑定后，可通过该公众号接收提醒

2、“精斗云协同”小程序二维码，商家下游客户，无需商家前期在PC端绑定维护，商家发送打印二维码给客户即可通过二维码快速下单

3、支持统一协同二维码，无需分享，扫码即可加入协同
', '2024-09-10 19:08:34', '2024-09-10 19:08:34', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(6, 1, '支付配置', '1、支持配置在线支付渠道和转账渠道，配置勾选生效后，协同小程序下单页面展示支付或者转账入口；
2、在线支付支付配置需要前期配置好通道，点击【设置】-【支付通道配置】', '2024-09-10 19:09:15', '2024-09-10 19:09:15', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(7, 1, '仓库配置', '', '2024-09-10 19:09:33', '2024-09-10 19:09:33', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(8, 1, '要货默认仓库', '勾选要货默认仓库并选中仓库，配置的仓库为要货单下推销货订单时，订单商品默认发货的仓库', '2024-09-10 19:10:11', '2024-09-10 19:10:11', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(9, 1, '库存展示设置', '3种库存展示方式；', '2024-09-10 19:10:30', '2024-09-10 19:10:30', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(10, 1, '不展示库存', '商品列表和商品详情页不展示剩余库存', '2024-09-10 19:10:47', '2024-09-10 19:10:47', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(11, 1, '展示库存', '商品列表和商品详情页展示剩余库存', '2024-09-10 19:11:06', '2024-09-10 19:11:06', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(12, 1, '展示模糊库存', '商品列表和商品详情页可选择展示具体剩余库存及对应库存区间内显示的配置文案，选择开启这种展示方式后需要进一步维护每个区间上限库存值，以及低于改库存值的文案说明', '2024-09-10 19:11:24', '2024-09-10 19:11:24', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(13, 1, '通用设置', '支持配置客户是否自行绑定，勾选后经营助手分享小程序给到对应用户，若用户前期未绑定，用户可在协同小程序申请自助绑定', '2024-09-10 19:11:51', '2024-09-10 19:11:51', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(14, 1, '散客设置', '需在【协同】-【协同配置】-【散客设置】-开启允许散客模式、散客下单、散客查看价格等', '2024-09-10 19:12:21', '2024-09-10 19:12:21', 0);
INSERT INTO node
(id, graph_id, name, content, create_time, update_time, is_delete)
VALUES(15, 1, '下单提醒', '要货单提交后PC端增加语音提醒、弹窗提醒', '2024-09-10 19:12:35', '2024-09-10 19:12:35', 0);

INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(3, 1, '包含', '4', '5', '2024-09-10 19:08:34', '2024-09-10 19:08:34', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(4, 1, '包含', '4', '6', '2024-09-10 19:09:15', '2024-09-10 19:09:15', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(5, 1, '包含', '4', '7', '2024-09-10 19:09:33', '2024-09-10 19:09:33', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(6, 1, '包含', '7', '8', '2024-09-10 19:10:11', '2024-09-10 19:10:11', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(7, 1, '包含', '7', '9', '2024-09-10 19:10:30', '2024-09-10 19:10:30', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(8, 1, '支持', '9', '10', '2024-09-10 19:10:47', '2024-09-10 19:10:47', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(9, 1, '支持', '9', '11', '2024-09-10 19:11:06', '2024-09-10 19:11:06', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(10, 1, '支持', '9', '12', '2024-09-10 19:11:24', '2024-09-10 19:11:24', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(11, 1, '包含', '4', '13', '2024-09-10 19:11:51', '2024-09-10 19:11:51', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(12, 1, '包含', '4', '14', '2024-09-10 19:12:21', '2024-09-10 19:12:21', 0);
INSERT INTO relation
(id, graph_id, relationship, source, target, create_time, update_time, is_delete)
VALUES(13, 1, '包含', '4', '15', '2024-09-10 19:12:35', '2024-09-10 19:12:35', 0);