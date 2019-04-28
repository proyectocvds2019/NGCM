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
	private boolean activo;

	public Equipo(){

	}
	
	public Equipo(Integer id, ArrayList<Elemento> elementos, boolean activo) {
		this.setId(id);
		this.setElementos(elementos);
		this.setActivo(activo);
	}
	
	public Equipo(Integer id, boolean activo) {
		this.setId(id);
		this.setElementos(new ArrayList<Elemento>());
		this.setActivo(activo);
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

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
