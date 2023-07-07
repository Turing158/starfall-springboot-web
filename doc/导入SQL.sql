create database web default character set utf8 collate  utf8_general_ci;
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

INSERT INTO web.user (user, password, date, level, name, introduce, email, head) VALUES ('1322621134', '123123', '2023-06-05', 1, '新用户19429E', null, '1322621134@qq.com', 'null.jpg');
INSERT INTO web.user (user, password, date, level, name, introduce, email, head) VALUES ('admin', 'admin', '0000-00-00', 9981, '管理员', '我是管理员!', 'admin@sf.com', '20230603103044829864061.jpg');
INSERT INTO web.user (user, password, date, level, name, introduce, email, head) VALUES ('asd', 'asd', '0000-00-00', 1, 'asd', 'asdasdasd', 'test@t.com', 'null.png');
INSERT INTO web.user (user, password, date, level, name, introduce, email, head) VALUES ('qwe', '123456', '0000-00-00', 10, 'qwenba', '我叫qwe，一个用户a', '123@qq.com', 'qwe.jpg');

create table notice
(
    id      int          not null,
    content varchar(255) null
);

INSERT INTO web.notice (id, content) VALUES (1, '欢迎来到StarFall,请勿随意在留言区发布不正当言论，谢谢！');
INSERT INTO web.notice (id, content) VALUES (2, '初学者纯手撸练习java，做的不好请见谅');
INSERT INTO web.notice (id, content) VALUES (3, '喜欢开放自由的像素游戏，不妨试试我的世界?');
