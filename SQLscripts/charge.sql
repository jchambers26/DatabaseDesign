drop table Charge;

create table Charge(
apptID integer,
amount float(5) NOT NULL,
description varchar2(20),
PRIMARY KEY(apptID),
FOREIGN KEY(apptID) REFERENCES Appointment(apptID)
);

insert into Charge(apptID, amount, description) values(1, 15.00, 'CAPS');
insert into Charge(apptID, amount, description) values(2, 30.00, 'CAPS');
insert into Charge(apptID, amount, description) values(4, 20.00, 'Lab/Testing');
insert into Charge(apptID, amount, description) values(5, 15.00, 'Immunization');
insert into Charge(apptID, amount, description) values(6, 50.00, 'General Medicine');
insert into Charge(apptID, amount, description) values(8, 45.00, 'Lab/Testing');
insert into Charge(apptID, amount, description) values(9, 20.00, 'Lab/Testing');
insert into Charge(apptID, amount, description) values(10, 20.00, 'Lab/Testing');
insert into Charge(apptID, amount, description) values(11, 50.00, 'Immunization');
insert into Charge(apptID, amount, description) values(13, 30.00, 'CAPS');
insert into Charge(apptID, amount, description) values(14, 100.00, 'No Show');
insert into Charge(apptID, amount, description) values(15, 100.00, 'General Medicine');
insert into Charge(apptID, amount, description) values(18, 25.00, 'General Medicine');
insert into Charge(apptID, amount, description) values(19, 25.00, 'Genearal Medicine');
insert into Charge(apptID, amount, description) values(23, 100.00, 'No Show');
insert into Charge(apptID, amount, description) values(24, 100.00, 'No Show');

