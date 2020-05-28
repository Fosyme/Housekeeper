create database housekeeper;
use housekeeper;
create table `user`
(
    `user_id`                 int primary key auto_increment,
    `user_name`               varchar(50)     not null unique,
    `user_pwd`                varchar(32)     not null,
    `user_encrypted_question` varchar(50),
    `user_encrypted_answer`  varchar(32),
    `user_reg_time`           long            not null,
    `user_last_time`          long,
    `user_sex`                enum ('男','女','保密') default '保密',
    `user_age`                int,
    `user_phone`              varchar(11)     not null,
    `user_address`            varchar(60),
    `user_head_thumb`         mediumblob
);

create table `book`
(
    `book_id`        int primary key auto_increment,
    `user_id`        int             not null,
    `book_name`      varchar(50)     not null,
    `book_desc`      tinytext,
    `book_add_time`  long            not null,
    `book_last_time` long,
    unique (user_id, book_name)
);

create table `order`
(
    `order_id`        int primary key not null auto_increment,
    `book_id`         int             not null,
    `order_name`      varchar(50)     not null,
    `order_price`     decimal(8, 2)                                       default '0.00',
    `order_way`       enum ('现金','支付宝','微信','银行卡','信用卡','其他') default '现金',
    `order_mod`       enum ('收入','支出')                                default '支出 ',
    `order_time`      long            not null,
    `order_cate`      varchar(20)     not null,
    `order_desc`      tinytext,
    `order_image_src` mediumblob
)
