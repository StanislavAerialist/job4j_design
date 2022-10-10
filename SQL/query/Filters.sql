create table type(
	id serial primary key,
	name text
);

create table product(
    id serial primary key,
    name text,
	type_id int references type(id),
	expired_date date,
	price money
);

insert into type(name) values ('СЫР'), ('МОРОЖЕНОЕ'), ('МОЛОКО');
insert into product(name, type_id, expired_date, price) values ('Сыр Косичка', 1, '2022-06-01', 120.00);
insert into product(name, type_id, expired_date, price) values ('Сыр Ламбер', 1, '2022-06-05', 400.00);
insert into product(name, type_id, expired_date, price) values ('Сыр Дружба', 1, '2022-06-03', 50.00);

insert into product(name, type_id, expired_date, price) values ('Мороженое Пломбир 66 копеек', 2, '2022-06-03', 150.00);
insert into product(name, type_id, expired_date, price) values ('Эскимо Ижевское', 2, '2022-06-04', 55.00);
insert into product(name, type_id, expired_date, price) values ('Мороженое Movenpick', 2, '2022-06-06', 650.00);

insert into product(name, type_id, expired_date, price) values ('Молоко Ирбитское', 3, '2022-06-06', 45.00);
insert into product(name, type_id, expired_date, price) values ('Молоко Талицкое', 3, '2022-06-06', 35.00);
insert into product(name, type_id, expired_date, price) values ('Молоко Сысертское', 3, '2022-06-06', 55.00);

select t.name as "Название типа", p.name as "Название продукта"
from type as t
join product as p
on p.type_id = t.id
where t.name like 'СЫР';

select t.name as "Название типа", p.name as "Название продукта"
from type as t
join product as p
on p.type_id = t.id
where p.name like '%Мороженое%';

select * from product where expired_date < current_date;

select name as "Название продукта", price as "Цена"
FROM product
WHERE price = (SELECT MAX(price) FROM product)

select t.name as "Название типа", count(p.name) as "Количество"
from product p
join type t
on p.type_id = t.id
group by t.name;

select t.name as "Название типа", p.name as "Название продукта"
from type as t
join product as p
on p.type_id = t.id
where t.name like 'СЫР' or t.name like 'МОЛОКО';

select t.name as "Название типа", count(p.name) as "Количество"
from product p
join type t
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select p.name as "Название продукта", t.name as "Название типа"
from product p
join type t
on p.type_id = t.id;