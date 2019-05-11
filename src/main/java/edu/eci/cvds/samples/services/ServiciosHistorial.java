package edu.eci.cvds.samples.services;

import java.util.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface ServiciosHistorial {
	
	public abstract void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws ExcepcionServiciosHistorial;
	
	public abstract List<Elemento> consultarElementos() throws ExcepcionServiciosHistorial;
	
	public abstract List<Elemento> consultarElementosDisponibles() throws ExcepcionServiciosHistorial;

	public abstract List<Equipo> consultarEquipos() throws ExcepcionServiciosHistorial;
	
	public abstract Equipo consultarEquipo(Integer id) throws ExcepcionServiciosHistorial;

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
	
	public abstract void desactivarEquipo(Integer id) throws ExcepcionServiciosHistorial;

	public abstract void exportarElementos() throws ExcepcionServiciosHistorial;

	public abstract List<Equipo> consultarEquiposDisponibles() throws ExcepcionServiciosHistorial;

	public abstract void cambiarIDElemento(Elemento elemento, String id) throws ExcepcionServiciosHistorial;

	public abstract void cambiarNombreElemento(Elemento elemento, String nombre) throws ExcepcionServiciosHistorial;
	
	public abstract void registrarLaboratorio(Laboratorio lab) throws ExcepcionServiciosHistorial;

	public abstract Equipo consultarEquipoDelLaboratorio(Laboratorio lab) throws ExcepcionServiciosHistorial;

	public abstract List<Laboratorio> consultarLaboratoriosDisponibles() throws ExcepcionServiciosHistorial;

	public abstract void actualizarIdLaboratorioEnEquipo(Integer idEquipo, Integer idLab) throws ExcepcionServiciosHistorial;

	public abstract Integer consultarSiguienteIdLaboratorio() throws ExcepcionServiciosHistorial;

	public abstract List<Laboratorio> consultarLaboratorios() throws ExcepcionServiciosHistorial;

	public abstract Integer consultarNumeroEquipos(Laboratorio laboratorio) throws ExcepcionServiciosHistorial;

	public abstract Date consultarFechaRegistro(Laboratorio laboratorio) throws ExcepcionServiciosHistorial;

	public abstract void eliminarLaboratorio(Laboratorio laboratorio) throws ExcepcionServiciosHistorial;

	public abstract void desasociarLaboratorioDeEquipos(Laboratorio laboratorio) throws ExcepcionServiciosHistorial;

	public abstract void importarTablaLaboratorios() throws ExcepcionServiciosHistorial;

	public abstract Integer consultarEquiposEliminadosLaboratorio(Laboratorio laboratorio) throws ExcepcionServiciosHistorial;

	public abstract void registrarNovedad(String titulo, String detalle, String clase, String usuario, Integer idEquipo, String idElemento) throws ExcepcionServiciosHistorial;

	public abstract List<Novedad> consultarNovedades() throws ExcepcionServiciosHistorial;

	public abstract void importarTablaNovedades() throws ExcepcionServiciosHistorial;

}
