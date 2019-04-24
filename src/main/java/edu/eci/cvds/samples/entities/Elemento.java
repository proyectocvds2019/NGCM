package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7381594049177090273L;
	private String id;
	private TipoElemento tipo;
	private Equipo equipoAsignado;
	private String nombre;

	
	public Elemento() {

	}

	public Elemento(String id,TipoElemento tipo, String nombre){
		setId(id);
		setTipo(tipo);
		setNombre(nombre);
	}

	public Elemento(String id, TipoElemento tipo, Equipo equipoAsignado){
		setId(id);
		setTipo(tipo);
		setEquipoAsignado(equipoAsignado);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoElemento getTipo() {
		return tipo;
	}

	public void setTipo(TipoElemento tipo) {
		this.tipo = tipo;
	}

	public void setEquipoAsignado(Equipo equipoAsignado){
		this.equipoAsignado = equipoAsignado;
	}
	public Equipo getEquipoAsignado(){
		return this.equipoAsignado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}