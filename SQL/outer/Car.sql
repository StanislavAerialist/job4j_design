create table car_bodies(
    id serial primary key,
    name varchar(200)
);
create table car_engines(
    id serial primary key,
    name varchar(200)
);
create table car_transmissions(
    id serial primary key,
    name varchar(200)
);
create table cars(
    id serial primary key,
    name varchar(200),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Седан'), ('Пикап'), ('Хэчбэк');
insert into car_engines(name) values ('V2'), ('V3'), ('V4');
insert into car_transmissions(name) values ('Auto'), ('Mechanics'), ('Robot');
insert into cars(name, body_id, engine_id, transmission_id)
values ('Лада Приора', 1, 1, 2), ('Лада Калина', 1, 2, 3), ('Лада Гранта', 3, 1, 1);
insert into cars(name, body_id, engine_id) values ('Фольцваген Пассат СС', 1, 3);

select c.id, c.name as "Название авто", b.name as "Тип кузова", e.name as "Тип двигателя", t.name as "Тип трансмиссии"
from cars as c
left outer join car_bodies as b
on c.body_id = b.id
left outer join car_engines as e
on c.engine_id = e.id
left outer join car_transmissions as t
on c.transmission_id = t.id;

select b.name as "Тип кузова", c.name "Название авто"
from car_bodies as b
left outer join cars as c
on c.body_id = b.id
where c.id is null;

select e.name as "Тип двигателя", c.name "Название авто"
from car_engines as e
left outer join cars as c
on c.engine_id = e.id
where c.id is null;

select t.name as "Тип трансмиссии", c.name "Название авто"
from car_transmissions as t
left outer join cars as c
on c.transmissions_id = t.id
where c.id is null;