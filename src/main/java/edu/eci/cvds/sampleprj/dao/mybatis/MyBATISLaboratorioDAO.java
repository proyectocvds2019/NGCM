package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratorioMapper;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;

import javax.inject.Inject;
import java.util.List;

public class MyBATISLaboratorioDAO implements LaboratorioDAO {
    @Inject
    private LaboratorioMapper laboratorioMapper;

    public Laboratorio consultarLaboratorio(Integer id) throws PersistenceException{
        return laboratorioMapper.consultarLaboratorio(id);
    }

    public Laboratorio consultarLaboratorio(Equipo equipo) throws PersistenceException{
        return laboratorioMapper.consultarLaboratorio2(equipo);
    }

	@Override
	public void registrarLaboratorio(Laboratorio lab) throws PersistenceException {
		laboratorioMapper.registrarLaboratorio(lab);
		
	}

	@Override
    public List<Laboratorio> consultarLaboratoriosDisponibles() throws PersistenceException{
        return laboratorioMapper.consultarLaboratoriosDisponibles();
    }

    @Override
    public Integer consultarSiguienteIdLaboratorio() throws PersistenceException{
        return laboratorioMapper.consultarSiguienteIdLaboratorio();
    }
}
