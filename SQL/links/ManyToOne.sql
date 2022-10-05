create table colour(
    id serial primary key,
    name varchar(255)
);

create table furniture(
    id serial primary key,
    name varchar(255),
    colour_id int references colour(id)
);
