package edu.eci.cvds.test;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;
import org.junit.Test;

import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorialFactory;

import static org.junit.Assert.assertTrue;
import static org.quicktheories.QuickTheory.qt;

public class ServiciosHistorialTest {

	private ServiciosHistorial serviciosHistorial;

	public ServiciosHistorialTest() {
		this.serviciosHistorial = ServiciosHistorialFactory.getInstance().getServiciosHistorialTest();
	}

	@Test
	public void deberiaRegistrarElementos() {
		/*qt().forAll(ElementoGenerator.genElementos()).check(elem -> {
			try {
				System.out.println(elem.getId()+" ##############################################");
				this.serviciosHistorial.registrarElemento(elem, "gualdronsito@hotmail.com", null);
				if(serviciosHistorial.consultarElemento(elem.getId())!=null) return true;
				else return false;
			}catch(ExcepcionServiciosHistorial e) {
				System.out.println("No se puede registrar un elemento con el mismo id");
				e.printStackTrace();
				return false;
			}
		});*/
		Elemento e1 = new Elemento("1", TipoElemento.TECLADO,"nombre",true);
		Elemento e2 = new Elemento("2",TipoElemento.TORRE,"nombre",true);
		Elemento e3 = new Elemento("3",TipoElemento.MOUSE,"nombre",false);
		Elemento e4 = new Elemento("4",TipoElemento.MONITOR,"nombre",true);
		try{
			this.serviciosHistorial.registrarElemento(e1,"gualdronsito@hotmail.com",null);
			assertTrue(true);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void deberiaConsultarEquipo() {
		/*qt().forAll(EquipoGenerator.genEquipos()).check((eq) ->{
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
		});*/
		try{
			this.serviciosHistorial.consultarEquipo(1);
			this.serviciosHistorial.consultarEquipo(2);
			this.serviciosHistorial.consultarEquipo(3);
			this.serviciosHistorial.consultarEquipo(4);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			assertTrue(false);
		}
	}



	@Test
	public void deberiaRegistrarEquipo() {
		/*qt().forAll(EquipoGenerator.genEquipos(), LaboratorioGenerator.genLaboratorio()).check((eq,lab)->{
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
		});*/
		Equipo e1 = new Equipo(1,true);
		Equipo e2 = new Equipo(2,true);
		Equipo e3 = new Equipo(3,true);
		Equipo e4 = new Equipo(4,true);
		try{
			this.serviciosHistorial.registrarEquipo(e1,null);
			this.serviciosHistorial.registrarEquipo(e2,null);
			this.serviciosHistorial.registrarEquipo(e3,null);
			this.serviciosHistorial.registrarEquipo(e4,null);
			assertTrue(true);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void deberiaAsociarElemento(){
		/*qt().forAll(EquipoGenerator.genEquipos(), ElementoGenerator.genElementos()).check((equipo, elemento) ->{
			try{
				this.serviciosHistorial.registrarElemento(elemento,"gualdronsito@hotmail.com",null);
				Integer id = this.serviciosHistorial.proximoIdEquipo();
				equipo.setId(id);
				this.serviciosHistorial.registrarEquipo(equipo,null);
				System.out.println(elemento.getId()+" "+equipo.getId()+" %%%%%%%%%%%%%%%%%%%%%%%%");
				this.serviciosHistorial.actualizarIdEquipoEnElemento(elemento.getId(),equipo.getId());
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
				return true;
			}catch (ExcepcionServiciosHistorial e){
				e.printStackTrace();
				return false;
			}
		});*/
		try{
			this.serviciosHistorial.actualizarIdEquipoEnElemento("1",1);
			assertTrue(true);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			assertTrue(false);
		}
	}
        
        @Test
        public void deberiaCrearLaboratorio(){
            try{
                Integer id = 24082000;
                String nombre = "Laboratorio1";
                Laboratorio n1 = new Laboratorio(id,nombre,true); 
                this.serviciosHistorial.consultarLaboratorio(id);
                assertTrue(true);
            }catch(ExcepcionServiciosHistorial e){
                e.printStackTrace();
                assertTrue(false);
            }
        }
        
        @Test
        public void deberiaAsociarEquipo(){
            try{
                Equipo eq = new Equipo(1,true);
                Laboratorio la = new Laboratorio(24921, "Plataformas", true);
                this.serviciosHistorial.actualizarIdLaboratorioEnEquipo(eq.getId(), la.getId());
                assertTrue(true);
            }catch(ExcepcionServiciosHistorial e){
                e.printStackTrace();
                assertTrue(false);
            }
        
        
        }
        
        
}

