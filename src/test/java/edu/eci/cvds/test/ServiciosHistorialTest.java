package edu.eci.cvds.test;

import java.util.List;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import org.junit.Test;


import org.quicktheories.QuickTheory.*;



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
	public void deberiaConsultarElemento() {
		
		qt.forAll(ElementoGenerator.genElementos(),EquipoGenerator.genEquipos()).check((el,eq) ->{
			try {
				this.serviciosHistorial.registrarElemento(e,"cesar.villamil@mail.escuelaing.edu.co", eq);
				Elemento e;
				e=this.serviciosHistorial.consultarElemento(e.getId());
				if(e!=null) return true;
				else return false;
			} catch (ExcepcionServiciosHistorial e) {
				System.out.println("No se puede registrar elemento");
				e.printStackTrace();
				return false;
			}
		});
	}

	@Test
	public void deberiaConsultarEquipo() {
		qt().forAll(EquipoGenerator.genEquipos(), LaboratorioGenerator.genLaboratorio()).check((eq,lab) ->{
			try {
				eq.getLaboratorio().setId(null);
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
				eq.getLaboratorio().setId(null);
				this.serviciosHistorial.registrarEquipo(eq);
				return true;
			}  catch(ExcepcionServiciosHistorial e) {
				e.printStackTrace();
				return false;
			}
		});
	}
}

