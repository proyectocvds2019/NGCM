package edu.eci.cvds.samples.services;

import java.util.Optional;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISElementoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISUsuarioDAO;

import static com.google.inject.Guice.createInjector;

public class ServiciosHistorialFactory {
	
	private static ServiciosHistorialFactory instancia = new ServiciosHistorialFactory();
	
	private static Optional<Injector> optInjector;
	
	private static Optional<Injector> optInjectorTest;
	
	private Injector myBatisInjector(final String env, final String pathResource) {
	       return createInjector(new XMLMyBatisModule() {
	           @Override
	           protected void initialize() {
	        	   install(JdbcHelper.PostgreSQL);
	               setEnvironmentId(env);
	               setClassPathResource(pathResource);
	               bind(UsuarioDAO.class).to(MyBATISUsuarioDAO.class);
	               bind(ElementoDAO.class).to(MyBATISElementoDAO.class);
	           }
	       });
	   }
	
	private Injector myBatisInjectorTest(final String env, final String pathResource) {
	       return createInjector(new XMLMyBatisModule() {
	           @Override
	           protected void initialize() {
	        	   install(JdbcHelper.PostgreSQL);
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
	
	public ServiciosHistorial getServiciosHistorialTest() {
		if(!optInjectorTest.isPresent()) {
			optInjectorTest = Optional.of(myBatisInjectorTest("test","mybatis-config-h2.xml"));
		}
		return optInjectorTest.get().getInstance(ServiciosHistorial.class);
	}
	
	public static ServiciosHistorialFactory getInstance() {
		return instancia;
	}
}
