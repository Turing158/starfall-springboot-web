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
create table topic
(
    id         int auto_increment
        primary key,
    icon       varchar(255) null,
    label      varchar(255) null,
    title      varchar(255) null,
    author     varchar(255) null,
    date       date         null,
    comment    int          null,
    view       int          null,
    href       varchar(255) null,
    label_href varchar(255) null
);

INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (1, 'skyblock.png', '服务端', '[1.8.x-1.9.x][Spigot]StarFall空岛生存>巨大更新[物品扩展|粘液科技]', 'Turing_ICE', '2023-07-06', 1, 100, null, 'serve');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (2, 'package.jpg', '客户端', '[1.12.2-1.8][低配福利]骐的整合---纯净基础整合[持续更新]', 'Turing_ICE', '2023-07-04', 5, 100, null, 'Client');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (3, 'bedwars.png', '服务端', '[1.8.x][Spigot]星辰倾城-起床战争服务端', 'Turing_ICE', '2023-07-02', 3, 100, null, 'serve');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (4, 'TheUndeadWar.jpg', '视频', '[冰骐解说]我的世界|亡灵战争', 'Turing_ICE', '2023-07-20', 6, 100, null, 'video');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (5, 'Double.png', '视频', '[冰骐]Minecraft双人默契大挑战——两位初三的帅[dou]气[bi]解密', 'Turing_ICE', '2023-06-14', 6, 100, null, 'video');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (7, 'healthPlug_in.png', '插件', '[信息]BeautyIndicator——轻量级的显血插件[1.8-1.12] [接权搬运]', 'Turing_ICE', '2023-07-11', 3, 100, null, 'plug_in');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (8, 'imgPlug_in.png', '插件', '[娱乐|信息][StarMC]Powder——通过粒子来显示图像！[1.12][接权搬运]', 'Turing_ICE', '2023-07-06', 1, 100, null, 'plug_in');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (9, 'SkillAPIArticle.png', '文章', 'SkillAPI教程and案例——来自定义职业吧！', 'Turing_ICE', '2023-07-08', 8, 100, null, 'article');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (10, 'ElectricityPlug_in.jpg', '插件', '[娱乐|机制]Advanced Electricity——高科技电力[接权搬运][1.10-1.12]', 'Turing_ICE', '2023-07-03', 9, 100, null, 'plug_in');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (11, 'MaintenancePlug_in.jpg', '插件', '[管理|信息][StarMC]MaintenanceMode-维护模式[接权搬运][1.8-1.12]', 'Turing_ICE', '2023-07-06', 4, 100, null, 'plug_in');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (12, 'LanguageBarrierBreakerPlug_in.jpg', '插件', '[信息]Language Barrier Breaker-多种语言[接权搬运][1.8-1.12] ', 'Turing_ICE', '2023-07-05', 2, 100, null, 'plug_in');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (13, 'SuperMobPlug_in.jpg', '插件', '[娱乐|角色]SuperMob---超级怪物[接权搬运][1.7.x-1.11.x] ', 'Turing_ICE', '2023-07-06', 7, 100, null, 'plug_in');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href) VALUES (14, 'skyblockVideo.jpg', '视频', '我的世界[ivtechsky]空岛生存系列', 'Turing_ICE', '2023-07-06', 6, 100, null, 'video');
