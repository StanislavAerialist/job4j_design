create table birds(
    id serial primary key,
    name text,
    age smallint,
    live boolean
);
insert into birds(name, age, live) values ('Eagle', 3, true );
select * from birds;
update birds set age = 4;
select * from birds;
delete from birds;
select * from birds;