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

INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (1, 'skyblock.png', '服务端', '[1.8.x-1.9.x][Spigot]StarFall空岛生存>巨大更新[物品扩展|粘液科技]', 'Turing_ICE', '2023-07-06', 1, 100, '/topic/html?html=1', 'serve', 'StarFall空岛生存', 'StarFall-Skyblock', '原创', '1.8.x-1.9.x', '简体中文', 'https://www.mcbbs.net/thread-792740-1-1.html', 'http://某.盘.com', '# StarFall空岛生存

#### 1.StarFall空岛生存☺
StarFall空岛生存
无mybatis，没学

#### 2.StarFall空岛生存☺
StarFall空岛生存
基于ssm框架，但是没mybatis

#### 3.安装教程☺

1.  安装配置MySQL 5.5以上数据库系统
2.  连接数据库,创建 login_web 数据库导入doc/web_user

#### 4.使用说明☺

1. 下载tomcat8以上版本
配置tomcat，方可运行
');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (2, 'package.jpg', '客户端', '[1.12.2-1.8][低配福利]骐的整合---纯净基础整合[持续更新]', 'Turing_ICE', '2023-07-04', 5, 100, '/topic/html?html=2', 'Client', '骐的整合', 'Integration of Qi', '原创', '1.12.2-1.8', '简体中文', 'https://www.mcbbs.net/thread-1126142-1-1.html', 'https://www.mcbbs.net/thread-1126142-1-1.html', '# 骐的整合

#### 1.骐的整合☺
骐的整合
无mybatis，没学

#### 2.骐的整合☺
骐的整合
基于ssm框架，但是没mybatis

#### 3.安装教程☺

1.  安装配置MySQL 5.5以上数据库系统
2.  连接数据库,创建 login_web 数据库导入doc/web_user

#### 4.骐的整合☺

1. 下载tomcat8以上版本
配置tomcat，方可运行
');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (3, 'bedwars.png', '服务端', '[1.8.x][Spigot]星辰倾城-起床战争服务端', 'Turing_ICE', '2023-07-02', 3, 100, '/topic/html?html=3', 'serve', '星辰倾城-起床战争服务端', 'StarFall-BedWard', '原创', '1.8.x', '简体中文', 'https://www.mcbbs.net/thread-773917-1-1.html', 'http://www.本贴.com', '# 星辰倾城-起床战争服务端

#### 1.星辰倾城-起床战争服务端☺
星辰倾城-起床战争服务端
无mybatis，没学

#### 2.星辰倾城-起床战争服务端☺
星辰倾城-起床战争服务端
基于ssm框架，但是没mybatis

#### 3.安装教程☺

1.  安装配置MySQL 5.5以上数据库系统
2.  连接数据库,创建 login_web 数据库导入doc/web_user

#### 4.星辰倾城-起床战争服务端☺

1. 星辰倾城-起床战争服务端
配置tomcat，方可运行
');
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (4, 'TheUndeadWar.jpg', '视频', '[冰骐解说]我的世界|亡灵战争', 'Turing_ICE', '2023-07-20', 6, 100, '/topic/html?html=4', 'video', '我的世界|亡灵战争', 'Minecraft|War of the Undead', '原创', '1.6', '简体中文', 'https://www.mcbbs.net/thread-878770-1-1.html', 'https://www.mcbbs.net/thread-878770-1-1.html', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (5, 'Double.png', '视频', '[冰骐]Minecraft双人默契大挑战——两位初三的帅[dou]气[bi]解密', 'Turing_ICE', '2023-06-14', 6, 100, '/topic/html?html=5', 'video', '双人默契大挑战', 'The Great Challenge of Mutual Understanding', '原创', '无', '简体中文', 'https://www.mcbbs.net/thread-812503-1-1.html', 'https://www.mcbbs.net/thread-812503-1-1.html', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (7, 'healthPlug_in.png', '插件', '[信息]BeautyIndicator——轻量级的显血插件[1.8-1.12] [接权搬运]', 'Turing_ICE', '2023-07-11', 3, 100, '/topic/html?html=7', 'plug_in', '轻量级的显血', 'BeautyIndicator', '搬运', '1.8-1.12', '简体中文|English', 'https://www.spigotmc.org/resources/beautyindicator-entity-health-in-combat.57546/', 'https://www.spigotmc.org/resources/beautyindicator-entity-health-in-combat.57546/download?version=225018/SupportTheAuthor', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (8, 'imgPlug_in.png', '插件', '[娱乐|信息][StarMC]Powder——通过粒子来显示图像！[1.12][接权搬运]', 'Turing_ICE', '2023-07-06', 1, 100, '/topic/html?html=8', 'plug_in', '粒子图像', 'Powder', '搬运', '1.12', '简体中文|English', 'https://www.spigotmc.org/resources/powder.57227/', 'https://www.spigotmc.org/resources/powder.57227/download?version=224643', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (9, 'SkillAPIArticle.png', '文章', 'SkillAPI教程and案例——来自定义职业吧！', 'Turing_ICE', '2023-07-08', 8, 100, '/topic/html?html=9', 'article', 'SkillAPI教程and案例——来自定义职业吧！', 'SkillAPI Tutorial and Case Study - Customize Your Career!', '原创', '无', '简体中文', 'https://www.mcbbs.net/thread-809466-1-1.html', 'https://www.mcbbs.net/thread-809466-1-1.html', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (10, 'ElectricityPlug_in.jpg', '插件', '[娱乐|机制]Advanced Electricity——高科技电力[接权搬运][1.10-1.12]', 'Turing_ICE', '2023-07-03', 9, 100, '/topic/html?html=10', 'plug_in', '高科技电力', 'Advanced Electricity', '搬运', '1.10-1.12', '简体中文|English', 'https://www.spigotmc.org/resources/advanced-electricity.56514/', 'https://www.spigotmc.org/resources/advanced-electricity.56514/download?version=221252', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (11, 'MaintenancePlug_in.jpg', '插件', '[管理|信息][StarMC]MaintenanceMode-维护模式[接权搬运][1.8-1.12]', 'Turing_ICE', '2023-07-06', 4, 100, '/topic/html?html=11', 'plug_in', '维护模式', 'MaintenanceMode', '搬运', '1.8-1.12', '简体中文|English', 'https://www.spigotmc.org/resources/maintenancemode-bungee-and-spigot-support.40699/', 'https://www.spigotmc.org/resources/maintenancemode-bungee-and-spigot-support.40699/download?version=217708', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (12, 'LanguageBarrierBreakerPlug_in.jpg', '插件', '[信息]Language Barrier Breaker-多种语言[接权搬运][1.8-1.12] ', 'Turing_ICE', '2023-07-05', 2, 100, '/topic/html?html=12', 'plug_in', '多种语言', 'Language Barrier Breaker', '搬运', '1.8-1.12', '简体中文|English', 'https://www.spigotmc.org/resources/rosetta-stone-language-barrier-breaker.55570/', 'https://www.spigotmc.org/resources/rosetta-stone-language-barrier-breaker.55570/download?version=217291', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (13, 'SuperMobPlug_in.jpg', '插件', '[娱乐|角色]SuperMob---超级怪物[接权搬运][1.7.x-1.11.x] ', 'Turing_ICE', '2023-07-06', 7, 100, '/topic/html?html=13', 'plug_in', '超级怪物', 'SuperMob', '搬运', '1.7.x-1.11.x', '简体中文|English', 'https://www.spigotmc.org/resources/supermob.42924/', 'https://www.spigotmc.org/resources/supermob.42924/download?version=215045', null);
INSERT INTO web.topic (id, icon, label, title, author, date, comment, view, href, label_href, titlename, titleenglishname, source, version, language, address, download, content) VALUES (14, 'skyblockVideo.jpg', '视频', '我的世界[ivtechsky]空岛生存系列', 'Turing_ICE', '2023-07-06', 6, 100, '/topic/html?html=14', 'video', '空岛生存系列', 'Skyblock Survival Series', '原创', '无', '简体中文', 'https://www.mcbbs.net/thread-1054576-1-1.html', 'https://www.mcbbs.net/thread-1054576-1-1.html', null);
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

INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试55011测试', '2023-10-16 00:00:00', '123456', 'null.jpg', '新用户BAE846', 9, 101, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试31276测试', '2023-01-09 00:00:00', 'asd', 'null.png', 'asd', 12, 102, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试88860测试', '2023-03-14 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 8, 103, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试96078测试', '2023-06-19 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 4, 104, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试80559测试', '2023-07-10 00:00:00', '123456', 'null.jpg', '新用户BAE846', 8, 105, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试23484测试', '2022-12-22 00:00:00', 'asd', 'null.png', 'asd', 12, 106, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试98093测试', '2022-12-21 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 5, 107, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试1809测试', '2022-12-26 00:00:00', 'asd', 'null.png', 'asd', 1, 108, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试36300测试', '2023-02-01 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 109, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试72902测试', '2023-01-21 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 110, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试73703测试', '2023-08-23 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 5, 111, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试25598测试', '2023-05-03 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 4, 112, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试2850测试', '2023-11-02 00:00:00', 'asd', 'null.png', 'asd', 1, 113, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试65193测试', '2023-05-15 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 5, 114, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试10047测试', '2023-11-22 00:00:00', '123456', 'null.jpg', '新用户BAE846', 6, 115, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试56526测试', '2023-07-17 00:00:00', 'asd', 'null.png', 'asd', 5, 116, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试74577测试', '2023-04-30 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 6, 117, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试91449测试', '2022-12-23 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 11, 118, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试54557测试', '2023-01-09 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 8, 119, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试86346测试', '2023-06-04 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 1, 120, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试59947测试', '2022-12-19 00:00:00', '123456', 'null.jpg', '新用户BAE846', 6, 121, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试60547测试', '2023-03-16 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 1, 122, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试5762测试', '2023-01-16 00:00:00', 'asd', 'null.png', 'asd', 4, 123, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试36123测试', '2022-12-22 00:00:00', 'asd', 'null.png', 'asd', 8, 124, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试16534测试', '2023-05-18 00:00:00', '123456', 'null.jpg', '新用户BAE846', 6, 125, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试24463测试', '2023-04-22 00:00:00', 'asd', 'null.png', 'asd', 12, 126, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试83286测试', '2023-10-07 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 11, 127, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试97721测试', '2023-04-03 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 128, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试73372测试', '2023-03-04 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 2, 129, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试78889测试', '2023-10-09 00:00:00', '123456', 'null.jpg', '新用户BAE846', 2, 130, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试36620测试', '2023-05-27 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 2, 131, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试12223测试', '2023-01-11 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 10, 132, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试9155测试', '2023-08-12 00:00:00', 'asd', 'null.png', 'asd', 6, 133, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试58436测试', '2023-04-10 00:00:00', 'asd', 'null.png', 'asd', 3, 134, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试30503测试', '2023-07-19 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 135, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试18051测试', '2023-06-19 00:00:00', '123456', 'null.jpg', '新用户BAE846', 3, 136, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试72902测试', '2023-02-03 00:00:00', 'asd', 'null.png', 'asd', 1, 137, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试79909测试', '2023-10-14 00:00:00', '123456', 'null.jpg', '新用户BAE846', 1, 138, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试81226测试', '2023-03-18 00:00:00', 'asd', 'null.png', 'asd', 9, 139, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试35774测试', '2022-12-08 00:00:00', '123456', 'null.jpg', '新用户BAE846', 12, 140, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试87534测试', '2023-11-04 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 7, 141, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试5771测试', '2023-03-10 00:00:00', '123456', 'null.jpg', '新用户BAE846', 5, 142, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试31653测试', '2023-05-18 00:00:00', 'asd', 'null.png', 'asd', 6, 143, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试3038测试', '2023-04-23 00:00:00', '123456', 'null.jpg', '新用户BAE846', 8, 144, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试95955测试', '2023-04-15 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 1, 145, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试78955测试', '2022-12-04 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 11, 146, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试44729测试', '2023-02-12 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 2, 147, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试78664测试', '2023-07-13 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 11, 148, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试36087测试', '2023-01-23 00:00:00', '123456', 'null.jpg', '新用户BAE846', 9, 149, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试24523测试', '2023-09-25 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 1, 150, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试36174测试', '2023-11-02 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 8, 151, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试30097测试', '2023-07-31 00:00:00', '123456', 'null.jpg', '新用户BAE846', 4, 152, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试68399测试', '2023-11-03 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 3, 153, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试21915测试', '2023-07-27 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 3, 154, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试62367测试', '2023-09-19 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 12, 155, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试87199测试', '2023-01-09 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 7, 156, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试44685测试', '2023-11-08 00:00:00', 'asd', 'null.png', 'asd', 3, 157, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试47906测试', '2023-04-06 00:00:00', '123456', 'null.jpg', '新用户BAE846', 7, 158, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试79088测试', '2023-11-23 00:00:00', 'asd', 'null.png', 'asd', 6, 159, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试31281测试', '2023-09-01 00:00:00', 'asd', 'null.png', 'asd', 6, 160, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试60615测试', '2023-10-17 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 10, 161, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试21193测试', '2023-04-12 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 9, 162, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试50767测试', '2023-04-13 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 9, 163, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试56383测试', '2023-06-02 00:00:00', '123456', 'null.jpg', '新用户BAE846', 1, 164, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试54341测试', '2023-07-10 00:00:00', '123456', 'null.jpg', '新用户BAE846', 12, 165, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试98515测试', '2023-05-17 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 6, 166, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试76276测试', '2023-01-13 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 167, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试46905测试', '2023-11-18 00:00:00', '123456', 'null.jpg', '新用户BAE846', 11, 168, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试84865测试', '2023-05-19 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 4, 169, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试89397测试', '2023-01-18 00:00:00', 'asd', 'null.png', 'asd', 2, 170, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试31412测试', '2023-10-09 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 171, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试9991测试', '2023-06-07 00:00:00', 'asd', 'null.png', 'asd', 1, 172, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试55217测试', '2023-07-14 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 173, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试26147测试', '2023-02-21 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 12, 174, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试31391测试', '2023-04-04 00:00:00', '123456', 'null.jpg', '新用户BAE846', 12, 175, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试70739测试', '2023-01-23 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 2, 176, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试96577测试', '2023-10-21 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 10, 177, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试37239测试', '2023-06-22 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 178, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试16633测试', '2023-02-02 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 5, 179, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试76535测试', '2023-06-08 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 12, 180, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试95889测试', '2023-08-27 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 10, 181, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试26767测试', '2023-06-22 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 1, 182, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试43517测试', '2022-12-31 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 6, 183, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试26382测试', '2023-04-16 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 184, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试11188测试', '2023-07-04 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 6, 185, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试62132测试', '2023-03-08 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 8, 186, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试91875测试', '2023-04-04 00:00:00', 'asd', 'null.png', 'asd', 7, 187, 'asdasdasd');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试13516测试', '2023-05-19 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 10, 188, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试35790测试', '2023-01-07 00:00:00', 'qwe', 'qwe.jpg', 'qwenba', 9, 189, '我叫qwe，一个用户a');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试69718测试', '2023-05-18 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 12, 190, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试51265测试', '2023-10-04 00:00:00', '123456', 'null.jpg', '新用户BAE846', 3, 191, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试82590测试', '2023-10-05 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 3, 192, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试16348测试', '2023-04-16 00:00:00', 'admin', '202307240226562037611190.jpg', '管理员admin', 8, 193, '我是管理员!!');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试94782测试', '2023-07-02 00:00:00', '123456', 'null.jpg', '新用户BAE846', 8, 194, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试46611测试', '2023-10-20 00:00:00', '123456', 'null.jpg', '新用户BAE846', 12, 195, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试10967测试', '2023-07-18 00:00:00', '123456', 'null.jpg', '新用户BAE846', 4, 196, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试27817测试', '2023-03-16 00:00:00', '123456', 'null.jpg', '新用户BAE846', 2, 197, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试84522测试', '2023-03-17 00:00:00', '1322621134', 'null.jpg', '新用户19429E', 8, 198, null);
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试82294测试', '2023-08-14 00:00:00', '123456', 'null.jpg', '新用户BAE846', 10, 199, 'nzTC');
INSERT INTO web.comment (content, date, user, head, name, topicid, id, introduce) VALUES ('测试3706测试', '2023-04-25 00:00:00', 'asd', 'null.png', 'asd', 4, 200, 'asdasdasd');
