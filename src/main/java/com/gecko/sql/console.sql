create DATABASE project_module_4;

use project_module_4;

create table category(
    id int primary key NOT NULL auto_increment,
    name varchar(255) NOT NULL unique ,
    description varchar(255) NOT NULL,
    status bit(1) default 1
);

create table product(
       id int primary key NOT NULL auto_increment,
       category_id int NOT NULL ,
       foreign key (category_id) REFERENCES category(id),
       name varchar(255) NOT NULL unique,
       description varchar(255) NOT NULL,
       image_url varchar(255) NOT NULL ,
       price double,
       quantity int,
       status bit(1) default 1
);

create table customer(
                         id int primary key NOT NULL auto_increment,
                         name varchar(255) NOT NULL,
                         email varchar(255) NOT NULL unique,
                         image_url varchar(255) NOT NULL,
                         address varchar(255) NOT NULL,
                         phone_number varchar(255) not null ,
                         password varchar(255) NOT NULL,
                         admin bit(1) default 0 not null,
                         banned bit(1) default 0 not null
);

create table orders(
                       id int primary key NOT NULL auto_increment,
                       customer_id int not null,
                       foreign key (customer_id) references customer(id),
                       total double NOT NULL
);

create table order_detail(
                       order_id int not null,
                       foreign key (order_id) references orders(id),
                       product_id int not null,
                       foreign key (product_id) references product(id),
                       quantity int NOT NULL,
                       price double NOT NULL,
                       primary key (order_id, product_id)
);

ALTER TABLE orders
    ADD COLUMN status INT DEFAULT 1 NOT NULL;

create table cart(
                       id int primary key NOT NULL auto_increment,
                       customer_id int not null,
                       foreign key (customer_id) references customer(id)
);

create table cart_item(
                            id int primary key NOT NULL auto_increment,
                            cart_id int not null,
                            foreign key (cart_id) references cart(id),
                            product_id int not null,
                            quantity int NOT NULL
);
