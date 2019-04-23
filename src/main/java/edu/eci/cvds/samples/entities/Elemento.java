package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7381594049177090273L;
	private Integer id;
	private TipoElemento tipo;
	private Equipo equipoAsignado;

	
	public Elemento() {

	}

	public Elemento(Integer id,TipoElemento tipo){
		setId(id);
		setTipo(tipo);
	}

	public Elemento(Integer id, TipoElemento tipo, Equipo equipoAsignado){
		setId(id);
		setTipo(tipo);
		setEquipoAsignado(equipoAsignado);
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

	public void setEquipoAsignado(Equipo equipoAsignado){
		this.equipoAsignado = equipoAsignado;
	}
	public Equipo getEquipoAsignado(){
		return this.equipoAsignado;
	}
	

}