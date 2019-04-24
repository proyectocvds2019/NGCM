package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 2128728
 *
 */

public interface EquipoMapper {
	
	public List<Equipo> consultarEquipos();
	
	public Equipo consultarEquipo(int id);
	
	public void registrarEquipo(Equipo equipo);

	public List<Equipo> consultarEquiposDisponibles(@Param("tipo") TipoElemento tipo);

}
