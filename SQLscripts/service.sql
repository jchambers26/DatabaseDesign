drop table Service;

create table Service(
serviceType varchar2(20),
uninsuredStudentRate float(5) NOT NULL,
insuredStudentRate float(5) NOT NULL,
employeeRate float(5) NOT NULL,
PRIMARY KEY(serviceType)
);

insert into Service(serviceType, uninsuredStudentRate, insuredStudentRate, employeeRate) values('Immunization', 50.00, 25.00, 15.00);
insert into Service(serviceType, uninsuredStudentRate, insuredStudentRate, employeeRate) values('General Medicine', 100.00, 50.00, 25.00);
insert into Service(serviceType, uninsuredStudentRate, insuredStudentRate, employeeRate) values('CAPS', 60.00, 30.00, 15.00);
insert into Service(serviceType, uninsuredStudentRate, insuredStudentRate, employeeRate) values('Lab/Testing', 90.00, 45.00, 20.00);

grant select on Service to Public;
