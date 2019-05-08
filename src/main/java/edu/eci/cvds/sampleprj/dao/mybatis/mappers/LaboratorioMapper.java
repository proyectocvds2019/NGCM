package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LaboratorioMapper {
	
    public Laboratorio consultarLaboratorio(@Param("id") Integer id);

    public Laboratorio consultarLaboratorio2(@Param("equipo")Equipo equipo);
    
    public void registrarLaboratorio(@Param("lab") Laboratorio lab);
    
    public void desactivarLaboratorio(@Param("id") int id);

    public List<Laboratorio> consultarLaboratoriosDisponibles();

    public Integer consultarSiguienteIdLaboratorio();
}
