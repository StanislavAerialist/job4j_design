create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Phone', 12.02), ('TV', 144.95), ('Notebook', 520.50);
insert into people(name) values ('Анатолий'), ('Григорий'), ('Виталий');
insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (2, 1), (2, 3), (3, 1), (3, 2);

select avg(price) as "Средняя стоимость устройства"
from devices;

select p.name as "Имя обладателя", avg(d.price) as "Средняя стоимость устройств"
from devices_people as dp
join devices as d
on dp.device_id = d.id
join people as p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;
