/* psql -h localhost -U nael -d onlyweebs */

/*
password : nael
*/

drop table IF EXISTS users CASCADE;
drop table IF EXISTS authorities;

create table users(
    username varchar(50)  not null primary key,
    password varchar(150) not null,
    enabled  boolean not null
);

create table authorities(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);

create unique index ix_auth_username on authorities (username, authority);

insert into users values('nael', '$2a$10$8xm5hu35eAthc.1owFa51.hkE7TeZ9K15BjpLkfsLxQ.LDDCPeOdG', true);
insert into users values('nael2', '$2a$10$8xm5hu35eAthc.1owFa51.hkE7TeZ9K15BjpLkfsLxQ.LDDCPeOdG', true);
insert into users values('romain', '$2a$10$S.U5VmMlqnpZlby7IeP0e.Hva4pCtlB0If1wugV6eLG1k.Qoe2yDm', true);

insert into authorities values('nael', 'root');
insert into authorities values('nael2', 'root');
insert into authorities values('romain', 'root');