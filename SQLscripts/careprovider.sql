drop table CareProvider;

create table CareProvider(
careProviderID integer,
name varchar2(50) NOT NULL,
providedServiceType varchar2(20),
PRIMARY KEY(careProviderID),
FOREIGN KEY(providedServiceType) REFERENCES Service(serviceType)
);

drop sequence careProviderID_seq;
create sequence careProviderID_seq;

drop trigger careProviderID_trigger;
create trigger careProviderID_trigger
before insert on CareProvider
for each row
begin
select careProviderID_seq.nextval
into :new.careProviderID
from dual; end;
/

insert into CareProvider(name, providedServiceType) values('Michael Benson', 'CAPS');
insert into CareProvider(name, providedServiceType) values('Marilyn Riggs', 'CAPS');
insert into CareProvider(name, providedServiceType) values('Ethan Mack', 'CAPS');
insert into CareProvider(name, providedServiceType) values('Marcus Lane', 'CAPS');
insert into CareProvider(name, providedServiceType) values('Johnnie Monaghan', 'CAPS');
insert into CareProvider(name, providedServiceType) values('Justine Bloggs', 'CAPS');
insert into CareProvider(name, providedServiceType) values('Edmund Steele', 'General Medicine');
insert into CareProvider(name, providedServiceType) values('Ellie-May Carson', 'General Medicine');
insert into CareProvider(name, providedServiceType) values('Jordyn Russo', 'General Medicine');
insert into CareProvider(name, providedServiceType) values('Rami Osborne', 'General Medicine');
insert into CareProvider(name, providedServiceType) values('Melanie Douglad', 'General Medicine');
insert into CareProvider(name, providedServiceType) values('Jedd Park', 'General Medicine');
insert into CareProvider(name, providedServiceType) values('Kieron Hartman', 'Immunization');
insert into CareProvider(name, providedServiceType) values('Keaton Pena', 'Immunization');
insert into CareProvider(name, providedServiceType) values('Debbie Houghton', 'Immunization');
insert into CareProvider(name, providedServiceType) values('Rees Ferreira', 'Immunization');
insert into CareProvider(name, providedServiceType) values('Claire Salt', 'Immunization');
insert into CareProvider(name, providedServiceType) values('Rachel Iles', 'Immunization');
insert into CareProvider(providedServiceType, name) values('Lab/Testing', 'Briana Rosario');
insert into CareProvider(providedServiceType, name) values('Lab/Testing', 'Rafael Howe');
insert into CareProvider(providedServiceType, name) values('Lab/Testing', 'Asim Hassan');
insert into CareProvider(providedServiceType, name) values('Lab/Testing', 'Wayne Washington');
insert into CareProvider(providedServiceType, name) values('Lab/Testing', 'Ian Valentine');
insert into CareProvider(providedServiceType, name) values('Lab/Testing', 'Darryl Weir');

GRANT SELECT on CareProvider to PUBLIC;
