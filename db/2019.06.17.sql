-- 月报表
CREATE TABLE IF NOT EXISTS `dust_mouth_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mouth` varchar(100) DEFAULT NULL,
  `file_path` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (86,0,'统计分析','','','0','config',6);
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (87,86,'月度统计','generator/monthly-tongji','','1','config','6');
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (88,86,'当前统计','generator/current-tongji','','1','config','6');
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (89,86,'月报','generator/mouthreport','','1','config','6');
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (90,89,'查看','','generator:mouthreport:list,generator:mouthreport:info','2','config','6');
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (91,89,'新增','','generator:mouthreport:save','2','config','6');
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (92,89,'修改','','generator:mouthreport:update','2','config','6');
INSERT INTO `sys_menu` (`menu_id`,`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (93,89,'删除','','generator:mouthreport:delete','2','config','6');
