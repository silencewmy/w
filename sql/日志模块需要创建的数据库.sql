
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `act_type` tinyint NOT NULL COMMENT '具体操作名称',
  `log_type` tinyint NOT NULL COMMENT '日志主类型',
  `sublog_type` tinyint NOT NULL COMMENT '日志子类型',
  `organization_id` varchar(40) NOT NULL COMMENT '组织结点id,无填无' DEFAULT '空',
  `line_code` varchar(40) NOT NULL COMMENT '线路code,无填无' DEFAULT '空',
  `line_name` varchar(64) NOT NULL COMMENT '线路名称' DEFAULT '空',
  `operation_type` tinyint DEFAULT NULL COMMENT '操作对象类型,1-车辆  2-人员 3-指令等',
  `operation_id` varchar(64) DEFAULT NULL COMMENT '操作对象ID,如果是车记录车辆ID,人的话记录人员ID,指令的话记录指令ID',
  `actor_name` varchar(64) DEFAULT NULL COMMENT '操作者用户名',
  `real_name` varchar(64) DEFAULT NULL COMMENT '操作者真实姓名',
  `actor_id` bigint(20) NOT NULL COMMENT '调度者主键',
  `ip_addr` varchar(64) DEFAULT NULL COMMENT '操作ip地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `log_title` varchar(1024) NOT NULL COMMENT '日志标题',
  `log_remark` varchar(1024) NOT NULL COMMENT '日志备注',
  `log_comment` varchar(8096) DEFAULT NULL COMMENT '服务日志详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';