package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3512777696530659507L;

	public PersistenceException(String mensaje, org.apache.ibatis.exceptions.PersistenceException e) {
		
	}

	public PersistenceException(String string) {
		super(string);
	}
	
}