package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.EquipoMapper;
import edu.eci.cvds.samples.entities.Equipo;

public class MyBATISEquipoDAO implements EquipoDAO{
	
	@Inject
	private EquipoMapper equipoMapper;

	public void registrarEquipo(Equipo equipo) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	public Equipo cargarEquipo(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Equipo> consultarEquipos() throws PersistenceException {
		equipoMapper.consultarEquipos();
	}
	
	

}
