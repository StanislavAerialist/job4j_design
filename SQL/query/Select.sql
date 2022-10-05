create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Elephant', 25, date '1122-01-01');
insert into fauna(name, avg_age, discovery_date) values ('Donkey', 15, date '1245-02-02');
insert into fauna(name, avg_age, discovery_date) values ('Horse', 16, date '985-05-15');
insert into fauna(name, avg_age, discovery_date) values ('Goldfish', 5, date '1549-03-26');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 AND avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';