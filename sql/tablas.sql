--- Creacion Tablas

CREATE TABLE Usuarios(
	nombre VARCHAR(100) NOT NULL,
	correo VARCHAR(100) PRIMARY KEY NOT NULL,
	contrasena VARCHAR(100) NOT NULL,
	rol VARCHAR(100) NOT NULL,
	carnet INTEGER
);

CREATE SEQUENCE id_lab;
CREATE TABLE Laboratorios(
	id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('id_lab'),
	nombre VARCHAR(100) NOT NULL
);

CREATE SEQUENCE id_equipo;
CREATE TABLE Equipos(
	id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('id_equipo'),
	laboratorio INTEGER NOT NULL
);

CREATE SEQUENCE id_elemento;
CREATE TABLE Elementos(
	id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('id_elemento'),
	tipo VARCHAR(100) NOT NULL,
	idEquipo INTEGER NOT NULL,
	registradoPor VARCHAR(100) NOT NULL
);

--- Foreigns
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

-- Roles
/*
ALTER TABLE Usuarios ADD CONSTRAINT Rol
		CHECK (Usuarios.rol IN());
*/