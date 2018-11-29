--Mysql Script
create database gpadb;

create user 'gpauser'@'localhost' identified by 'Welcome#1'

grant all on gpadb.* to 'gpauser'@'localhost';

create table gpadb.ofdi_execution_control (
       id varchar(255) not null,
        execution_status varchar(255),
        file_name varchar(255),
        file_path varchar(255),
        last_update datetime,
        step integer,
        username varchar(255),
        primary key (id)
    )
