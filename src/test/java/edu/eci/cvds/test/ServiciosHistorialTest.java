package edu.eci.cvds.test;

import java.util.List;
import edu.eci.cvds.samples.entities.Equipo;
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
				List<Equipo> lista;
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
	
	@Test
	public void deberiaConsultarEquipo() {
		qt().forAll(EquipoGenerator.genEquipos(), LaboratorioGenerator.genLaboratorio()).check((eq,lab) ->{
			try {

				this.serviciosHistorial.registrarEquipo(eq);
				Equipo e = this.serviciosHistorial.consultarEquipo(eq.getId());
                                return true;
			} catch(ExcepcionServiciosHistorial e) {
				e.printStackTrace();
				return false;
			}
		});
	}

	@Test
	public void deberiaRegistrarEquipo() {
		qt().forAll(EquipoGenerator.genEquipos()).check(eq->{
			try {
				this.serviciosHistorial.registrarEquipo(eq);

				return true;
			}  catch(ExcepcionServiciosHistorial e) {
				e.printStackTrace();
				return false;
			}
		});
	}
	
}
