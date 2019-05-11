package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Novedad;

public interface NovedadDAO {
	
	public void registrarNovedad(String titulo, String detalle, String clase, String usuario, Integer idEq,
			String idElemento) throws PersistenceException;

	public List<Novedad> consultarNovedades() throws PersistenceException;
	
	public List<Novedad> consultarNovedadesElemento(Elemento elemento) throws PersistenceException;
	
	public List<Novedad> consultarNovedadesEquipo(Equipo equipo) throws PersistenceException;

}
