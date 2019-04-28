package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratorioMapper;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;

public class MyBATISLaboratorioDAO implements LaboratorioDAO {
    @Inject
    LaboratorioMapper laboratorioMapper;

    public Laboratorio consultarLaboratorio(Integer id) throws PersistenceException{
        return laboratorioMapper.consultarLaboratorio(id);
    }

    public Laboratorio consultarLaboratorio(Equipo equipo) throws PersistenceException{
        return laboratorioMapper.consultarLaboratorio2(equipo);
    }
}
