package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7381594049177090273L;
	private Integer id;
	private TipoElemento tipo;
	
	public Elemento() {
		
	}
	
	public Elemento(Integer id, TipoElemento tipo) {
		this.setId(id);
		this.setTipo(tipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoElemento getTipo() {
		return tipo;
	}

	public void setTipo(TipoElemento tipo) {
		this.tipo = tipo;
	}
	
	

}