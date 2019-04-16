package edu.eci.cvds.test;

import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.integers;

import edu.eci.cvds.samples.entities.Equipo;

public class EquipoGenerator {
	
	public static Gen<Equipo> genEquipos(){
		return source->{
			int id = genId().generate(source);
			return new Equipo(id);
		};
	}
	
	public static Gen<Integer> genId(){
		return integers().between(1,9999);
	}
	
}
