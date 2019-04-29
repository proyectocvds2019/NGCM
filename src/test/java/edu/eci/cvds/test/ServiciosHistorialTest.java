package edu.eci.cvds.test;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import org.junit.Test;

import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorialFactory;

import static org.quicktheories.QuickTheory.qt;

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
	public void deberiaConsultarEquipo() {
		qt().forAll(EquipoGenerator.genEquipos()).check((eq) ->{
			try {
				Integer id = this.serviciosHistorial.proximoIdEquipo();
				eq.setId(id);
				Laboratorio laboratorio = new Laboratorio();
				this.serviciosHistorial.registrarEquipo(eq,laboratorio);
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
		qt().forAll(EquipoGenerator.genEquipos(), LaboratorioGenerator.genLaboratorio()).check((eq,lab)->{
			try {
				Integer id = this.serviciosHistorial.proximoIdEquipo();
				eq.setId(id);
				Laboratorio laboratorio = new Laboratorio();
				this.serviciosHistorial.registrarEquipo(eq, laboratorio);
				return true;
			}  catch(ExcepcionServiciosHistorial e) {
				e.printStackTrace();
				return false;
			}
		});
	}
}

