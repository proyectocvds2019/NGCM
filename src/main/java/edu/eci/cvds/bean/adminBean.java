package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "adminBean")
@ViewScoped
public class adminBean implements Serializable{
	private List<String> tipos;
	private String tipoSeleccionado;
	private String tipo;
	
	@PostConstruct
    public void init() {
        tipos = new ArrayList<String>();
        tipos.add("Mouse");
        tipos.add("Teclado");
        tipos.add("Torre");
        tipos.add("Monitor");
    }
	
	public void registrar() {
		
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String i) {
		this.tipo = i;
	}
	public List getTipos() {
		return this.tipos;
	}
	
	public void setTipoSeleccionado(String s) {
		this.tipoSeleccionado = s;
	}
	
	public String getTipoSeleccionado() {
		return this.tipoSeleccionado;
	}
}
