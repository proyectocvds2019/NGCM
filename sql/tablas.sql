--- Creacion Tablas

CREATE TABLE Usuarios(
	nombre VARCHAR(100) NOT NULL,
	correo VARCHAR(100) PRIMARY KEY NOT NULL,
	contrasena VARCHAR(100) NOT NULL,
	rol VARCHAR(20) NOT NULL,
	carnet INTEGER
);

/*CREATE SEQUENCE id_lab;
CREATE TABLE Laboratorios(
	id INTEGER PRIMARY KEY NOT NULL DEFAULT ,
	nombre VARCHAR(100) NOT NULL
);*/

CREATE SEQUENCE id_equipo;
CREATE TABLE Equipos(
	id INTEGER PRIMARY KEY NOT NULL
	--laboratorio INTEGER NOT NULL
);

CREATE SEQUENCE id_elemento;
CREATE TABLE Elementos(
	id INTEGER PRIMARY KEY NOT NULL,
	tipo VARCHAR(10) NOT NULL,
	idEquipo INTEGER,
	registradoPor VARCHAR(100) NOT NULL
);

--- Foreigns
ALTER TABLE Elementos ADD CONSTRAINT Elementos_Equipo
	FOREIGN KEY (idEquipo)
	REFERENCES Equipos (id);
	
ALTER TABLE Elementos ADD CONSTRAINT Elementos_Usuario
	FOREIGN KEY (registradoPor)
	REFERENCES Usuarios (correo);
	
/*ALTER TABLE Equipos ADD CONSTRAINT Equipo_Laboratorio
	FOREIGN KEY (laboratorio)
	REFERENCES Laboratorios (id);*/

--- Checks

-- Tipo Correo
ALTER TABLE Usuarios ADD CONSTRAINT Correo
		CHECK (Usuarios.correo LIKE '%@%.%');
		
-- Tipo Elementos
/*ALTER TABLE Elementos ADD CONSTRAINT Tipo
		CHECK (Elementos.tipo IN ('PANTALLA') OR Elementos.tipo IN ('TECLADO') OR Elementos.tipo IN ('MOUSE') OR Elementos.tipo IN('TORRE'));*/

-- Roles
/*
ALTER TABLE Usuarios ADD CONSTRAINT Rol
		CHECK (Usuarios.rol IN());
*/
--El administrador para las pruebas
insert into usuarios (nombre, correo, contrasena, carnet, rol) values ('Andres Gualdron','gualdronsito@hotmail.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',2128884,'admin');