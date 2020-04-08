
-- 创建库
create database supervision;

-- 使用该表
use supervision;


-- 创建账号表
create table `tb_account`(
  `id` int(10) unsigned not null auto_increment comment 'id',
  `account` varchar(64) not null comment '账号',
  `name` varchar(64) null comment '姓名',
  `pwd` varchar(256) not null comment '密码',
  `role` int(4) not null comment '角色',
  `token` varchar(64) default null comment '登录token',
  `token_expire` datetime null comment 'token过期时间',
  `status` int not null comment '状态',
  `remark` text null comment '备注',

  `create_at` datetime not null comment '创建时间',
  `creater` varchar(64) not null comment '创建人账号',
  `modify_at` datetime not null comment '修改时间',
  `modifier` varchar(64) not null comment '修改人账号',
  primary key(`id`)
)Engine=InnoDB comment '账号表';


-- 创建图像数据表
create table `tb_pic` (
  `id` int(10) unsigned not null auto_increment comment 'id',
  `camera_id` int(10) null comment '图像所属摄像头id',
  `project_id` int(10) null comment '所属监测项目id',
  `path` varchar(512) null comment '图像在服务器上的完整路径',
  `pic_time` datetime null comment '拍摄时间',
  `pic_status` int(6) not null comment '图像状态',
  `size` bigint null comment '文件大小',

  
  `create_at` datetime not null comment '创建时间',
  `creater` varchar(64) not null comment '创建人账号',
  `modify_at` datetime not null comment '修改时间',
  `modifier` varchar(64) not null comment '修改人账号',
  primary key(`id`)
)Engine=InnoDB comment '图像数据表';


-- 创建项目表
create table `tb_project` (
  `id` int(10) unsigned not null auto_increment comment 'id',
  `name` varchar(64) not null comment '项目名称',
  `equipment_numnber` int(6) not null default 0 comment '设备数',
  `city` varchar(64) null comment '所属城市',
  `longitude` varchar(64) null comment '经度',
  `latitude` varchar(64) null comment '纬度',
  `project_status` int(6) not null comment '项目状态',

  `create_at` datetime not null comment '创建时间',
  `creater` varchar(64) not null comment '创建人账号',
  `modify_at` datetime not null comment '修改时间',
  `modifier` varchar(64) not null comment '修改人账号',
  primary key(`id`)
)Engine=InnoDB comment '项目表';

alter table `tb_project` add column `unit_id` varchar(64) null comment '项目id'; 



-- 设备表
create table `tb_equipment` (
  `id` int(10) unsigned not null auto_increment comment 'id',
  `equipment_status` int(6) not null comment '设备状态',
  `dev_code` varchar(64) not null comment 'dev_code',
  `dev_name` varchar(64) null comment 'dev_name',
  `project_id` varchar(64) null comment '所属监测项目id',
  `period` varchar(64) null comment '抓拍间隔',
  `parameter` varchar(1024) null comment '设备参数',
  `longitude` varchar(64) null comment '经度',
  `latitude` varchar(64) null comment '纬度',



  `create_at` datetime not null comment '创建时间',
  `creater` varchar(64) not null comment '创建人账号',
  `modify_at` datetime not null comment '修改时间',
  `modifier` varchar(64) not null comment '修改人账号',
  primary key(`id`)
)Engine=InnoDB comment '设备表';






