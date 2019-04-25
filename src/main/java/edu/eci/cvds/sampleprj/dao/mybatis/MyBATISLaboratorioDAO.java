package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratorioMapper;

import javax.inject.Inject;

public class MyBATISLaboratorioDAO implements LaboratorioDAO {
    @Inject
    LaboratorioMapper laboratorioMapper;
}
