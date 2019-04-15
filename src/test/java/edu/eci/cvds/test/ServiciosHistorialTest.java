package edu.eci.cvds.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import static org.quicktheories.QuickTheory.qt;

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
	
	/*@Test
	public void deberiaRegistrarElementos() {
		qt().forAll(ElementoGenerator.genElementos()).check(elem -> {
			try {
				serviciosHistorial.registrarElemento(elem);
				return true;
			}catch(ExcepcionServiciosHistorial e) {
				e.printStackTrace();
				return false;
			}
		});
	}*/

}
