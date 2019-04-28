package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.Equipo;
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
	
	public void actualizarIdEquipo(@Param("idEl") String idEl, @Param("idEq") Integer idEq);
	
	public void desactivarElemento(@Param("id") String id);

	public Elemento consultarElementoDelEquipo(@Param("tipoElemento") TipoElemento tipo, @Param("equipo")Equipo equipo);
}
