create table topicos (

    id bigint not null auto_increment,
    titulo varchar(250) not null,
    mensaje varchar(500) not null unique,
    data datetime not null,
    status smallint,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key (id)
);