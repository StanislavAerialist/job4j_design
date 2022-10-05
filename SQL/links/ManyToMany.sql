create table order(
    id serial primary key,
    number int
);

create table product(
    id serial primary key,
    name varchar(255)
    price money
);

create table order_product(
    id serial primary key,
    order_id int references order(id),
    product_id int references product(id)
);