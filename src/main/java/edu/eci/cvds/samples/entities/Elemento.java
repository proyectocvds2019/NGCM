package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7381594049177090273L;
	private String id;
	private TipoElemento tipo;
	private String nombre;



	private boolean activo;

	
	public Elemento() {

	}

	public Elemento(String id,TipoElemento tipo, String nombre, boolean activo){
		setId(id);
		setTipo(tipo);
		setNombre(nombre);
		setActivo(activo);
	}

	public Elemento(String id, TipoElemento tipo){
		setId(id);
		setTipo(tipo);
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	

}