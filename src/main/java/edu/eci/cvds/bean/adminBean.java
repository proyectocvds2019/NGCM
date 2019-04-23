package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorialFactory;


@ManagedBean(name = "adminBean")
@ViewScoped
public class adminBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6362614236013128488L;
	private Integer equipo;
	private TipoElemento tipoSeleccionado;
	private String correo;
	private Integer laboratorioSeleccionado;
	private Integer monitorSeleccionado;
	private Integer mouseSeleccionado;
	private Integer torreSeleccionada;
	private Integer tecladoSeleccionado;
    @Inject
    private ServiciosHistorial serviciosHistorial;
	
	public adminBean() {
		Subject subject = SecurityUtils.getSubject();
		this.correo = (String) subject.getSession().getAttribute("correo");
		this.serviciosHistorial = ServiciosHistorialFactory.getInstance().getServiciosHistorial();
		//System.out.println("hola");
	}
    
	public void registrarElemento() throws ExcepcionServiciosHistorial {
		Elemento elemento = new Elemento(1,tipoSeleccionado);
		try {
			serviciosHistorial.registrarElemento(elemento,this.correo,this.equipo);
			this.mensajeCorrecto();
		}catch(Exception e) {
			e.printStackTrace();
			this.mensajeError();
		}
	}
	
	public List<Elemento> consultarElementos(){
		try {
			return this.serviciosHistorial.consultarElementos();
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Elemento> consultarElementosDisponibles(){
		try {
			return this.serviciosHistorial.consultarElementosDisponibles();
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
			return null;
		}
	}
	
	public void registrarEquipo() {
		
	}

	public void enlazarElemento(Elemento elemento){

	}
	
	
	public void mensajeCorrecto() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Elemento registrado satisfactoriamente", "Elemento registrado satisfactoriamente"));
	}
	
	public void mensajeError() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! No se pudo registrar el elemento", "No se pudo registrar el elemento"));
	}

	public TipoElemento[] getTipoElementos() {
		return TipoElemento.values();
	}
	
	public TipoElemento getTipoSeleccionado() {
		return this.tipoSeleccionado;
	}
	
	public List<SelectItem> getEquipos(){
		List<SelectItem> lis = new ArrayList<SelectItem>();
		try {
			List<Equipo> l = this.serviciosHistorial.consultarEquipos();
			for(Equipo e: l) {
				lis.add(new SelectItem(e.getId()));
			}
			return lis;
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
			return null;
		}
	}

	public List<SelectItem> getEquiposDisponibles(){
		return null;
	}

	
	public List<SelectItem> laboratoriosDisponibles(){
		return null;
	}
	
	public List<SelectItem> monitoresDisponibles(){
		return null;
	}
	
	public List<SelectItem> mousesDisponibles(){
		return null;
	}
	
	public List<SelectItem> torresDisponibles(){
		return null;
	}
	
	public List<SelectItem> tecladosDisponibles(){
		return null;
	}
	
	
	
	
	//GETS Y SETS
	
	
	
	
	
	public Integer getEquipo() {
		return this.equipo;
	}
	public void setEquipo(Integer equipo) {
		if(equipo == -1) {
			equipo = null;
		}
		this.equipo = equipo;
	}
	
	public Integer getLaboratorioSeleccionado() {
		return this.laboratorioSeleccionado;
	}
	
	public void setTipoSeleccionado(TipoElemento s) {
		this.tipoSeleccionado = s;
	}
	
	public void setLaboratorioSeleccionado(Integer laboratorio) {
		this.laboratorioSeleccionado = laboratorio;
	}
	
	public void setMonitorSeleccionado(Integer monitor) {
		this.monitorSeleccionado = monitor;
	}
	
	public Integer getMonitorSeleccionado() {
		return this.monitorSeleccionado;
	}
	
	public Integer getMouseSeleccionado() {
		return this.mouseSeleccionado;
	}
	
	public void setMouseSeleccionado(Integer mouse) {
		this.mouseSeleccionado = mouse;
	}
	
	public void setTorreSeleccionada(Integer torre) {
		this.torreSeleccionada = torre;
	}
	public Integer getTorreSeleccionada() {
		return this.torreSeleccionada;
	}
	
	public Integer getTecladoSeleccionado() {
		return this.tecladoSeleccionado;
	}
	
	public void setTecladoSeleccionado(Integer teclado) {
		this.tecladoSeleccionado = teclado;
	}
	

	
}
