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

insert into type(name) values ('���'), ('���������'), ('������');
insert into product(name, type_id, expired_date, price) values ('��� �������', 1, date '01.06.2022', 120.00);
insert into product(name, type_id, expired_date, price) values ('��� ������', 1, date '05.06.2022', 400.00);
insert into product(name, type_id, expired_date, price) values ('��� ������', 1, date '03.06.2022', 50.00);

insert into product(name, type_id, expired_date, price) values ('��������� ������� 66 ������', 2, date '03.06.2022', 150.00);
insert into product(name, type_id, expired_date, price) values ('������ ��������', 2, date '04.06.2022', 55.00);
insert into product(name, type_id, expired_date, price) values ('��������� Movenpick', 2, date '06.06.2022', 650.00);

insert into product(name, type_id, expired_date, price) values ('������ ���������', 3, date '06.06.2022', 45.00);
insert into product(name, type_id, expired_date, price) values ('������ ��������', 3, date '06.06.2022', 35.00);
insert into product(name, type_id, expired_date, price) values ('������ ����������', 3, date '06.06.2022', 55.00);

select t.name as "�������� ����", p.name as "�������� ��������"
from type as t
join product as p
on p.type_id = t.id
where t.name like '���';

select t.name as "�������� ����", p.name as "�������� ��������"
from type as t
join product as p
on p.type_id = t.id
where p.name like '%���������%';

select * from product where expired_date < date '05.06.2022';

select t.name as "�������� ����", max(p.price) as "������������ ���������"
from type as t
join product as p
on p.type_id = t.id
group by t.name;

select t.name as "�������� ����", count(p.name) as "����������"
from product p
join type t
on p.type_id = t.id
group by t.name;

select t.name as "�������� ����", p.name as "�������� ��������"
from type as t
join product as p
on p.type_id = t.id
where t.name like '���' or t.name like '������';

select t.name as "�������� ����", count(p.name) as "����������"
from product p
join type t
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select p.name as "�������� ��������", t.name as "�������� ����"
from product p
join type t
on p.type_id = t.id;