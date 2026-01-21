-- 创建数据库
create database if not exists SpiderPool;

use SpiderPool;


-- 创建表

-- 用户表
create table if not exists user
(
    user_id         bigint primary key auto_increment comment '用户id',
    username        varchar(255) not null comment '用户名',
    password        varchar(255) not null comment '密码',
    phone           varchar(20) comment '手机号',
    email           varchar(25) comment '邮箱',
    real_name       varchar(50) comment '真实姓名',
    id_card         varchar(18) comment '身份证号',
    avatar          varchar(255) comment '用户头像',
    gender          tinyint(1) default 2 comment '性别 1男 0女 2未知',
    birthday        date comment '出生日期',
    last_login_time datetime comment '最后登录时间',
    last_login_ip   varchar(50) comment '最后登录ip',
    delete_flag     tinyint(1) default 0 comment '删除标志 1删除 0正常',
    user_status     tinyint(1) default 1 comment '用户状态 1正常 0禁用',
    create_time     datetime   default current_timestamp comment '创建时间',
    update_time     datetime   default current_timestamp comment '更新时间',
    unique key (username)
) charset utf8mb4 comment '用户表';

-- 角色表
create table if not exists sys_role
(
    role_id     bigint primary key auto_increment comment '角色id',
    role_code   varchar(255) default 0 not null comment '角色编码(参与运算)',
    role_name   varchar(255)           not null comment '角色名称(中文名称)',
    status      tinyint(1)   default 1 comment '状态 1正常 0禁用',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '更新时间',
    unique key (role_name)
) charset utf8mb4 comment '角色表';

-- 权限表
create table if not exists sys_permission
(
    permission_id   bigint primary key auto_increment comment '权限id',
    permission_code varchar(255) not null comment '权限编码(参与运算必须为2的幂次整数倍)',
    permission_name varchar(255) null comment '权限名称(中文名称)',
    status          tinyint(1) default 1 comment '状态 1正常 0禁用',
    create_time     datetime   default current_timestamp comment '创建时间',
    update_time     datetime   default current_timestamp comment '更新时间',
    unique key (permission_code, permission_name)
) charset utf8mb4 comment '权限表';

-- 用户角色关系表
create table if not exists sys_user_role
(
    user_id     bigint not null comment '用户id',
    role_id     bigint not null comment '角色id',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp comment '更新时间',
    primary key (user_id, role_id)
) charset utf8mb4 comment '用户角色关系表';

-- 角色权限关系表
create table if not exists sys_role_permission
(
    role_id       bigint not null comment '角色id',
    permission_id bigint not null comment '权限id',
    create_time   datetime default current_timestamp comment '创建时间',
    update_time   datetime default current_timestamp comment '更新时间',
    primary key (role_id, permission_id)
) charset utf8mb4 comment '角色权限关系表';