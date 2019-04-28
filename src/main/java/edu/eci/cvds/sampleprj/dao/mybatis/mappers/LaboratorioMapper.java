package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import org.apache.ibatis.annotations.Param;

public interface LaboratorioMapper {
    public Laboratorio consultarLaboratorio(@Param("id") Integer id);

    public Laboratorio consultarLaboratorio2(@Param("equipo")Equipo equipo);
}
