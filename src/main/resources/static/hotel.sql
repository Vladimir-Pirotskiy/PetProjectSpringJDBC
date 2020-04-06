create table visitor (
                         id uuid constraint visitor_pk primary key,
                         first_name varchar,
                         last_name varchar,
                         email varchar,
                         password varchar
);

create table services (
                          id uuid constraint services_pl primary key ,
                          three_b boolean,
                          ski boolean,
                          ski_pass boolean
);

create table room (
                      id uuid constraint room_pk primary key ,
                      type varchar(100),
                      count int,
                      cost decimal(5,2),
                      services_id uuid constraint room_services_fk references services(id)
);

create table order_hotel (
                             id uuid constraint  order_hotel_pk primary key ,
                             dates_count int,
                             visitor_id uuid constraint order_hotel_visitor_fk references visitor(id),
                             room_id uuid constraint order_hotel_room_fk references room(id),
                             services_id uuid constraint order_hotel_services_fk references services(id)
);

alter table visitor add constraint email_unique unique(email);

create extension if not exists "uuid-ossp";
select * from uuid_generate_v1();

insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), true, true, true);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), true, true, false);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), true, false, true);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), true, false, false);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), false, true, true);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), false, true, false);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), false, false, true);
insert into services (id, three_b, ski, ski_pass) values(uuid_generate_v1(), false, false, false);

insert into room(id, type, count, cost, services_id) values(uuid_generate_v1(), 'single', 5, 250.00, '34cf4bb7-5cac-11ea-a5ba-020000e1fef0');
insert into room(id, type, count, cost, services_id) values(uuid_generate_v1(), 'double', 3, 500.00, '323762a9-5cac-11ea-a5ba-020000e1fef0');
insert into room(id, type, count, cost, services_id) values(uuid_generate_v1(), 'double lux', 2, 999.00, 'dc84a4ce-5cab-11ea-a5ba-020000e1fef0');

select * from room;
select * from services;
select * from visitor;
select * from order_hotel;
