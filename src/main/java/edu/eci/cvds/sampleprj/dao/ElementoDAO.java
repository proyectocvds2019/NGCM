package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface ElementoDAO {

	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws PersistenceException;
	
	public List<Elemento> consultarElementos() throws PersistenceException;
	
	public List<Elemento> consultarElementosDisponibles() throws PersistenceException;

	public Elemento consultarElemento(String id) throws PersistenceException;

	public List<Elemento> consultarElementosDisponibles(TipoElemento tipo) throws PersistenceException;
	

}
