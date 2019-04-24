package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Equipo;

public interface EquipoDAO {

	public void registrarEquipo(Equipo equipo) throws PersistenceException;
	
	public Equipo consultarEquipo(int id) throws PersistenceException;
	
	public List<Equipo> consultarEquipos() throws PersistenceException;
}
