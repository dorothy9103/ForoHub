create table curso(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoriaCurso varchar(255) not null,
    activo tinyint not null,
    primary key(id)
);

