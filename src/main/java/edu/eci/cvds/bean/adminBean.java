package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	private String equipo;
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
		System.out.println(tipoSeleccionado+" "+this.correo+" "+this.equipo);
		serviciosHistorial.registrarElemento(elemento,this.correo,this.equipo);
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
	
	public List<Equipo> getEquipos(){
		//return this.ServiciosHistorial.consultarEquipos();
		return null;
	}
	public String getEquipo() {
		return this.equipo;
	}
	public void setEquipo(String e) {
		if(e.equals("null")) {
			e = null;
		}
		this.equipo = e;
	}

	
}
