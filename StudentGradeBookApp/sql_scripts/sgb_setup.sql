drop database if exists student_gradebook;
create database student_gradebook;

use student_gradebook;


-- Create teachers table -- 
drop table if exists teachers;
create table teachers (
	teacher_id int unique auto_increment,
    teacher_email varchar(40) not null,
    teacher_password varchar(40) not null,
    primary key(teacher_id)
);

insert into teachers (teacher_email, teacher_password) values ('randy@gmail.com', 'randy');

select * from teachers;

