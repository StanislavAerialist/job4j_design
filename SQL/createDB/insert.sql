insert into role(name) values('admin');
insert into rules(name) values('rule1');
insert into users(name, role_id) values('Pavel', 1);
insert into item(name, users_id, category_id, state_id) values('item1', 1, 1, 1);
insert into comments(name, item_id) values('comment1', 1);
insert into attachs(name, item_id) values('attachs1', 1);
insert into category(name) values('category1');
insert into state(name) values('state1');