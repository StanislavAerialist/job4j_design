insert into users(name, item_id) values('Pavel', 1);
insert into role(name, users_id) values('admin', 1);
insert into rules(name) values('rule1');
insert into item(name, comments_id, attachs_id) values('item1', 1, 1);
insert into comments(name) values('comment1');
insert into attachs(name) values('attachs1');
insert into category(name, item_id) values('category1', 1);
insert into state(name, item_id) values('state1', 1);