package edu.eci.cvds.test;

import edu.eci.cvds.samples.entities.Laboratorio;
import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.integers;

import edu.eci.cvds.samples.entities.Equipo;

public class EquipoGenerator {
	
	public static Gen<Equipo> genEquipos(){
		return source->{
			int id = genId().generate(source);
			/*LaboratorioGenerator ge = new LaboratorioGenerator();
			Laboratorio lab = ge.genLaboratorio().generate(source);
			*/
                        return new Equipo(id,true,null);
		};
	}
	
	public static Gen<Integer> genId(){
		return integers().between(1,9999);
	}
	
}
