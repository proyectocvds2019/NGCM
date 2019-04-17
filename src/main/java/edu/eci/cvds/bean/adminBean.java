package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
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
	private Integer equipo;
	private TipoElemento tipoSeleccionado;
	private String correo;
    @Inject
    private ServiciosHistorial serviciosHistorial;
	
	public adminBean() {
		Subject subject = SecurityUtils.getSubject();
		this.correo = (String) subject.getSession().getAttribute("correo");
		this.serviciosHistorial = ServiciosHistorialFactory.getInstance().getServiciosHistorial();
		//System.out.println("hola");
	}
    
	public void registrar() throws ExcepcionServiciosHistorial {
		Elemento elemento = new Elemento(1,tipoSeleccionado);
		try {
			serviciosHistorial.registrarElemento(elemento,this.correo,this.equipo);
			this.mensajeCorrecto();
		}catch(Exception e) {
			e.printStackTrace();
			this.mensajeError();
		}
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
	
	public void setTipoSeleccionado(TipoElemento s) {
		this.tipoSeleccionado = s;
	}
	
	public TipoElemento getTipoSeleccionado() {
		return this.tipoSeleccionado;
	}
	
	public List<Elemento> consultarElementos(){
		try {
			return this.serviciosHistorial.consultarElementos();
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Elemento> consultarElementosDisponibles(){
		try {
			return this.serviciosHistorial.consultarElementosDisponibles();
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		}
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
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		}
	}
	public Integer getEquipo() {
		return this.equipo;
	}
	public void setEquipo(Integer e) {
		if(e == -1) {
			e = null;
		}
		this.equipo = e;
	}
	

	
}
