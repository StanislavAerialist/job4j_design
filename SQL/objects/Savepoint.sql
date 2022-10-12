create table beer(id serial primary key, name varchar(50));

insert into beer(name) values ('Blue Moon'), ('Holsten');

begin transaction;

insert into beer(name) values('Livu');

savepoint first_savepoint;

insert into beer(name) values('Bira');

select * from beer;

savepoint second_savepoint;

delete from beer where name = 'Bira';

select * from beer;

rollback to second_savepoint;

insert into beer(name) values('Pacho');

select * from beer;

commit;

select * from beer;