
/*DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;*/


create table if not exists users
(
    id         bigserial primary key,
    email      varchar(128) unique not null,
    "password" varchar(128)        not null,
    "role"     varchar(128),
    first_name varchar(128),
    last_name  varchar(128)
);

create table if not exists books
(
    id     bigserial primary key,
    name   varchar(128) not null,
    author varchar(128),
    price  numeric(19, 2) not null
);

create table if not exists orders
(
    id      BIGSERIAL primary key,
    user_id BIGINT      not null REFERENCES users,
    status  varchar(75) not null,
    cost   numeric(19, 2) not null
);

create table if not exists order_items
(
    id BIGSERIAL primary key,
    order_id BIGINT references orders,
    book_id  BIGINT references books,
    quantity INT not null DEFAULT 1,
    price    numeric(19, 2)
);

