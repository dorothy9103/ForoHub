create Table topico(
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje TEXT  not null,
    fechaCreacion timestamp not null,
    ultimaActualizacion timestamp not null,
    estado varchar(100) not null,
    usuario varchar(255) not null,
    curso varchar(255),
    categoriaCurso varchar(255) not null,
    primary key(id)
);