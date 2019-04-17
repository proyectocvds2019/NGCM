package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;

public interface ServiciosHistorial {
	
	public abstract void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws ExcepcionServiciosHistorial;
	
	public abstract List<Elemento> consultarElementos() throws ExcepcionServiciosHistorial;
	
	public abstract List<Elemento> consultarElementosDisponibles() throws ExcepcionServiciosHistorial;

	public abstract List<Equipo> consultarEquipos() throws ExcepcionServiciosHistorial;
	
}
