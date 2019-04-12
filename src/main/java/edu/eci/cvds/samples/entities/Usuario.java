package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{
	private String nombre;
	private String correo;
	private String contraseña;
	private int id;
	private String rol;
	private ArrayList<Elemento> elementos;
	
	public Usuario(int id, String nombre, String correo, String contraseña, String rol, ArrayList<Elemento> elementos) {
		this.setId(id);
		this.setNombre(nombre);
		this.setCorreo(correo);
		this.setContraseña(contraseña);
		this.setRol(rol);
		this.setElementos(elementos);
	}
	
	public Usuario(int id, String nombre, String correo, String contraseña, String rol) {
		this.setId(id);
		this.setNombre(nombre);
		this.setCorreo(correo);
		this.setContraseña(contraseña);
		this.setRol(rol);
		this.setElementos(new ArrayList<Elemento>());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Elemento> elementos) {
		this.elementos = elementos;
	}
	
	
}
