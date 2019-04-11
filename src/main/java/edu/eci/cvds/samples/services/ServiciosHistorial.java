package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Elemento;

public interface ServiciosHistorial {
	public abstract void registrarElemento(Elemento elemento) throws ExcepcionServiciosHistorial;
}
