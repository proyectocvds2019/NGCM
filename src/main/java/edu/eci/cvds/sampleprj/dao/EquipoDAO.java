package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface EquipoDAO {

	public void registrarEquipo(Equipo equipo) throws PersistenceException;
	
	public Equipo cargarEquipo(int id) throws PersistenceException;
	
	public List<Equipo> consultarEquipos() throws PersistenceException;

	public List<Equipo> consultarEquiposDisponibles(TipoElemento tipo) throws PersistenceException;
}
