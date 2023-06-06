create table discuss
(
    date    datetime     null,
    content varchar(255) null,
    user    varchar(255) null,
    name    varchar(255) null,
    head    varchar(255) null,
    id      int auto_increment
        primary key,
    constraint head
        foreign key (head) references user (head)
            on update cascade on delete cascade
);

INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-06 16:40:45', '我是管理，不要随意爆粗口哈', 'admin', '管理员', '20230603103044829864061.jpg', 1);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-06 16:41:21', '注意规则：
1.不要爆粗
2.不要发布不当言论
3.不要人身攻击', 'admin', '管理员', '20230603103044829864061.jpg', 2);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-13 22:34:12', '我
是
管
理
!
!', 'admin', '管理员', '20230603103044829864061.jpg', 3);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-13 22:42:58', '我
是
管
理
!
!', 'admin', '管理员', '20230603103044829864061.jpg', 4);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-13 22:43:18', 'a
a
a
a
a
a', 'admin', '管理员', '20230603103044829864061.jpg', 5);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-14 21:04:13', 'dddddddddddddda', 'qwe', 'qwe', 'qwe.jpg', 6);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-14 21:04:13', 'ssssssssssssss', 'qwe', 'qwe', 'qwe.jpg', 7);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-14 21:04:13', 'eeeeeeeeeeee', 'qwe', 'qwe', 'qwe.jpg', 8);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-14 21:04:13', 'ccccccccccccc', 'qwe', 'qwe', 'qwe.jpg', 9);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-14 21:04:13', 'cccccccccccc', 'qwe', 'qwe', 'qwe.jpg', 10);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-15 15:42:56', 'qwe', 'qwe', 'qwe', 'qwe.jpg', 11);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-15 21:38:45', '测试测试
测试测试
测试测试', 'admin', '管理员', '20230603103044829864061.jpg', 12);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-28 19:11:42', '11111111111111test1', 'admin', '管理员', '20230603103044829864061.jpg', 13);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-28 19:17:01', '66666666666666test', 'admin', '管理员', '20230603103044829864061.jpg', 14);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-05-11 18:25:40', 'a
a
a
a
a
a
aa
a
a
aaaaaa', 'admin', '管理员', '20230603103044829864061.jpg', 15);
INSERT INTO web.discuss (date, content, user, name, head, id) VALUES ('2023-04-14 21:04:13', 'cccccccccccc', 'qwe', 'qwe', 'qwe.jpg', 16);
