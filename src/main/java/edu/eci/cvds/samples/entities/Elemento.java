package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	private int id;
	private TipoElemento tipo;
	
	public Elemento(int id, TipoElemento tipo) {
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