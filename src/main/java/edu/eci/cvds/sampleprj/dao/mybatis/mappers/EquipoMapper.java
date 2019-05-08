package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 2128728
 *
 */

public interface EquipoMapper {
	
	public List<Equipo> consultarEquipos();
	
	public Equipo consultarEquipo(@Param("id") Integer id);
	
	public void registrarEquipo(@Param("equipo") Equipo equipo, @Param("laboratorio") Laboratorio laboratorio);

	public List<Equipo> consultarEquiposDisponiblesParaElemento(@Param("tipo") TipoElemento tipo);

	public  Integer consultarEquipoDeElemento(@Param("elemento") Elemento elemento);
	
	public Integer proximoIdEquipo();
	
	public void desactivarEquipo(@Param("id") Integer id);

	public List<Equipo> consultarEquiposDisponibles();
	
	public void actualizarIdLaboratorio(@Param("idLab") Integer idLab, @Param("idEq") Integer idEq);

	public Equipo consultarEquipoDelLaboratorio(@Param("lab") Laboratorio lab);

}
