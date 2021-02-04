CREATE TABLE hatcher_user (
    id bigint ,
    nome varchar (255),
    email varchar (255) unique,
    login varchar (200),
    admin boolean ,
    PRIMARY KEY (id)

);


CREATE SEQUENCE User_sequence INCREMENT BY 1;