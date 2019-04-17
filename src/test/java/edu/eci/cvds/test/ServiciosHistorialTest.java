	package edu.eci.cvds.test;

import org.junit.Test;

import static org.quicktheories.QuickTheory.qt;


import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorialFactory;

public class ServiciosHistorialTest {
	
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
