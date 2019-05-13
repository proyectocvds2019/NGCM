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
	@Override
	public void registrarEquipo(Equipo equipo, Laboratorio laboratorio) throws PersistenceException {
		equipoMapper.registrarEquipo(equipo, laboratorio);
		
	}
	@Override
	public Equipo consultarEquipo(Integer id) throws PersistenceException {
		return equipoMapper.consultarEquipo(id);
	}
	@Override
	public List<Equipo> consultarEquipos() throws PersistenceException {
		return equipoMapper.consultarEquipos();
	}
	@Override
	public List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws PersistenceException{
		return equipoMapper.consultarEquiposDisponiblesParaElemento(tipo);
	}
	@Override
	public Integer consultarEquipoDeElemento(Elemento elemento) throws PersistenceException{
		return equipoMapper.consultarEquipoDeElemento(elemento);
	}

	@Override
	public Integer proximoIdEquipo() throws PersistenceException {
		return equipoMapper.proximoIdEquipo();
	}
	
	@Override
	public void desactivarEquipo(Integer id) throws PersistenceException{
                equipoMapper.desactivarEquipo(id);
	}

	@Override
	public List<Equipo> consultarEquiposDisponibles() throws PersistenceException{
		return equipoMapper.consultarEquiposDisponibles();
	}

	@Override
	public void actualizarIdLaboratorio(Integer idLab, Integer idEq) throws PersistenceException {
		equipoMapper.actualizarIdLaboratorio(idLab, idEq);
		
	}

	@Override
	public Equipo consultarEquipoDelLaboratorio(Laboratorio lab) throws PersistenceException{
		return equipoMapper.consultarEquipoDelLaboratorio(lab);
	}

	@Override
	public List<Equipo> consultarEquiposDelLaboratorio(Laboratorio laboratorio){
		return equipoMapper.consultarEquiposDelLaboratorio(laboratorio);
	}

}
