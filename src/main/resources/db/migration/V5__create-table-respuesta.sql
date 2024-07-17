create table respuesta(
    id bigint not null auto_increment,
    username varchar(100) not null,
    mensaje TEXT  not null,
    fechaCreacion timestamp not null,
    ultimaActualizacion timestamp not null,
    solucion bigint not null,
    borrado bigint not null,
    usuario_id varchar(255) not null,
    topico varchar(255) not null,
    topico_id varchar(255) not null,
	primary key(id)
);
