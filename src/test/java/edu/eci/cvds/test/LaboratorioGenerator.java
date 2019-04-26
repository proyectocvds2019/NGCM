package edu.eci.cvds.test;

import edu.eci.cvds.samples.entities.Laboratorio;
import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

import edu.eci.cvds.samples.entities.Equipo;

public class LaboratorioGenerator {

    public Gen<Laboratorio> genLaboratorio(){
        return source -> {
            Integer id = genId().generate(source);
            String nombre = genNombre().generate(source);
            return new Laboratorio(id,nombre,true);
        };
    }

    public Gen<Integer> genId(){
        return integers().allPositive();
    }

    public Gen<String> genNombre(){
        return strings().allPossible().ofLengthBetween(10,20);
    }
}