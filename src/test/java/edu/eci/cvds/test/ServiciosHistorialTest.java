package edu.eci.cvds.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.quicktheories.core.Gen;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

import com.google.inject.Inject;

import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorialFactory;

public class ServiciosHistorialTest {
	
	@Inject
	private SqlSession sqlSession;
	
	private ServiciosHistorial serviciosHistorial;
	
	public ServiciosHistorialTest() {
		this.serviciosHistorial = ServiciosHistorialFactory.getInstance().getServiciosHistorialTest();
	}
	
	@Test
	public void deberiaRegistrarElementos() {
		qt().forAll(ElementoGenerator.genElementos()).check(elem -> {
			try {
				this.serviciosHistorial.registrarElemento(elem, "gualdronsito@hotmail.com", null);
				return true;
			}catch(ExcepcionServiciosHistorial e) {
				e.printStackTrace();
				return false;
			}
		});
	}
	
	@Test
	public void deberiaConsultarEquipos() {
		qt().forAll(EquipoGenerator.genEquipos()).check(eq -> {
			try {
				this.serviciosHistorial.consultarEquipos();
				return true;
			}catch(ExcepcionServiciosHistorial e) {
				System.out.println("jajaja");
				e.printStackTrace();
				return false;
			}
		});
	}

}
