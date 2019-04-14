package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;

public interface ServiciosHistorial {
	
	public abstract void registrarElemento(Elemento elemento) throws ExcepcionServiciosHistorial;
	
	
}
