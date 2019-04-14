package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	private int id;
	private tipoElemento tipo;
	
	public Elemento(int id, tipoElemento tipo) {
		this.setId(id);
		this.setTipo(tipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public tipoElemento getTipo() {
		return tipo;
	}

	public void setTipo(tipoElemento tipo) {
		this.tipo = tipo;
	}

}
