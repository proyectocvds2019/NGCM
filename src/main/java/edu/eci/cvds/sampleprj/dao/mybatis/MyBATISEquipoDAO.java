package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.EquipoMapper;
import edu.eci.cvds.samples.entities.Equipo;

public class MyBATISEquipoDAO implements EquipoDAO{
	
	@Inject
	private EquipoMapper equipoMapper;

	public void registrarEquipo(Equipo equipo) throws PersistenceException {
		equipoMapper.registrarEquipo(equipo);
		
	}

	public Equipo consultarEquipo(int id) throws PersistenceException {
		return equipoMapper.consultarEquipo(id);
	}

	public List<Equipo> consultarEquipos() throws PersistenceException {
		return equipoMapper.consultarEquipos();
	}

	
	
	

}
