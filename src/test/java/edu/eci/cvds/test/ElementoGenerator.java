package edu.eci.cvds.test;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;
import static org.quicktheories.generators.SourceDSL.strings;

import static org.quicktheories.generators.SourceDSL.integers;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.TipoElemento;


public class ElementoGenerator {
	
	
	public static Gen<String> genId(){
		return strings().allPossible().ofLengthBetween(10,20);
	}

	public static Gen<String> genNombre(){
		return strings().allPossible().ofLengthBetween(10,20);
	}
	
	
	public static Gen<TipoElemento> genTipoElemento(){
		return Generate.enumValues(TipoElemento.class);
	}
	
	
	public static Gen<Elemento> genElementos(){
		return source->{
			String id = genId().generate(source);
			String nombre = genNombre().generate(source);
			TipoElemento tipoElemento = genTipoElemento().generate(source);
			return new Elemento(id,tipoElemento,nombre);
		};
	}

}
