package edu.eci.cvds.sampleprj.dao.mybatis;

import org.apache.ibatis.annotations.Param;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.NovedadMapper;

public class MyBATISNovedadDAO implements NovedadDAO{
	
	@Inject
	private NovedadMapper novedadMapper;

	@Override
	public void registrarNovedad(String titulo, String detalle, String clase, String usuario, Integer idEq, String idElemento) {
			novedadMapper.registrarNovedad(titulo, detalle, clase, usuario, idEq, idElemento);
	}

}
