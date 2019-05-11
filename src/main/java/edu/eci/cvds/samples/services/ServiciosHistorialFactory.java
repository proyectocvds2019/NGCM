package edu.eci.cvds.samples.services;

import java.util.Optional;

import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISLaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISNovedadDAO;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISElementoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISEquipoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISUsuarioDAO;
import edu.eci.cvds.samples.services.impl.ServiciosHistorialImpl;

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
	               bind(ServiciosHistorial.class).to(ServiciosHistorialImpl.class);
	               bind(UsuarioDAO.class).to(MyBATISUsuarioDAO.class);
	               bind(ElementoDAO.class).to(MyBATISElementoDAO.class);
	               bind(EquipoDAO.class).to(MyBATISEquipoDAO.class);
	               bind(LaboratorioDAO.class).to(MyBATISLaboratorioDAO.class);
	               bind(NovedadDAO.class).to(MyBATISNovedadDAO.class);
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
	               bind(ServiciosHistorial.class).to(ServiciosHistorialImpl.class);
	               bind(UsuarioDAO.class).to(MyBATISUsuarioDAO.class);
	               bind(ElementoDAO.class).to(MyBATISElementoDAO.class);
	               bind(EquipoDAO.class).to(MyBATISEquipoDAO.class);
				   bind(LaboratorioDAO.class).to(MyBATISLaboratorioDAO.class);
				   bind(NovedadDAO.class).to(MyBATISNovedadDAO.class);
	           }
	       });
	   }
	
	private ServiciosHistorialFactory() {
		optInjector = Optional.empty(); 
		optInjectorTest = Optional.empty();
	}
	
	public ServiciosHistorial getServiciosHistorial() {
		if(!optInjector.isPresent()) {
			optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
		}
		ServiciosHistorial s= optInjector.get().getInstance(ServiciosHistorial.class);
		return s;
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
