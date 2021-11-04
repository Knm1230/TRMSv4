drop table if exists emp_roles cascade;
drop table if exists event_types cascade;
drop table if exists overcharges cascade;
drop table if exists grade_formats cascade;
drop table if exists employees cascade;
drop table if exists requests cascade;

create table if not exists emp_roles(
	id serial primary key,
	role_name varchar(25)
);

create table if not exists event_types(
	id serial primary key,
	type_name varchar(30),
	coverage numeric(5,2)
);

create table if not exists overcharges(
	id serial primary key,
	reason varchar(256)
);

create table if not exists grade_formats(
	id serial primary key,
	threshold varchar(20)
);

create table if not exists employees(
	id serial primary key,
	first_name varchar(20),
	last_name varchar(30),
	balance numeric(6,2) default 1000,
	user_name varchar(20),
	pass varchar(64),
	special_role int references emp_roles(id),
	supervisor int references employees(id) on delete set null,
	dep_head int references employees(id) on delete set null,
	ben_co int references employees(id) on delete set null
);

create table if not exists requests(
	id serial primary key,
	status int default 0,
	request_location varchar(100),
	request_cost numeric(6,2),
	file_path text,
	description text,
	work_time int,
	last_update_time bigint,
	start_time bigint,
	grade varchar(20),
	grade_format int references grade_formats(id),
	event_type int references event_types(id),
	emp_id int references employees(id),
	current_desk int references employees(id),
	overcharge int references overcharges(id)
);

create table if not exists messages(
	id serial primary key,
	from_id int references employees(id),
	to_id int references employees(id),
	req_id int references requests(id),
	msg_body varchar(100)
);



--populating reference tables
insert into emp_roles values(default, 'Employee');
insert into emp_roles values(default, 'Supervisor');
insert into emp_roles values(default, 'Department Head');
insert into emp_roles values(default, 'Ben. Coordinator');
insert into emp_roles values(default, 'Super Dep Head');

insert into event_types values(default, 'University Course', .80);
insert into event_types values(default,'Seminar', .60);
insert into event_types values(default,'Certification Prep Class', .75);
insert into event_types values(default,'Certification', 1);
insert into event_types values(default,'Technical Training', .90);
insert into event_types values(default,'Other', .30);

insert into grade_formats values(default, 'Presentation');
insert into grade_formats values(default, 'Percentage');




INSERT INTO employees VALUES(default, 'john', 'doe', 1000.00, 'jdoe', '1', 4, 1, 1, 1);
INSERT INTO employees VALUES(default, 'james', 'doe', 1000.00, 'jdoe2', '2', 3, 2, 2, 1);
INSERT INTO employees VALUES(default, 'jack', 'supervisor', 1000.00, 'jdoe3', '3', 2, 2, 2, 1);
INSERT INTO employees VALUES(default, 'jerry', 'department head', 1000.00, 'jdoe4', '4', 1, 3, 2, 1);
INSERT INTO employees VALUES(default, 'joe', 'benCo', 1000.00, 'jdoe5', '5', 5, 3, 2, 1);

