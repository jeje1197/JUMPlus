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
insert into teachers(teacher_name) values ('Joseph Admin'); 
insert into teacher_login(login_email, login_password) values ('joseph@gmail.com', 'joseph');

select * from teacher_login
join teachers
on
	teacher_login.login_id = teachers.teacher_id;

-- Create classes table -- 
drop table if exists classes;
create table classes (
	class_id int unique auto_increment,
    class_name varchar(40) unique not null,
    teacher_id int not null,
    primary key(class_id)
);

insert into classes (class_name, teacher_id) values ('Intro to Java', 2);
select * from classes;

-- Create students table -- 
drop table if exists students;
create table students (
	student_id int unique auto_increment,
    student_name varchar(40) unique not null,
    primary key(student_id)
);

insert into students (student_name) values ("Joseph Evans");
insert into students (student_name) values ("Elijah Busick");
insert into students (student_name) values ("Lebron James");
insert into students (student_name) values ("Stephen Curry");
insert into students (student_name) values ("De'Aaron Fox");
insert into students (student_name) values ("Cliff");
insert into students (student_name) values ("Sean Bryson");

select * from students order by student_id;

-- Create student_classes table --
drop table if exists student_classes;
create table student_classes (
	student_id int not null,
    class_id int not null,
    grade int not null,
    primary key(student_id, class_id)
);

insert into student_classes (class_id, student_id, grade) values (1, 5, 80);
insert into student_classes (class_id, student_id, grade) values (1, 6, 95);

select * from student_classes;

-- delete from student_classes where class_id = 1 and student_id = 5;

-- Select all students in class -- 
select * from student_classes where class_id = 1;

-- Students in class (sorted)--
select * from student_classes
join students on student_classes.student_id = students.student_id
where class_id = 1 order by student_name, grade;

-- Students not enrolled in class -- 
select * from students
where student_id not in (
	select student_id from student_classes
    where class_id = 1
);