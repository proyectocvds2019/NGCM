package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ElementoMapper;

public class MyBATISElementoDAO implements ElementoDAO{
	
	@Inject
	private ElementoMapper ElementoMapper;
	
	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo)  throws PersistenceException{
		ElementoMapper.registrarElemento(
				elemento,correoUsuario,equipo);
		
	}

	@Override
	public List<Elemento> consultarElementos() throws PersistenceException {
		return ElementoMapper.consultarElementos();
	}

	@Override
	public List<Elemento> consultarElementosDisponibles() throws PersistenceException {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return ElementoMapper.consultarElementosDisponibles();
	}

	@Override
	public Elemento consultarElemento(String id) throws PersistenceException{
		return ElementoMapper.consultarElemento(id);
	}
	
	

}
