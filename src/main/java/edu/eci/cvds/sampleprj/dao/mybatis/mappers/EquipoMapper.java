package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.Equipo;

/**
 * 
 * @author 2128728
 *
 */

public interface EquipoMapper {
	
	public List<Equipo> consultarEquipos();

}
