create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

alter table products disable trigger discount_trigger;

drop trigger discount_trigger on products;

create or replace function tax15()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.15
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax15_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax15();

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 2, 100);

alter table products disable trigger tax15_trigger;

create or replace function tax20()
    returns trigger as
$$
    BEGIN
		new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax20_trigger
    before insert
    on products
    for each row
    execute procedure tax20();

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 1, 200);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) VALUES (new.name, new.price, now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_trigger
    after insert
    on products
    for each row
    execute procedure history();

insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 4, 30);