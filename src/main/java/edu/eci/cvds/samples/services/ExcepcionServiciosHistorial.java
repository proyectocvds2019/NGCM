package edu.eci.cvds.samples.services;


public class ExcepcionServiciosHistorial extends Exception{
	public ExcepcionServiciosHistorial(String string, Exception ex) {
		ex.printStackTrace();
	}
	
	public ExcepcionServiciosHistorial(String string) {
		
	}
}
