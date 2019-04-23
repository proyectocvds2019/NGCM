package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4250247578117025104L;
	private Integer id;
	private ArrayList<Elemento> elementos;

	public Equipo(){

	}
	
	public Equipo(Integer id, ArrayList<Elemento> elementos) {
		this.setId(id);
		this.setElementos(elementos);
	}
	
	public Equipo(Integer id) {
		this.setId(id);
		this.setElementos(new ArrayList<Elemento>());
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Elemento> elementos) {
		this.elementos = elementos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
