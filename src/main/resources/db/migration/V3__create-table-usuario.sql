create table usuario(
    id bigint not null auto_increment,
    username varchar(100) not null,
    password varchar(100) not null,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    email varchar(100) not null,
    role varchar(50) not null,
    activo tinyint not null,
    primary key(id)
);