package edu.eci.cvds.test;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.generators.SourceDSL.integers;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.tipoElemento;


public class ElementoGenerator {
	
	
	public static Gen<Integer> genId(){
		return integers().between(1,9999);
	}
	
	
	public static Gen<tipoElemento> genTipoElemento(){
		return Generate.enumValues(tipoElemento.class);
	}
	
	
	public static Gen<Elemento> genElementos(){
		return source->{
			int id = genId().generate(source);
			tipoElemento tipoElemento = genTipoElemento().generate(source);
			return new Elemento(id,tipoElemento);
		};
	}

}
