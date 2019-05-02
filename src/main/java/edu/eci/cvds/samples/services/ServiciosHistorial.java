package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface ServiciosHistorial {
	
	public abstract void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws ExcepcionServiciosHistorial;
	
	public abstract List<Elemento> consultarElementos() throws ExcepcionServiciosHistorial;
	
	public abstract List<Elemento> consultarElementosDisponibles() throws ExcepcionServiciosHistorial;

	public abstract List<Equipo> consultarEquipos() throws ExcepcionServiciosHistorial;
	
	public abstract Equipo consultarEquipo(int id) throws ExcepcionServiciosHistorial;

	public abstract List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws ExcepcionServiciosHistorial;
	
	public abstract void registrarEquipo(Equipo equipo, Laboratorio laboratorio) throws ExcepcionServiciosHistorial;

	public abstract Integer consultarEquipoDeElemento(Elemento elemento) throws ExcepcionServiciosHistorial;

	public abstract Elemento consultarElemento(String id) throws ExcepcionServiciosHistorial;

	public abstract Laboratorio consultarLaboratorio(Integer id) throws ExcepcionServiciosHistorial;

	public abstract List<Elemento> consultarElementosDisponibles(TipoElemento tipo) throws  ExcepcionServiciosHistorial;
	
	public abstract void actualizarIdEquipoEnElemento(String idElemento, Integer idEquipo) throws ExcepcionServiciosHistorial;
	
	public abstract void desactivarElemento(String id) throws ExcepcionServiciosHistorial;
	
	public abstract Integer proximoIdEquipo() throws ExcepcionServiciosHistorial;

	public abstract Laboratorio consultarLaboratorio(Equipo equipo) throws ExcepcionServiciosHistorial;

	public abstract  Elemento consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo) throws ExcepcionServiciosHistorial;

	public abstract void exportarExcelEquipos() throws ExcepcionServiciosHistorial;
	
	public abstract void desactivarEquipo(int id) throws ExcepcionServiciosHistorial;

	public abstract void exportarElementos() throws ExcepcionServiciosHistorial;

	public abstract List<Equipo> consultarEquiposDisponibles() throws ExcepcionServiciosHistorial;
}
