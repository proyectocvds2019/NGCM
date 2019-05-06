package edu.eci.cvds.samples.entities;

import java.util.ArrayList;

public class Laboratorio {

    private Integer id;
    private String nombre;
    private boolean activo;
    private ArrayList<Equipo> equipos;
    
    public Laboratorio(){

    }

    public Laboratorio(Integer id, String nombre, boolean activo){
        this.setActivo(activo);
        this.setId(id);
        this.setNombre(nombre);
        this.setEquipos(new ArrayList<Equipo>());
    }

	public Laboratorio(Integer id, String nombre, boolean activo, ArrayList<Equipo> equipos){
    	this.setActivo(activo);
    	this.setId(id);
    	this.setNombre(nombre);
    	this.setEquipos(equipos);
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}
	
	private void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	
}
