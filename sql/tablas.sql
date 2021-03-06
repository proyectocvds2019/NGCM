--- Creacion Tablas

CREATE TABLE Usuarios(
	nombre VARCHAR(100) NOT NULL,
	correo VARCHAR(100) PRIMARY KEY NOT NULL,
	contrasena VARCHAR(100) NOT NULL,
	rol VARCHAR(20) NOT NULL,
	carnet INTEGER,
	fechaRegistro DATE DEFAULT CURRENT_DATE,
	fechaEliminacion DATE
);

CREATE SEQUENCE id_lab;
CREATE TABLE Laboratorios(
id INTEGER PRIMARY KEY NOT NULL,
nombre VARCHAR(100) NOT NULL,
activo BOOLEAN NOT NULL,
fechaRegistro DATE DEFAULT CURRENT_DATE,
fechaEliminacion DATE
);

CREATE SEQUENCE id_equipo;
CREATE TABLE Equipos(
	id INTEGER PRIMARY KEY NOT NULL,
	activo boolean NOT NULL,
	laboratorio INTEGER,
	fechaRegistro DATE DEFAULT CURRENT_DATE,
	fechaEliminacion DATE
);

CREATE TABLE Elementos(
	id VARCHAR(100) PRIMARY KEY NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	tipo VARCHAR(10) NOT NULL,
	activo boolean NOT NULL,
	idEquipo INTEGER,
	registradoPor VARCHAR(100) NOT NULL,
	fechaRegistro DATE DEFAULT CURRENT_DATE,
	fechaEliminacion DATE
);

CREATE SEQUENCE id_novedad;
create table Novedades(
	id INTEGER primary key not null,
	fechaRegistro DATE default CURRENT_DATE,
	titulo VARCHAR(100) not null,
	detalle VARCHAR(200) not null,
	clase VARCHAR(100) not null,
	idElemento VARCHAR(100),
	idUsuario VARCHAR(100) not null,
	idEquipo INTEGER
);

--- Foreigns
alter table Novedades add constraint fk_novedadEquipo
	foreign key(idEquipo)
	references Equipos(id);

alter table Novedades add constraint fk_novedadUsuario
	foreign key(idUsuario)
	references usuarios(correo);

alter table Novedades add constraint fk_novedadElemento
	foreign key(idElemento)
	references Elementos(id);

ALTER TABLE Elementos ADD CONSTRAINT Elementos_Equipo
	FOREIGN KEY (idEquipo)
	REFERENCES Equipos (id);
	
ALTER TABLE Elementos ADD CONSTRAINT Elementos_Usuario
	FOREIGN KEY (registradoPor)
	REFERENCES Usuarios (correo);
	
ALTER TABLE Equipos ADD CONSTRAINT Equipo_Laboratorio
	FOREIGN KEY (laboratorio)
	REFERENCES Laboratorios (id);


--- Checks

-- Tipo Correo
ALTER TABLE Usuarios ADD CONSTRAINT Correo
		CHECK (Usuarios.correo LIKE '%@%.%');
		
-- Tipo Elementos
ALTER TABLE Elementos ADD CONSTRAINT Tipo
		CHECK (Elementos.tipo IN ('PANTALLA') OR Elementos.tipo IN ('TECLADO') OR Elementos.tipo IN ('MOUSE') OR Elementos.tipo IN('TORRE') OR elementos.tipo IN('MONITOR') );

-- Roles
/*
ALTER TABLE Usuarios ADD CONSTRAINT Rol
		CHECK (Usuarios.rol IN());
*/
--El administrador para las pruebas
insert into usuarios (nombre, correo, contrasena, carnet, rol) values ('Andres Gualdron','gualdronsito@hotmail.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',2128884,'admin');
--El equipo para las pruebas
insert into laboratorios (id,nombre,activo) values (nextval('id_lab'),'laboratorio de redes',true);
--El elemento para pruebas

insert into equipos (id,activo,laboratorio) values (nextval('id_equipo'),true,null);

insert into elementos (id,nombre,activo,tipo,registradoPor,idequipo) values ('teclado1','teclado1',true,'TECLADO','gualdronsito@hotmail.com',1);
insert into elementos (id,nombre,activo,tipo,registradoPor,idequipo) values ('mouse1','mouse1',true,'MOUSE','gualdronsito@hotmail.com',1);
insert into elementos (id,nombre,activo,tipo,registradoPor,idequipo) values ('monitor1','monitor1',true,'MONITOR','gualdronsito@hotmail.com',1);
insert into elementos (id,nombre,activo,tipo,registradoPor,idequipo) values ('torre1','torre1',true,'TORRE','gualdronsito@hotmail.com',1);

--La novedad para pruebas
--insert into Novedades (id,titulo,detalle,clase,idElemento,idEquipo,idUsuario) values (nextval('id_novedad'),'titulo','detalle','novedadEliminar','ttt',null,'gualdronsito@hotmail.com');