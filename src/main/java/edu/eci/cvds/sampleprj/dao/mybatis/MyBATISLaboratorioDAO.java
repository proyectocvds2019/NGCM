package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratorioMapper;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;

import javax.inject.Inject;
import java.util.Date;
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

    @Override
    public List<Laboratorio> consultarLaboratorios() throws PersistenceException{
        return laboratorioMapper.consultarLaboratorios();
    }

    @Override
    public Integer consultarNumeroEquipos(Laboratorio laboratorio) throws PersistenceException{
        return laboratorioMapper.consultarNumeroEquipos(laboratorio);
    }

    @Override
    public Date consultarFechaRegistro(Laboratorio laboratorio) throws PersistenceException{
        return laboratorioMapper.consultarFechaRegistro(laboratorio);
    }

    @Override
    public void eliminarLaboratorio(Laboratorio laboratorio) throws PersistenceException{
        laboratorioMapper.eliminarLaboratorio(laboratorio);
    }

    @Override
    public void desasociarLaboratorioDeEquipos(Laboratorio laboratorio) throws PersistenceException{
        laboratorioMapper.desasociarLaboratorioDeEquipos(laboratorio);
    }

    @Override
    public Integer consultarEquiposEliminadosLaboratorio(Laboratorio laboratorio) throws PersistenceException{
        return laboratorioMapper.consultarEquiposEliminadosLaboratorio(laboratorio);
    }

}
