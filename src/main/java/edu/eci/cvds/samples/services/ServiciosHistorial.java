package edu.eci.cvds.samples.services;

public interface ServiciosHistorial {
	public abstract void registrarElemento(int id,String tipo, int idEquipo) throws ExcepcionServiciosHistorial;
}
