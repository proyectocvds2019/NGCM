package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosHistorial extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7177351675360331415L;

	public ExcepcionServiciosHistorial(String string, PersistenceException ex) {
		ex.printStackTrace();
	}
	
	public ExcepcionServiciosHistorial(String string) {
		
	}
}
