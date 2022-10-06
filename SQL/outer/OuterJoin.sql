create table departments(
    id serial primary key,
    name varchar(200)
);
create table employees(
    id serial primary key,
    name varchar(200),
    department_id int references departments(id)
);
insert into departments(name) values ('Department 1'), ('Department 2'), ('Department 3');
insert into employees(name, department_id) values ('Igor', 1), ('Pavel', 1), ('Piter', 3);

select d.name as "Название департамента", e.name as "Имя работника"
from departments as d
left outer join employees as e on e.department_id = d.id
where e.department_id is null;

select d.name as "Название департамента", e.name as "Имя работника"
from departments as d
left outer join employees as e on e.department_id = d.id;

select d.name as "Название департамента", e.name as "Имя работника"
from employees as e
right outer join departments as d on e.department_id = d.id;

create table teens(
    id serial primary key,
    name varchar(200),
    gender varchar(200)
);

insert into teens(name, gender) values ('Таня', 'Ж'), ('Миша', 'М'), ('Ирина', 'Ж'), ('Дима', 'М');

select t1.name, t2.name
from teens as t1
cross join teens as t2
where t1.gender != t2.gender;

