package edu.eci.cvds.samples.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;

@Singleton
public class ServiciosHistorialImpl implements ServiciosHistorial{
	
	@Inject
	private ElementoDAO elementoDAO;
	@Inject
	private EquipoDAO equipoDAO;
	@Inject
	private LaboratorioDAO laboratorioDAO;

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
		try {
			return elementoDAO.consultarElementosDisponibles();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar los elementos disponibles");
		}
	}

	@Override
	public void registrarEquipo(Equipo equipo, Laboratorio laboratorio) throws ExcepcionServiciosHistorial {
		try {
			equipoDAO.registrarEquipo(equipo, laboratorio);
			for(Elemento e: equipo.getElementos()) {
				actualizarIdEquipoEnElemento(e.getId(), equipo.getId());
			}
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo registrar el equipo.");
		}
	}
	
	@Override
	public Equipo consultarEquipo(int id) throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.consultarEquipo(id);
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar el equipo solicitado.");
		}
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
	public  Integer consultarEquipoDeElemento(Elemento elemento) throws  ExcepcionServiciosHistorial{
		try{
			return equipoDAO.consultarEquipoDeElemento(elemento);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el equipo asociado al elemento: "+elemento.getId());
		}
	}

	@Override
	public Elemento consultarElemento(String id) throws ExcepcionServiciosHistorial{
		try{
			return elementoDAO.consultarElemento(id);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el elemento");
		}
	}
	@Override
	public Laboratorio consultarLaboratorio(Integer id) throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarLaboratorio(id);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el laboratorio");
		}
	}

	@Override
	public List<Elemento> consultarElementosDisponibles(TipoElemento tipo) throws ExcepcionServiciosHistorial{
		try{
			return elementoDAO.consultarElementosDisponibles(tipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar los elementos disponibles de tipo: "+tipo);
		}
	}

	@Override
	public void actualizarIdEquipoEnElemento(String idElemento, int idEquipo) throws ExcepcionServiciosHistorial {
		try {
			elementoDAO.actualizarIdEquipo(idElemento,idEquipo);
		}catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo actualizar el idEquipo en el elemento.");
		}
		
	}

	@Override
	public void desactivarElemento(String id) throws ExcepcionServiciosHistorial {
		try {
			elementoDAO.desactivarElemento(id);
		}catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo dar de baja al elemento.");
		}
		
	}

	@Override
	public Integer proximoIdEquipo() throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.proximoIdEquipo();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar el proximo id de equipo.");
		}
	}

	@Override
	public Laboratorio consultarLaboratorio(Equipo equipo) throws ExcepcionServiciosHistorial{
		try {
			return laboratorioDAO.consultarLaboratorio(equipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el laboratorio del equipo: "+equipo.getId());
		}
	}

	@Override
	public Elemento consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo) throws ExcepcionServiciosHistorial{
		try {
			return elementoDAO.consultarElementoDelEquipo(tipo,equipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consulta el elemento de tipo "+tipo+" y equipo "+equipo.getId());
		}
	}



}
