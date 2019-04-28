package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.EquipoMapper;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;

public class MyBATISEquipoDAO implements EquipoDAO{
	
	@Inject
	private EquipoMapper equipoMapper;

	public void registrarEquipo(Equipo equipo, Laboratorio laboratorio) throws PersistenceException {
		equipoMapper.registrarEquipo(equipo, laboratorio);
		
	}

	public Equipo consultarEquipo(int id) throws PersistenceException {
		return equipoMapper.consultarEquipo(id);
	}

	public List<Equipo> consultarEquipos() throws PersistenceException {
		return equipoMapper.consultarEquipos();
	}

	public List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws PersistenceException{
		return equipoMapper.consultarEquiposDisponiblesParaElemento(tipo);
	}

	public Integer consultarEquipoDeElemento(Elemento elemento) throws PersistenceException{
		return equipoMapper.consultarEquipoDeElemento(elemento);
	}

	@Override
	public Integer proximoIdEquipo() throws PersistenceException {
		return equipoMapper.proximoIdEquipo();
	}
	
        @Override
	public void desactivarEquipo(int id) throws PersistenceException{
                equipoMapper.desactivarEquipo(id);
        }

}
