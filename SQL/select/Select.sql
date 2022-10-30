CREATE TABLE company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name)
values (1, 'BBJ'), (2, 'MSC Production'), (3,'LT_Indastry'), (4,'NASA'), (5,'Black Mesa');

insert into person (id, name, company_id)
values (1, 'Ember', 1), (2, 'Luke', 2), (3,'Ethan', 3), (4,'Ruf', 4), (5,'Howard', 5), (6,'Rufus', 4);

select p.name, c.name
from person as p
join company as c
on p.company_id=c.id
where p.company_id!=5;

select c.name, count(p.company_id)
from person as p
join company as c
on p.company_id=c.id
group by c.name
having count(p.id) = (
 	select max(m.m_id) from (
 	    select company_id, count(id) as m_id
 	    from person
 	    group by company_id) as m);