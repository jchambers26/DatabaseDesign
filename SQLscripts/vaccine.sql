drop table Vaccine;

create table Vaccine(
apptID integer,
type varchar2(30) NOT NULL,
doseNumber integer,
PRIMARY KEY(apptID),
FOREIGN KEY(apptID) REFERENCES Appointment(apptID)
);


insert into Vaccine values(5, 'COVID-19', 2);
insert into Vaccine values(7, 'COVID-19', 3);
insert into Vaccine(apptID, type) values(11, 'Influenza');
insert into Vaccine values(17, 'COVID-19', 3);
insert into Vaccine values(20, 'COVID-19', 3);
insert into Vaccine values(25, 'COVID-19', 4);
insert into Vaccine values(26, 'COVID-19', 1);
insert into Vaccine values(27, 'COVID-19', 2);
insert into Vaccine values(28, 'COVID-19', 1);
insert into Vaccine values(29, 'COVID-19', 2);
insert into Vaccine values(30, 'COVID-19', 1);
insert into Vaccine values(31, 'COVID-19', 2);
insert into Vaccine values(32, 'COVID-19', 1);
