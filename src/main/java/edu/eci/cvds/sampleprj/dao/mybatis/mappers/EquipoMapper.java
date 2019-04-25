package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
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

	public List<Equipo> consultarEquiposDisponiblesParaElemento(@Param("tipo") TipoElemento tipo);

	public  Equipo consultarEquipoDeElemento(@Param("elemento") Elemento elemento);

}
