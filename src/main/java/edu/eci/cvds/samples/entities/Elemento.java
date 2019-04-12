package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	private int id;
	private String tipo;
	
	public Elemento(int id, String tipo) {
		this.setId(id);
		this.setTipo(tipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
