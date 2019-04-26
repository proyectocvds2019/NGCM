package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Laboratorio;

public interface LaboratorioDAO {

    public Laboratorio consultarLaboratorio(Integer id) throws PersistenceException;
}
