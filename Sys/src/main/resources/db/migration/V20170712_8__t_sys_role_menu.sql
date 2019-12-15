create table IF NOT EXISTS t_sys_role_menu
(
   id                   bigint unsigned not null auto_increment comment 'id',
   role_code            varchar(50) comment '关联角色编码',
   menu_id              bigint comment '关联菜单id',
   primary key (id)
);

alter table t_sys_role_menu comment '角色与菜单关联关系表';