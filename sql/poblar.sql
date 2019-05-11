--insert into usuarios (nombre, correo, contrasena, carnet, rol) values ('Andres Gualdron','gualdronsito@hotmail.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',2128884,'admin');


/*insert into elementos (id,tipo,idequipo,registradopor) values (nextval('id_elemento'),'TECLADO',null,'gualdronsito@hotmail.com');*/
insert into equipos (id) values (nextval('id_equipo'));
select * from equipos;

select * from elementos;

SELECT novedades.id,
		novedades.titulo, 
		novedades.detalle, 
		novedades.idUsuario, 
		novedades.clase,
		novedades.fechaRegistro,


		elementos.id as idElemento,
		elementos.tipo as tipoElemento,
		elementos.nombre as nombreElemento,
		elementos.activo as activoElemento,
		elementos.fechaRegistro as ff,
		
		equipos.id as idEquipo,
		equipos.activo as activoEquipo,
		equipos.fechaRegistro as fff
FROM Novedades, elementos, equipos
WHERE (Novedades.idEquipo = equipos.id AND
		Novedades.idElemento = elementos.id)
		OR
		(novedades.idelemento is null and novedades.idequipo is not null)
		or
		(novedades.idelemento is not null and novedades.idequipo is null)
ORDER BY fechaRegistro;

UPDATE Elementos SET idequipo = null   WHERE Elementos.id = 'monitor'  AND Elementos.idEquipo = 2;

/*update equipos set activo = true;*/

delete from elementos where id = 'moni';

select *
from equipos, elementos
where equipos.id = elementos.idequipo;

select equipos.id from equipos where equipos.id not in (select elementos.idequipo from elementos where elementos.tipo = 'MONITOR' and elementos.idequipo is not null group by elementos.idequipo);
select * from equipos,elementos where equipos.id = elementos.idequipo;
select elementos.idequipo from elementos where elementos.tipo = 'TORRE' and elementos.idequipo is not null group by elementos.idequipo;
select equipos.id, elementos.id as idE from equipos,elementos where(elementos.idequipo is null and elementos.tipo = 'MONITOR') group by equipos.id,elementos.id;


SELECT equipos.id, equipos.activo, equipos.laboratorio, laboratorios.nombre as laboratorioNombre,
		laboratorios.activo as laboratorioActivo
		FROM Equipos, laboratorios
		WHERE (equipos.id = (select elementos.idEquipo from elementos where elementos.id = 'asdf')) AND
		equipos.laboratorio = laboratorios.id;