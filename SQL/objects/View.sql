create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('���� ������');
insert into students (name) values ('���� ������');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('��������� ������');
insert into authors (name) values ('������� ������');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('������� ������', 1);
insert into books (name, author_id) values ('����������� �����', 1);
insert into books (name, author_id) values ('����������', 1);
insert into books (name, author_id) values ('������� ����', 2);
insert into books (name, author_id) values ('���', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

create view show_students_with_2_or_more_books_and_fl_of_author
    as select substr(a.name, 1, 2) as "������ 2 ����� �����", substr(a.name, 3, 4) as "��������� 4 ����� �����", s.name as student, count(a.name)  from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
         group by (substr(a.name, 1, 2),substr(a.name, 3, 4), s.name, a.name)
         having count(a.name) >= 2 and substr(a.name, 1, 2) like '��' and substr(a.name, 3, 4) like '����' ;

select * from show_students_with_2_or_more_books_and_fl_of_author