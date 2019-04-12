package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosHistorial extends Exception{
	public ExcepcionServiciosHistorial(String string, PersistenceException ex) {
		ex.printStackTrace();
	}
	
	public ExcepcionServiciosHistorial(String string) {
		
	}
}
