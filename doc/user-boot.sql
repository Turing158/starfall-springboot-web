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
