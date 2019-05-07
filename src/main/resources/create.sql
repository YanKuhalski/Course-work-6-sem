create table users (
id bigserial primary key,
login varchar(45) not null UNIQUE,
enabled boolean not null default true,
password varchar  not null
);

create table role (
id bigserial primary key,
role varchar(20)not null
);

create table user_role(
 user_id bigserial references users(id) on delete cascade on update cascade not null,
 role_id bigserial references role(id) on delete cascade on update cascade not null
 );

create table  discount (
id bigserial primary key,
value  decimal (3,2) not null UNIQUE
);

create table user_discount(
users_id bigserial not null references users(id) on delete cascade on update cascade ,
discount_id bigserial not null references discount(id) on delete cascade on update cascade
);

create table car (
id bigserial primary key UNIQUE,
driver bigserial not null references users(id) on delete cascade on update cascade ,
brand_name varchar(70) not null,
car_model varchar(70) not null,
car_number varchar(10)not null UNIQUE
);

create table region(
id bigserial primary key,
name varchar  not null UNIQUE,
zone_number int not null
);

create table trip (
id bigserial primary key,
client_id bigserial not null,
driver_id bigserial not null,
car_id bigserial not null,
start_region_id bigserial not null ,
end_region_id bigserial not null,
discount_id bigserial not null,
is_accepted bool default false not null,
is_payed bool default false not null,
is_finished bool default false not null,
foreign key (client_id) references users(id) on delete no action on update cascade,
foreign key (driver_id) references users(id) on delete no action on update cascade,
foreign key (car_id) references car(id) on delete no action on update cascade,
foreign key (start_region_id) references region(id) on delete no action on update cascade,
foreign key (end_region_id) references region(id) on delete no action on update cascade,
foreign key (discount_id) references discount(id) on delete no action on update cascade
);
