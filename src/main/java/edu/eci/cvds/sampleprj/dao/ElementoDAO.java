package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Elemento;

public interface ElementoDAO {

	public void registrarElemento(Elemento elemento) throws PersistenceException;

}
