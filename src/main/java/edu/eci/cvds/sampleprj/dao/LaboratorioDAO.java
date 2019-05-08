package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;

import java.util.List;

public interface LaboratorioDAO {

    public Laboratorio consultarLaboratorio(Integer id) throws PersistenceException;

    public Laboratorio consultarLaboratorio(Equipo equipo) throws PersistenceException;
    
    public void registrarLaboratorio(Laboratorio lab) throws PersistenceException;

    public List<Laboratorio> consultarLaboratoriosDisponibles() throws PersistenceException;

    public Integer consultarSiguienteIdLaboratorio() throws PersistenceException;
}
