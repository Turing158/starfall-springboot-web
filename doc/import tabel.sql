create database web default character set utf8;
create table user
(
    user      varchar(255) not null
        primary key,
    password  varchar(255) null,
    date      varchar(255) null,
    level     int          null,
    name      varchar(255) null,
    introduce varchar(255) null,
    email     varchar(255) null,
    head      varchar(255) not null,
    constraint user
        unique (user)
);

create index head
    on user (head);
create table notice
(
    id      int          not null,
    content varchar(255) null
);
create table topic
(
    id               int auto_increment
        primary key,
    icon             varchar(255) null,
    label            varchar(255) null,
    title            varchar(255) null,
    author           varchar(255) null,
    date             date         null,
    comment          int          null,
    view             int          null,
    href             varchar(255) null,
    label_href       varchar(255) null,
    titlename        varchar(255) null,
    titleenglishname varchar(255) null,
    source           varchar(255) null,
    version          varchar(255) null,
    language         varchar(255) null,
    address          varchar(255) null,
    download         varchar(255) null,
    content          varchar(255) null
);
create table comment
(
    content   varchar(255) null,
    date      datetime     null,
    user      varchar(255) null,
    head      varchar(255) null,
    name      varchar(255) null,
    topicid   int          null,
    id        int auto_increment
        primary key,
    introduce varchar(255) null
);