# 新建数据库
create database web default character set utf8mb4;
use web;
#新建用户表结构
create table user
(
    user           varchar(255) not null comment '用户名[独一无二]'
        primary key,
    password       varchar(255) null comment '用户名密码',
    date           varchar(255) null comment '用户注册日期',
    level          int          null comment '用户等级',
    name           varchar(255) null comment '用户显示名称',
    introduce      varchar(255) null comment '用户介绍',
    email          varchar(255) null comment '用户邮箱[独一无二]',
    head           varchar(255) not null,
    promise        int          null comment '用户权限',
    exp            int          null comment '经验',
    signcontinuous int          null,
    constraint user
        unique (user)
);

create index head
    on user (head);


#新建公告表结构
create table notice
(
    id      int          not null comment '公告id',
    content varchar(255) null comment '公告内容'
);


#新建主题表结构
create table topic
(
    id               int auto_increment comment '主题id[用于分辨每个主题]'
        primary key,
    icon             varchar(255)  null comment '可上传的主题贴图标，就是为了好看',
    label            varchar(255)  null comment '标签：用于区分每个主题贴的主题为什么',
    title            varchar(255)  null comment '大标题：在主题区显示，且在主题帖最顶上',
    user             varchar(255)  null comment '发帖人用户名',
    date             date          null comment '发帖日期',
    comment          int           null comment '评论数量',
    view             int           null comment '点击查看人数',
    label_href       varchar(255)  null comment '标签链接[此列相当于lable列的翻译]',
    titlename        varchar(255)  null comment '标题名称[中文]',
    titleenglishname varchar(255)  null comment '标题名称[英文]',
    source           varchar(255)  null comment '来源[原创 or 转载]',
    version          varchar(255)  null comment 'ban''b',
    language         varchar(255)  null comment '语言',
    address          varchar(255)  null comment '原帖地址',
    download         varchar(255)  null comment '下载地址',
    content          varchar(8126) null comment '主题帖内容',
    authorname       varchar(255)  null comment '主题发布的东西的作者',
    username         varchar(255)  null comment '发帖人名称',
    userhead         varchar(255)  null comment '发帖人头像',
    userinformation  varchar(255)  null comment '发帖人介绍'
);



#新建评论表结构
create table comment
(
    content   varchar(255) null comment '评论内容',
    date      datetime     null comment '评论日期',
    user      varchar(255) null comment '评论用户',
    head      varchar(255) null comment '评论用户的头像',
    name      varchar(255) null comment '评论用户的名称',
    topicid   int          null comment '评论位于那个主题帖id',
    id        int auto_increment comment '评论的id'
        primary key,
    introduce varchar(255) null comment '评论用户的介绍'
);

# 新建点赞表结构
create table good
(
    id      int auto_increment
        primary key,
    good    int          null,
    user    varchar(255) null,
    topicid int          null,
    date    date         null
)
    comment '喜欢，对于这个文件命名的问题，不能直接命名为Like，因为Like是mysql的关键字，会报错';

# 新建签到表结构
create table sign_in
(
    id      int          not null
        primary key,
    user    varchar(100) null,
    date    datetime     null,
    comment varchar(255) null,
    name    varchar(100) null
);