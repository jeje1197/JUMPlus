-- Create database for Student Gradebook Project -- 
drop database if exists student_gradebook;
create database student_gradebook;

use student_gradebook;


-- Create teachers table -- 
drop table if exists teachers;
create table teachers (
	teacher_id int unique auto_increment,
    teacher_name varchar(40) unique not null,
    primary key(teacher_id)
);

-- Create login table -- 
drop table if exists teacher_login;
create table teacher_login (
	login_id int unique auto_increment,
    login_email varchar(40) unique not null,
    login_password varchar(40) not null,
    primary key(login_id),
    foreign key(login_id) references teachers(teacher_id)
);

insert into teachers(teacher_name) values ('Randy Crushu'); 
insert into teacher_login(login_email, login_password) values ('randy@gmail.com', 'randy1');

select * from teachers;
select * from teacher_login;

select * from teacher_login
join teachers
on
	teacher_login.login_id = teachers.teacher_id;

-- Create teachers table -- 
-- drop table if exists classes;
-- create table classes (
-- 	class_id int unique auto_increment,
--     class_name varchar(40) unique not null,
--     primary key(class_id),
--     foreign key(class_id) references students(student_id)
-- );

-- -- Create teachers table -- 
-- drop table if exists students;
-- create table students (
-- 	student_id int unique auto_increment,
--     student_name varchar(40) unique not null,
--     student_grad int not null,
--     primary key(student_id)
-- );


select * from teacher_login 
join teachers on teacher_login.login_id = teachers.teacher_id
where teacher_login.login_email = 'joseph@gmail.com' and teacher_login.login_password = 'joseph';

