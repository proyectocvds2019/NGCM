package edu.eci.cvds.samples.services.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;

@Singleton
public class ServiciosHistorialImpl implements ServiciosHistorial{
	
	@Inject
	private ElementoDAO elementoDAO;
	@Inject
	private EquipoDAO equipoDAO; 

	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws ExcepcionServiciosHistorial {
		try {
			elementoDAO.registrarElemento(elemento,correoUsuario,equipo);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo registrar el elemento.");
		}
	}

	@Override
	public List<Equipo> consultarEquipos() throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.consultarEquipos();
		} catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar los equipos.");
		}
	}

	@Override
	public List<Elemento> consultarElementos() throws ExcepcionServiciosHistorial {
		try {
			return elementoDAO.consultarElementos();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("no se pudo consultar los elementos");
		}
	}

	@Override
	public List<Elemento> consultarElementosDisponibles() throws ExcepcionServiciosHistorial {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		try {
			return elementoDAO.consultarElementosDisponibles();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("no se pudo consultar los elementos disponibles");
		}
	}

	@Override
	public void registrarEquipo(Integer laboratorio) throws ExcepcionServiciosHistorial {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws ExcepcionServiciosHistorial{
		try{
			return equipoDAO.consultarEquiposDisponiblesParaElemento(tipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar los equipos disponibles");
		}
	}

	@Override
	public  Equipo consultarEquipoDeElemento(Elemento elemento) throws  ExcepcionServiciosHistorial{
		try{
			return equipoDAO.consultarEquipoDeElemento(elemento);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el equipo asociado al elemento: "+elemento.getId());
		}
	}



}
