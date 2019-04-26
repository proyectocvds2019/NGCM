package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.TipoElemento;
import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.samples.entities.Elemento;

/**
 * 
 * @author 2128728
 *
 */

public interface ElementoMapper {
	
	public void registrarElemento(@Param("el") Elemento elemento, @Param("correo")String correoUsuario, @Param("equipo")Integer equipo);
	
	public List<Elemento> consultarElementos();
	
	public List<Elemento> consultarElementosDisponibles();

	public Elemento consultarElemento(@Param("id") String id);

	public List<Elemento> consultarElementosDisponibles2(@Param("tipo") TipoElemento tipo);
	

}
