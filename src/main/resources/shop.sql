drop database if exists shop;
create database shop;
use shop;

CREATE TABLE t_product (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(20),
  price double
);
insert into t_product values(1,'phone1',1300),(2,'phone2',2000),(3,'phone3',500),(4,'phone4',600);

CREATE TABLE t_user (
  id int PRIMARY KEY AUTO_INCREMENT,
  username varchar(20),
  password varchar(20),
  phone varchar(20),
  address varchar(100)
);
insert into t_user values(1,'a1','123','139','nj');
insert into t_user values(2,'a2','123','138','nj');

CREATE TABLE t_order (
  id int PRIMARY KEY AUTO_INCREMENT,
  no varchar(50) ,
  price double,
  user_id int,
  CONSTRAINT t_order_ibfk_1 FOREIGN KEY (user_id) REFERENCES t_user (id)
);

CREATE TABLE t_item(
  id int PRIMARY KEY AUTO_INCREMENT,
  product_id int,
  num int,
  price double,
  order_id int,
  CONSTRAINT t_item_ibfk_1 FOREIGN KEY (product_id) REFERENCES t_product (id),
  CONSTRAINT t_item_ibfk_2 FOREIGN KEY (order_id) REFERENCES t_order (id)
);


CREATE TABLE t_cart (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  product_id int,
  num int,
  price double,
  CONSTRAINT PRODUCT_CART_CONS FOREIGN KEY (product_id) REFERENCES t_product (id),
  CONSTRAINT USER_CART_CONS FOREIGN KEY (user_id) REFERENCES t_user (id)
);