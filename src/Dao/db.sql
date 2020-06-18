create database house_keeper;

use house_keeper;

create table `user` (
    `user_id`                 int primary key auto_increment,
    `user_name`               varchar(50) not null unique,
    `user_pwd`                varchar(32) not null,
    `user_encrypted_question` varchar(50),
    `user_encrypted_answer`   varchar(32),
    `user_reg_time`           varchar(10) not null,
    `user_last_time`          varchar(10) default null,
    `user_sex`                enum ('男','女','保密') default '保密',
    `user_age`                int,
    `user_phone`              varchar(11) not null,
    `user_address`            varchar(60),
    `user_head_thumb`         mediumblob
);

create table `book` (
    `book_id`        int primary key auto_increment,
    `user_id`        int         not null,
    `book_name`      varchar(50) not null,
    `book_desc`      tinytext,
    `book_add_time`  varchar(10) not null,
    unique (user_id, book_name)
);

create table `order` (
    `order_id`        int primary key auto_increment,
    `book_id`         int         not null,
    `order_name`      varchar(50) not null,
    `order_price`     decimal(8, 2)                           default '0.00',
    `order_way`       enum ('现金','支付宝','微信','银行卡','信用卡','其他') default '现金',
    `order_mod`       enum ('收入','支出')                        default '支出 ',
    `order_time`      varchar(10) not null,
    `order_cate`      varchar(20) not null,
    `order_desc`      tinytext,
    `order_image_src` mediumblob
);

insert into `user` values (0, 'a', md5(123), null, null, unix_timestamp(now()), null, null, 18, 13000000000, '四川成都', null);
insert into `user` values (0, 'b', md5(123), null, null, unix_timestamp(now()), null, null, 20, 13000000000, '四川成都', null);
insert into `user` values (0, 'c', md5(123), null, null, unix_timestamp(now()), null, null, 25, 13000000000, '四川成都', null);
insert into `user` values (0, 'd', md5(123), null, null, unix_timestamp(now()), null, null, 25, 13000000000, '四川成都', null);
insert into `user` values (0, 'e', md5(123), null, null, unix_timestamp(now()), null, null, 35, 13000000000, '四川成都', null);
insert into `user` values (0, 'f', md5(123), null, null, unix_timestamp(now()), null, null, 11, 13000000000, '四川成都', null);

insert into `book` values (0, 1, 'book_1', '家庭账本', unix_timestamp(now()));
insert into `book` values (0, 1, 'book_2', '家庭账本', unix_timestamp(now()));
insert into `book` values (0, 2, 'book_1', '家庭账本', unix_timestamp(now()));
insert into `book` values (0, 3, 'book_0', '家庭账本', unix_timestamp(now()));
insert into `book` values (0, 3, 'book_1', '家庭账本', unix_timestamp(now()));
insert into `book` values (0, 3, 'book_2', '家庭账本', unix_timestamp(now()));
insert into `book` values (0, 4, 'book_4', '家庭账本', unix_timestamp(now()));

insert into `order` values (0, 1, '书籍购买', 50.89, null, null, unix_timestamp(now()), '学习', null, null);
insert into `order` values (0, 1, '午餐', 12, null, null, unix_timestamp(now()), '餐饮', null, null);
insert into `order` values (0, 1, '零食', 8.5, null, null, unix_timestamp(now()), '餐饮', null, null);
insert into `order` values (0, 1, '下午茶', 20, null, null, unix_timestamp(now()), '餐饮', null, null);
insert into `order` values (0, 2, '电脑', 4000, null, null, unix_timestamp(now()), '电子产品', null, null);
insert into `order` values (0, 2, '打印机', 1500, null, null, unix_timestamp(now()), '电子产品', null, null);
insert into `order` values (0, 3, '房租', 11232.58, null, null, unix_timestamp(now()), '生活', null, null);
insert into `order` values (0, 1, '大头鬼', 100, '现金', null, unix_timestamp(now()), '生活', '锤子', null);