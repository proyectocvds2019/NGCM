package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.SQLException;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ElementoMapper;

public class MyBATISElementoDAO implements ElementoDAO{
	
	@Inject
	private ElementoMapper ElementoMapper;
	
	public void registrarElemento(Elemento elemento)  throws PersistenceException{
		ElementoMapper.registrarElemento(elemento);
		
	}

}
