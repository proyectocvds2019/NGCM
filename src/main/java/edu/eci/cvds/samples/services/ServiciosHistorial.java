package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;

public interface ServiciosHistorial {
	
	public abstract void registrarElemento(Elemento elemento, String correoUsuario, String equipo) throws ExcepcionServiciosHistorial;

	public abstract Equipo consultarEquipos() throws ExcepcionServiciosHistorial;
	
}
