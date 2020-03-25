-- 创建管理员账号
INSERT INTO `tb_account` (`id`,`account`,`pwd`,`role`,`token`, `token_expire`, `create_at`,`creater`,`modify_at`,`modifier`,`name`,`status`)
VALUES
(0,'admin','cbc9652ebcb81445b6c55ddf2ca89e97', 0, NULL, now(), now(),'auto', now(),'auto', 'admin', 0);


-- 测试项目
INSERT INTO `supervision`.`tb_project` (`name`, `equipment_numnber`, `city`, `longitude`, `latitude`, `project_status`, `create_at`, `creater`, `modify_at`, `modifier`) VALUES ('测试项目', '2', '上海', '121.48', '31.22  ', '2', now(), 'auto', now(), 'auto');

-- 测试项目对应两个设备 注意project_id需要修改为上述新增完的id
INSERT INTO `supervision`.`tb_equipment` (`equipment_status`, `project_id`, `period`, `parameter`, `create_at`, `creater`, `modify_at`, `modifier`) VALUES ('0', '1', '2', '{}', now(), 'auto', now(), 'auto');
INSERT INTO `supervision`.`tb_equipment` (`equipment_status`, `project_id`, `period`, `parameter`, `create_at`, `creater`, `modify_at`, `modifier`) VALUES ('0', '1', '2', '{}', now(), 'auto', now(), 'auto');


-- 添加图片信息
INSERT INTO `supervision`.`tb_pic` (`camera_id`, `project_id`, `path`, `pic_time`, `pic_status`, `size`, `create_at`, `creater`, `modify_at`, `modifier`) VALUES ('1', '1', 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg', now(), '0', '100', now(), 'auto', now(), 'auto');