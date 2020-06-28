create database house_keeper;

use house_keeper;

create table `user`
(
    `user_id`                 int primary key auto_increment,
    `user_name`               varchar(50) not null unique,
    `user_pwd`                varchar(32) not null,
    `user_encrypted_question` varchar(50) not null,
    `user_encrypted_answer`   varchar(32) not null,
    `user_reg_time`           varchar(10) not null,
    `user_last_time`          varchar(10) default null,
    `user_sex`                enum ('男','女','保密'),
    `user_age`                int         not null,
    `user_phone`              varchar(11) not null,
    `user_address`            varchar(60) not null
);

create table `book`
(
    `book_id`       int primary key auto_increment,
    `user_id`       int         not null,
    `book_name`     varchar(50) not null,
    `book_desc`     tinytext,
    `book_add_time` varchar(10) not null,
    unique (user_id, book_name),
    foreign key (user_id) references `user` (user_id)
);

create table `order`
(
    `order_id`        int primary key auto_increment,
    `book_id`         int         not null,
    `order_name`      varchar(50) not null,
    `order_price`     decimal(8, 2),
    `order_way`       enum ('现金','支付宝','微信','银行卡','信用卡','其他'),
    `order_mod`       enum ('收入','支出'),
    `order_time`      varchar(10) not null,
    `order_cate`      varchar(20) not null,
    `order_desc`      tinytext,
    foreign key (book_id) references `book` (book_id)
);