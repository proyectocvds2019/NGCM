package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;

public interface ElementoDAO {

	public void registrarElemento(Elemento elemento, String correoUsuario, String equipo) throws PersistenceException;
	

}
