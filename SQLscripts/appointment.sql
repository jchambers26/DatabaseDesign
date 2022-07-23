drop table Appointment;

create table Appointment(
apptID integer,
patientID integer NOT NULL,
careProviderID integer NOT NULL,
begin timestamp(0) NOT NULL,
end timestamp(0) NOT NULL,
isWalkIn integer NOT NULL,
PRIMARY KEY(apptID),
FOREIGN KEY(patientID) references Patient(patientID),
FOREIGN KEY(careProviderID) references CareProvider(careProviderID)
);

drop sequence apptID_seq;
create sequence apptID_seq;

drop trigger apptID_trigger;
create trigger apptID_trigger
before insert on Appointment
for each row
begin
select apptID_seq.nextval
into :new.apptID
from dual; end;
/

insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(1, 1, timestamp '2022-04-23 12:00:00', timestamp '2022-04-23 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(2, 3, timestamp '2022-04-15 8:00:00', timestamp '2022-04-15 9:00:00', 1);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(3, 8, timestamp '2022-04-27 14:00:00', timestamp '2022-04-27 15:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(4, 19, timestamp '2022-03-15 12:00:00', timestamp '2022-03-15 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(5, 13, timestamp '2022-03-23 14:00:00', timestamp '2022-03-23 14:00:00', 1);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(6, 7, timestamp '2021-12-23 7:00:00', timestamp '2022-12-23 8:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(7, 13, timestamp '2022-05-04 12:00:00', timestamp '2022-05-04 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(8, 22, timestamp '2022-03-15 12:00:00', timestamp '2022-03-15 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(9, 24, timestamp '2022-04-04 11:00:00', timestamp '2022-04-04 12:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(10, 19, timestamp '2022-04-08 9:00:00', timestamp '2022-04-08 10:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(11, 17, timestamp '2022-04-21 15:00:00', timestamp '2022-04-21 16:00:00', 1);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(12, 4, timestamp '2022-05-15 17:00:00', timestamp '2022-05-15 18:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(13, 5, timestamp '2022-04-23 8:00:00', timestamp '2022-04-23 9:00:00', 1);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(14, 8, timestamp '2022-04-23 7:00:00', timestamp '2022-04-23 8:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(15, 9, timestamp '2022-04-25 17:00:00', timestamp '2022-04-25 18:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(16, 12, timestamp '2022-04-30 12:00:00', timestamp '2022-04-30 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(17, 13, timestamp '2022-04-29 10:00:00', timestamp '2022-04-29 11:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(18, 10, timestamp '2022-04-01 06:00:00', timestamp '2022-04-01 07:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(19, 11, timestamp '2022-04-09 18:00:00', timestamp '2022-04-09 19:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(20, 18, timestamp '2022-04-11 13:00:00', timestamp '2022-04-11 14:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(21, 21, timestamp '2022-05-10 12:00:00', timestamp '2022-05-10 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(22, 22, timestamp '2022-06-15 08:00:00', timestamp '2022-06-15 09:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(23, 24, timestamp '2022-04-23 18:00:00', timestamp '2022-04-23 19:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(24, 1, timestamp '2022-04-23 15:00:00', timestamp '2022-04-23 16:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(20, 13, timestamp '2022-05-10 14:00:00', timestamp '2022-05-10 15:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(5, 14, timestamp '2021-06-15 12:00:00', timestamp '2021-06-15 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(7, 18, timestamp '2021-08-19 12:00:00', timestamp '2021-08-19 13:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(7, 13, timestamp '2021-05-19 10:00:00', timestamp '2021-05-19 11:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(17, 14, timestamp '2022-01-15 10:00:00', timestamp '2021-01-15 11:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(17, 15, timestamp '2021-10-18 10:00:00', timestamp '2021-10-18 11:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(20, 14, timestamp '2022-02-10 10:00:00', timestamp '2021-02-10 11:00:00', 0);
insert into Appointment(patientID, careProviderID, begin, end, isWalkIn) values(20, 15, timestamp '2021-08-08 10:00:00', timestamp '2021-08-08 11:00:00', 0);


