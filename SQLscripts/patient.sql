drop table Patient;

CREATE TABLE Patient(
patientID integer,
name varchar2(50) NOT NULL,
birthday date NOT NULL,
type varchar2(10) NOT NULL,
insurance integer,
PRIMARY KEY(patientID),
constraint patient_type check ((type = 'Student') or (type = 'Employee')),
constraint check_insurance check ((type='Employee') or (insurance IS NOT NULL)));
);

drop sequence patientID_seq;
create sequence patientID_seq;


drop trigger patientID_trigger;
create trigger patientID_trigger
before insert on Patient
for each row
begin
select patientID_seq.nextval
into :new.patientID
from dual; end;
/

insert into Patient(name, birthday, type) values('Preston Nixon', DATE '1973-01-01', 'Employee');
insert into Patient(name, birthday, type, insurance) values('Ana Washington', DATE '2001-12-15', 'Student', 1);
insert into Patient(name, birthday, type, insurance) values('Jason Hunter', DATE '1952-07-29', 'Student', 0);
insert into Patient(name, birthday, type) values('Rodrigo Hodges', DATE '1984-03-12', 'Employee');
insert into Patient(name, birthday, type) values('Michaela Hawkins', DATE '1968-09-07', 'Employee');
insert into Patient(name, birthday, type, insurance) values('Travis Ochoa', DATE '2003-04-26', 'Student', 1);
insert into Patient(name, birthday, type, insurance) values('Alex Duke', DATE '1997-02-12', 'Student', 0);
insert into Patient(name, birthday, type, insurance) values('Rose Holt', DATE '1963-07-13', 'Student', 1);
insert into Patient(name, birthday, type) values('Gilligan Floyd', DATE '1993-02-17', 'Employee');
insert into Patient(name, birthday, type) values('Rafael Start', DATE '1972-07-13', 'Employee');
insert into Patient(name, birthday, type, insurance) values('Denny Fitzpatrick', DATE '2001-03-29', 'Student', 0);
insert into Patient(name, birthday, type, insurance) values('Rick David', DATE '2002-12-25', 'Student', 0);
insert into Patient(name, birthday, type, insurance) values('Ed Bowman', DATE '2000-09-02', 'Student', 1);
insert into Patient(name, birthday, type, insurance) values('Mercedes Underwood', DATE '1998-11-23', 'Student', 1);
insert into Patient(name, birthday, type, insurance) values('Jaden McCoy', DATE '1997-03-19', 'Student', 0);
insert into Patient(name, birthday, type, insurance) values('Danika Lawrence', DATE '2004-01-01', 'Student', 1);
insert into Patient(name, birthday, type, insurance) values('Bilal Wood', DATE '2004-02-28', 'Student', 0);
insert into Patient(name, birthday, type) values('Eleanor Burton', DATE '1958-03-19', 'Employee');
insert into Patient(name, birthday, type) values('Vincent Buckley', DATE '1970-10-31', 'Employee');
insert into Patient(name, birthday, type) values('Katey Greenway', DATE '1963-08-22', 'Employee');
insert into Patient(name, birthday, type) values('Nikki Clifford', DATE '1969-05-11', 'Employee');
insert into Patient(name, birthday, type) values('Stephanie Andrew', DATE '2001-06-13', 'Employee');
insert into Patient(name, birthday, type) values('Zahra Allison', DATE '1942-12-25', 'Employee');
insert into Patient(name, birthday, type) values('Martha Boone', DATE '1950-01-15', 'Employee');


GRANT SELECT ON Patient TO PUBLIC;
