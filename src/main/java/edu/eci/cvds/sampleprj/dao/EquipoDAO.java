package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface EquipoDAO {

	public void registrarEquipo(Equipo equipo) throws PersistenceException;
	
	public Equipo consultarEquipo(int id) throws PersistenceException;
	
	public List<Equipo> consultarEquipos() throws PersistenceException;

	public List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws PersistenceException;

	public Equipo consultarEquipoDeElemento(Elemento elemento) throws PersistenceException;
	
	public Integer proximoIdEquipo() throws PersistenceException;
        
        public void desactivarEquipo(int id) throws PersistenceException;
}
