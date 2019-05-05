package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface ElementoDAO {

	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws PersistenceException;
	
	public List<Elemento> consultarElementos() throws PersistenceException;
	
	public List<Elemento> consultarElementosDisponibles() throws PersistenceException;

	public Elemento consultarElemento(String id) throws PersistenceException;

	public List<Elemento> consultarElementosDisponibles(TipoElemento tipo) throws PersistenceException;

	public void actualizarIdEquipo(String idElemento, Integer idEquipo) throws PersistenceException;
	
    public void desactivarElemento(String id) throws PersistenceException;

    public Elemento consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo) throws PersistenceException;

    public void cambiarIDElemento(Elemento elemento, String id) throws PersistenceException;

    public void cambiarNombreElemento(Elemento elemento, String nombre) throws PersistenceException;
}
