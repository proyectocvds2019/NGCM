package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Usuario;

public interface UsuarioDAO {

	public void registrarUsuario(Usuario usuario)  throws PersistenceException;

	public Usuario cargarUsuario(int id) throws PersistenceException;
	
}
