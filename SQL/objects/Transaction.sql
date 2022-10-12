create table beer(id serial primary key, name varchar(50));

insert into beer(name) values ('Blue Moon'), ('Holsten');

select * from beer;

# transaction 1

begin transaction isolation level serializable;

update beer set name = 'Gara Guzu' where id = 2;

commit;

# transaction 2

begin transaction isolation level serializable;

delete from beer where id = 2;

commit;