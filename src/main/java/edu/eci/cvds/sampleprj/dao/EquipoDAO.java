package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipo;

public interface EquipoDAO {

	public void registrarEquipo(Equipo equipo) throws PersistenceException;
	
	public Equipo cargarEquipo(int id) throws PersistenceException;
}
