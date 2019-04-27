package edu.eci.cvds.test;

import org.junit.Test;

import  org.quicktheories.QuickTheory.qt;


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
				if(serviciosHistorial.consultarElemento(elem.getId())!=null) return true;
				else return false;
			}catch(ExcepcionServiciosHistorial e) {
				System.out.println("No se puede registrar un elemento con el mismo id");
				e.printStackTrace();
				return false;
			}
		});
	}
	
	@Test
	public void deberiaRegistrarEquipo() {
		qt().forAll(EquipoGenerator.genEquipos()).check((eq) -> {
			try {
				this.serviciosHistorial.registrarEquipo(eq);
				lista=serviciosHistorial.consultarEquipos();
				if(lista.contains(eq))return true;
				else return false;

			}catch(ExcepcionServiciosHistorial e) {
				System.out.println("No se pudo registrar un equipo con ese  id");

				e.printStackTrace();
				return false;
			}
		});
	}

}
