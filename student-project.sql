CREATE DATABASE student-project;

use student-project;
commit;

CREATE TABLE student
(
student_no  varchar(3),
student_name varchar(10),
student_dob date,
student_doj date,
PRIMARY KEY (student_no)
);


CREATE TABLE project
(
project_no varchar(3),
project_name varchar(15),
project_dur int(2) ,
project_platform varchar(10),

PRIMARY KEY (project_no)
);

commit;

CREATE TABLE project
(
project_no varchar(3),
project_name varchar(15),
project_dur int(2) ,
project_platform varchar(10),
PRIMARY KEY (project_no)
);
commit;


CREATE TABLE studentproject
(
student_no  varchar(3),
project_no varchar(3),
designation varchar(10),
PRIMARY KEY (student_no,project_no),
CONSTRAINT `FK_studentproject_1` FOREIGN KEY `FK_studentproject_1` (`student_no`)
REFERENCES `student` (`STUDENT_NO`)
CONSTRAINT `FK_studentproject_2` FOREIGN KEY `FK_studentproject_2` (`project_no`)
REFERENCES `project` (`project_no`)
);

commit;

insert into student values (1, "Rushikesh","1998-08-04","2020-02-01");
insert into student values (2, "Avinash","1995-12-30","2020-03-01");

commit;


insert into project values (1, "Library Management System",45,"JAVA");
insert into project values (2, "Scholarship Management System",30,"PHP");

commit;

