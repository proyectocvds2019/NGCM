package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ElementoMapper;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;

public class MyBATISElementoDAO implements ElementoDAO{
	
	@Inject
	private ElementoMapper ElementoMapper;
	
	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo)  throws PersistenceException{
		ElementoMapper.registrarElemento(elemento,correoUsuario,equipo);
		
	}

	@Override
	public List<Elemento> consultarElementos() throws PersistenceException {
		return ElementoMapper.consultarElementos();
	}

	@Override
	public List<Elemento> consultarElementosDisponibles() throws PersistenceException {
		return ElementoMapper.consultarElementosDisponibles();
	}

	@Override
	public Elemento consultarElemento(String id) throws PersistenceException{
		return ElementoMapper.consultarElemento(id);
	}

	@Override
	public List<Elemento> consultarElementosDisponibles(TipoElemento tipo) throws PersistenceException{
		return ElementoMapper.consultarElementosDisponibles2(tipo);
	}

	@Override
	public void actualizarIdEquipo(String idElemento, int idEquipo) throws PersistenceException {
		ElementoMapper.actualizarIdEquipo(idElemento, idEquipo);
		
	}
	
	@Override
	public void desactivarElemento(String id) throws PersistenceException{
			ElementoMapper.desactivarElemento(id);
	}

	@Override
	public Elemento consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo){
		return ElementoMapper.consultarElementoDelEquipo(tipo,equipo);
	}
	

}
