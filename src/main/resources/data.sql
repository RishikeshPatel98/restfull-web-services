insert into user_details(id,birth_date,name)
values (10001, current_date(), 'Ram');

insert into user_details(id,birth_date,name)
values (10002, current_date(), 'SiyaRam');

insert into user_details(id,birth_date,name)
values (10003, current_date(), 'Bajrang');

insert into post(id,description,user_id)
values(20001, 'I want to learn Devops', 10001);

insert into post(id,description,user_id)
values(20002, 'I want to learn AWS', 10001);

insert into post(id,description,user_id)
values(20003, 'I want to learn Frontend', 10002);