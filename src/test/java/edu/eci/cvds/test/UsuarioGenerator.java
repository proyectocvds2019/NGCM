package edu.eci.cvds.test;

import org.quicktheories.core.Gen;

import edu.eci.cvds.samples.entities.Usuario;

import static org.quicktheories.generators.SourceDSL.strings;

public class UsuarioGenerator {
	public static Gen<String> genNombre(){
		return strings().allPossible().ofLengthBetween(15, 50);
	}
	
	public static Gen<String> genCorreo(){
		return strings().allPossible().ofLengthBetween(15, 50);
	}
	
	public static Gen<String> genContrasena(){
		return strings().allPossible().ofLengthBetween(15, 50);
	}
	
	public static Gen<Usuario> genUsuario(){
		return resource->{
			String nombre = genNombre().generate(resource);
			String correo = genCorreo().generate(resource);
			String cont = genContrasena().generate(resource);
			String rol = "admin";
			return new Usuario(nombre,correo,cont,rol);
		};
	}
}
