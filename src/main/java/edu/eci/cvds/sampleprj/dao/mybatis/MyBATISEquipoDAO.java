package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.EquipoMapper;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;

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
		return equipoMapper.consultarEquipos();
	}

	public List<Equipo> consultarEquiposDisponibles(TipoElemento tipo) throws PersistenceException{
		return equipoMapper.consultarEquiposDisponibles(tipo);
	}
	
	

}
