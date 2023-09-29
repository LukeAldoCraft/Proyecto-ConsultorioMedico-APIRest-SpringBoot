
create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null unique,
    clave varchar(300) not null,

    primary key(id)
);


