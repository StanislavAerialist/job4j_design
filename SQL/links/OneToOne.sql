create table weapon(
    id serial primary key,
    name text,
    number int
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table weapon_people(
    id serial primary key,
    weapon_id int references weapon(id) unique,
    people_id int references people(id) unique
);