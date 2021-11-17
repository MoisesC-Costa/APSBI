create database db_ong;
use db_ong;

create table usuario (
id int8 primary key auto_increment,
nome varchar(256),
email varchar(256) unique,
userpass varchar(256)); 

create table localidade(
estado varchar(256),
longitude double,
latitude double,
primary key(longitude, latitude));

create table ocorrencia(
id int8 primary key auto_increment,
data_ocorrencia date,
longitude double,
latitude double,
foreign key(longitude, latitude) references localidade(longitude, latitude));

create table consulta(
id_user int8 not null,
id_ocorrencia int8 not null);

alter table consulta
add foreign key(id_user) references usuario(id);

alter table consulta
add foreign key(id_ocorrencia) references ocorrencia(id);




