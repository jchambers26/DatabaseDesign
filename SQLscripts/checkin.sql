drop table CheckIn;

create table CheckIn(
apptID integer,
day date,
urgent integer,
PRIMARY KEY(apptID), 
FOREIGN KEY(apptID) REFERENCES Appointment(apptID)
);

insert into CheckIn(apptID, day, urgent) values(1, date '2022-04-23', 0);
insert into CheckIn(apptID, day, urgent) values(2, date '2022-04-15', 1);
insert into CheckIn(apptID, day, urgent) values(4, date '2022-03-15', 0);
insert into CheckIn(apptID, day, urgent) values(5, date '2022-03-23', 1);
insert into CheckIn(apptID, day, urgent) values(6, date '2021-12-23', 0);
insert into CheckIn(apptID, day, urgent) values(8, date '2022-03-15', 0);
insert into CheckIn(apptID, day, urgent) values(9, date '2022-04-04', 0);
insert into CheckIn(apptID, day, urgent) values(10, date '2022-04-08', 0);
insert into CheckIn(apptID, day, urgent) values(11, date '2022-04-21', 0);
insert into CheckIn(apptID, day, urgent) values(13, date '2022-04-23', 1);
insert into CheckIn(apptID, day, urgent) values(15, date '2022-04-25', 0);
insert into CheckIn(apptID, day, urgent) values(18, date '2022-04-01', 0);
insert into CheckIn(apptID, day, urgent) values(19, date '2022-04-09', 0);
insert into CheckIn(apptID, day, urgent) values(20, date '2022-04-11', 0);
