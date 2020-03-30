CREATE TABLE `xxx` (
  `xxx_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) DEFAULT '' COMMENT '登记码（R开头）',
  `sex` int(11) DEFAULT '0' COMMENT '0-男 1-女',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志：0：未删除，1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
  PRIMARY KEY (`xxx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据表示例';