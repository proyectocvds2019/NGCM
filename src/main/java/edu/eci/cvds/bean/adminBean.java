package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.tipoElemento;
import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;


@ManagedBean(name = "adminBean")
@ViewScoped
public class adminBean implements Serializable{
	private Elemento elemento;
	private tipoElemento tipoElemento;
	private Equipo equipo;
	private tipoElemento tipoSeleccionado;
    @Inject
    private ServiciosHistorial serviciosHistorial;
	
	
	public void registrar() throws ExcepcionServiciosHistorial {
		elemento = new Elemento(1,tipoElemento);
		serviciosHistorial.registrarElemento(elemento);
	}
	public tipoElemento getTipoElemento() {
		return this.tipoElemento;
	}
	public void setTipo(tipoElemento i) {
		this.tipoElemento = i;
	}
	public tipoElemento[] getTipoElementos() {
		return this.tipoElemento.values();
	}
	
	public void setTipoSeleccionado(tipoElemento s) {
		this.tipoSeleccionado = s;
	}
	
	public tipoElemento getTipoSeleccionado() {
		return this.tipoSeleccionado;
	}
	
	public List<Equipo> getEquipos(){
		//return this.ServiciosHistorial.consultarEquipos();
		return null;
	}
	public Equipo getEquipo() {
		return this.equipo;
	}
	public void setEquipo(Equipo e) {
		this.equipo = e;
	}

	
}
