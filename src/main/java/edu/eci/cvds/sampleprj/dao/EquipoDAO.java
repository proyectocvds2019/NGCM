package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;

public interface EquipoDAO {

	public void registrarEquipo(Equipo equipo, Laboratorio laboratorio) throws PersistenceException;
	
	public Equipo consultarEquipo(Integer id) throws PersistenceException;
	
	public List<Equipo> consultarEquipos() throws PersistenceException;

	public List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws PersistenceException;

	public Integer consultarEquipoDeElemento(Elemento elemento) throws PersistenceException;
	
	public Integer proximoIdEquipo() throws PersistenceException;
        
        public void desactivarEquipo(Integer id) throws PersistenceException;

        public List<Equipo> consultarEquiposDisponibles() throws PersistenceException;
    
        public void actualizarIdLaboratorio(Integer idLab, Integer idEq) throws PersistenceException;

	public Equipo consultarEquipoDelLaboratorio(Laboratorio lab) throws PersistenceException;

	
}
