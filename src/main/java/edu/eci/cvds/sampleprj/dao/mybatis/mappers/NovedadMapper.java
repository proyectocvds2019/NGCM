package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

public interface NovedadMapper {
	
	public void registrarNovedad(@Param("titulo")String titulo, @Param("detalle")String detalle, @Param("clase")String clase, @Param("usuario")String usuario, @Param("idEq")Integer idEq, @Param("idElem")String idElemento);
}
