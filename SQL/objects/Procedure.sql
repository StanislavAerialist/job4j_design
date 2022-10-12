create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_1', 'producer_1', 0, 32);

create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products where id = d_id and count = 0;
    END
$$;

call delere_data(1);

call insert_data('product_1', 'producer_1', 0, 32);

create or replace function f_delete_data(d_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        delete from products where id = d_id and count = 0;
    end;
$$;

select f_delete_data(2);