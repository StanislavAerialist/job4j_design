create table colour(
    id serial primary key,
    name varchar(255)
);

create table furniture(
    id serial primary key,
    name varchar(255),
    weight int,
    colour_id int references colour(id)
);

insert into colour(name) values ('Birch');
insert into colour(name) values ('Oak');
insert into colour(name) values ('Pine');

insert into furniture(name, weight, colour_id) values ('Table', 12, 1);
insert into furniture(name, weight, colour_id) values ('Chair', 3, 2);
insert into furniture(name, weight, colour_id) values ('Dresser', 10, 3);

select pp.name as "Наименование изделия", p.name as "Название цвета"
from colour as p
join furniture as pp
on pp.colour_id = p.id;

select pp.name as "Наименование изделия", pp.weight as "Вес изделия", p.name as "Название цвета"
from colour as p
join furniture as pp
on pp.colour_id = p.id
where pp.weight > 5 and pp.weight < 12;

select pp.weight as "Вес изделия", p.name as "Название цвета"
from colour as p
join furniture as pp
on pp.colour_id = p.id
where p.name like 'O%';