--insert into usuarios (nombre, correo, contrasena, carnet, rol) values ('Andres Gualdron','gualdronsito@hotmail.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',2128884,'admin');


/*insert into elementos (id,tipo,idequipo,registradopor) values (nextval('id_elemento'),'TECLADO',null,'gualdronsito@hotmail.com');*/
insert into equipos (id) values (nextval('id_equipo'));
select * from equipos;

select * from elementos;