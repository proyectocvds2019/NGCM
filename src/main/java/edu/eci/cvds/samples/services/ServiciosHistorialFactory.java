package edu.eci.cvds.samples.services;

import java.util.Optional;

import org.mybatis.guice.XMLMyBatisModule;

import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISElementoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISUsuarioDAO;

import static com.google.inject.Guice.createInjector;

public class ServiciosHistorialFactory {
	
	private static ServiciosHistorialFactory instancia = new ServiciosHistorialFactory();
	
	private static Optional<Injector> optInjector;
	
	private Injector myBatisInjector(final String env, final String pathResource) {
	       return createInjector(new XMLMyBatisModule() {
	           @Override
	           protected void initialize() {
	               setEnvironmentId(env);
	               setClassPathResource(pathResource);
	               bind(UsuarioDAO.class).to(MyBATISUsuarioDAO.class);
	               bind(ElementoDAO.class).to(MyBATISElementoDAO.class);
	           }
	       });
	   }
	
	private ServiciosHistorialFactory() {
		optInjector = Optional.empty(); 
	}
	
	public ServiciosHistorial getServiciosHistorial() {
		if(!optInjector.isPresent()) {
			optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
		}
		return optInjector.get().getInstance(ServiciosHistorial.class);
	}
	
	public static ServiciosHistorialFactory getInstance() {
		return instancia;
	}
}