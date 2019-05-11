package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Novedad;

public interface NovedadDAO {
	
	public void registrarNovedad(String titulo, String detalle, String clase, String usuario, Integer idEq,
			String idElemento);

}
