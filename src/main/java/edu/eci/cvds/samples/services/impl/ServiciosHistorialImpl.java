package edu.eci.cvds.samples.services.impl;

import java.io.OutputStream;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class ServiciosHistorialImpl implements ServiciosHistorial{
	
	@Inject
	private ElementoDAO elementoDAO;
	@Inject
	private EquipoDAO equipoDAO;
	@Inject
	private LaboratorioDAO laboratorioDAO;

	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws ExcepcionServiciosHistorial {
		try {
			if(equipo != null){
				Equipo equi = equipoDAO.consultarEquipo(equipo);
				for (Elemento e: equi.getElementos()){
					System.out.println(e.getId());
					if(e.getTipo().name().equals(elemento.getTipo().name())){
						System.out.println("entro "+e.getId());
						elementoDAO.actualizarIdEquipo(e.getId(),null);
					}
				}
			}
			elementoDAO.registrarElemento(elemento,correoUsuario,equipo);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo registrar el elemento.");
		}
	}

	@Override
	public List<Equipo> consultarEquipos() throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.consultarEquipos();
		} catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar los equipos.");
		}
	}

	@Override
	public List<Elemento> consultarElementos() throws ExcepcionServiciosHistorial {
		try {
			return elementoDAO.consultarElementos();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("no se pudo consultar los elementos");
		}
	}

	@Override
	public List<Elemento> consultarElementosDisponibles() throws ExcepcionServiciosHistorial {
		try {
			return elementoDAO.consultarElementosDisponibles();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar los elementos disponibles");
		}
	}

	@Override
	public void registrarEquipo(Equipo equipo, Laboratorio laboratorio) throws ExcepcionServiciosHistorial {
		try {
			equipoDAO.registrarEquipo(equipo, laboratorio);
			for(Elemento e: equipo.getElementos()) {
				actualizarIdEquipoEnElemento(e.getId(), equipo.getId());
			}
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo registrar el equipo.");
		}
	}
	
	@Override
	public Equipo consultarEquipo(int id) throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.consultarEquipo(id);
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar el equipo solicitado.");
		}
	}

	@Override
	public List<Equipo> consultarEquiposDisponiblesParaElemento(TipoElemento tipo) throws ExcepcionServiciosHistorial{
		try{
			return equipoDAO.consultarEquiposDisponiblesParaElemento(tipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar los equipos disponibles");
		}
	}

	@Override
	public  Integer consultarEquipoDeElemento(Elemento elemento) throws  ExcepcionServiciosHistorial{
		try{
			return equipoDAO.consultarEquipoDeElemento(elemento);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el equipo asociado al elemento: "+elemento.getId());
		}
	}

	@Override
	public Elemento consultarElemento(String id) throws ExcepcionServiciosHistorial{
		try{
			return elementoDAO.consultarElemento(id);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el elemento");
		}
	}
	@Override
	public Laboratorio consultarLaboratorio(Integer id) throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarLaboratorio(id);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el laboratorio");
		}
	}

	@Override
	public List<Elemento> consultarElementosDisponibles(TipoElemento tipo) throws ExcepcionServiciosHistorial{
		try{
			return elementoDAO.consultarElementosDisponibles(tipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar los elementos disponibles de tipo: "+tipo);
		}
	}

	@Override
	public void actualizarIdEquipoEnElemento(String idElemento, Integer idEquipo) throws ExcepcionServiciosHistorial {
		try {
			if(idEquipo != null){
				Elemento elem = elementoDAO.consultarElemento(idElemento);
				Equipo equi = equipoDAO.consultarEquipo(idEquipo);
				Elemento elem2 =null;
				if(equi != null){
					elem2 = elementoDAO.consultarElementoDelEquipo(elem.getTipo(),equi);
				}
				if(elem != null){
					elementoDAO.actualizarIdEquipo(elem.getId(),null);
				}
				if(elem2 != null){
					elementoDAO.actualizarIdEquipo(elem2.getId(),null);
				}

			}
			elementoDAO.actualizarIdEquipo(idElemento,idEquipo);
		}catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo actualizar el idEquipo en el elemento.");
		}
		
	}

	@Override
	public void desactivarElemento(String id) throws ExcepcionServiciosHistorial {
		try {
			elementoDAO.desactivarElemento(id);
		}catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo dar de baja al elemento.");
		}
		
	}

	@Override
	public Integer proximoIdEquipo() throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.proximoIdEquipo();
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar el proximo id de equipo.");
		}
	}

	@Override
	public Laboratorio consultarLaboratorio(Equipo equipo) throws ExcepcionServiciosHistorial{
		try {
			return laboratorioDAO.consultarLaboratorio(equipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el laboratorio del equipo: "+equipo.getId());
		}
	}

	@Override
	public Elemento consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo) throws ExcepcionServiciosHistorial{
		try {
			return elementoDAO.consultarElementoDelEquipo(tipo,equipo);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consulta el elemento de tipo "+tipo+" y equipo "+equipo.getId());
		}
	}

	@Override
	public void exportarExcelEquipos() throws ExcepcionServiciosHistorial{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=equipos.xls");
		response.setContentType("application/vnd.ms-excel");
		try{
			HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
			HSSFSheet sheet = wb.createSheet("Equipos"); // crea hoja
			List<Equipo> equipos = this.consultarEquipos();
			int rownum = 0;
			int column = 0;
			HSSFRow row = sheet.createRow(rownum);
			HSSFCell celda = row.createCell(column);
			celda.setCellValue("ID");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("ACTIVO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("LABORATORIO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("TECLADO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("TORRE");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("MOUSE");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("MONITOR");
			column++;
			rownum = 1;
			for(Equipo e: equipos){
				column = 0;
				row = sheet.createRow(rownum);
				celda = row.createCell(column);
				celda.setCellValue(e.getId());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(e.getActivo());
				column++;
				celda = row.createCell(column);
				Laboratorio lab = this.consultarLaboratorio(e);
				if(lab == null){
					celda.setCellValue("");
				}else{
					celda.setCellValue(lab.getId());
				}
				column++;
				celda = row.createCell(column);
				Elemento tec = this.consultarElementoDelEquipo(TipoElemento.TECLADO,e);
				if(tec == null){
					celda.setCellValue("");
				}else{
					celda.setCellValue(tec.getNombre());
				}

				column++;
				celda = row.createCell(column);
				Elemento tor = this.consultarElementoDelEquipo(TipoElemento.TORRE,e);
				if(tor == null){
					celda.setCellValue("");
				}else{
					celda.setCellValue(tor.getNombre());
				}
				column++;
				celda = row.createCell(column);
				Elemento mou = this.consultarElementoDelEquipo(TipoElemento.MOUSE,e);
				if(mou == null){
					celda.setCellValue("");
				}else{
					celda.setCellValue(mou.getNombre());
				}
				column++;
				celda = row.createCell(column);
				Elemento mon = this.consultarElementoDelEquipo(TipoElemento.MONITOR,e);
				if(mon == null){
					celda.setCellValue("");
				}else{
					celda.setCellValue(mon.getNombre());
				}
				column++;
				rownum++;
			}
			OutputStream out = response.getOutputStream();
			wb.write(out);
		}catch (ExcepcionServiciosHistorial e){
			throw new ExcepcionServiciosHistorial("No se pudo exportar el excel de los equipos");
		}catch (Exception e){
			e.printStackTrace();
		}
		try {
			response.getOutputStream().flush();
		}catch (Exception e){
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().responseComplete();
	}

	@Override
	public void desactivarEquipo(int id) throws ExcepcionServiciosHistorial {
		try {
			equipoDAO.desactivarEquipo(id);
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo dar de baja al equipo.");
		}
		
	}

	@Override
	public void exportarElementos() throws ExcepcionServiciosHistorial{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=elementos.xls");
		response.setContentType("application/vnd.ms-excel");
		try{
			HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
			HSSFSheet sheet = wb.createSheet("Elementos"); // crea hoja
			List<Elemento> elementos = this.consultarElementos();
			int rownum = 0;
			int column = 0;
			HSSFRow row = sheet.createRow(rownum);
			HSSFCell celda = row.createCell(column);
			celda.setCellValue("ID");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("NOMBRE");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("TIPO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("EQUIPO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("ACTIVO");
			rownum = 1;
			for(Elemento e: elementos){
				column = 0;
				row = sheet.createRow(rownum);
				celda = row.createCell(column);
				celda.setCellValue(e.getId());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(e.getNombre());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(e.getTipo().name());
				column++;
				celda = row.createCell(column);
				Integer equi = this.consultarEquipoDeElemento(e);
				if(equi == null){
					celda.setCellValue("");
				}else{
					celda.setCellValue(equi);
				}
				column++;
				celda = row.createCell(column);
				celda.setCellValue(e.isActivo());
				column++;
				rownum++;
			}
			OutputStream out = response.getOutputStream();
			wb.write(out);
		}catch (ExcepcionServiciosHistorial e){
			throw new ExcepcionServiciosHistorial("No se pudo exportar el excel de los equipos");
		}catch (Exception e){
			e.printStackTrace();
		}
		try {
			response.getOutputStream().flush();
		}catch (Exception e){
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().responseComplete();
	}

	@Override
	public List<Equipo> consultarEquiposDisponibles() throws ExcepcionServiciosHistorial{
		try {
			return this.equipoDAO.consultarEquiposDisponibles();
		}catch (PersistenceException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void cambiarIDElemento(Elemento elemento, String id) throws ExcepcionServiciosHistorial{
		try{
			this.elementoDAO.cambiarIDElemento(elemento,id);
		}catch(PersistenceException e){
			e.printStackTrace();
		}
	}

	@Override
	public void cambiarNombreElemento(Elemento elemento, String nombre) throws ExcepcionServiciosHistorial{
		try{
			this.elementoDAO.cambiarNombreElemento(elemento,nombre);
		}catch(PersistenceException e){
			e.printStackTrace();
		}
	}

	@Override
	public void registrarLaboratorio(Laboratorio lab) throws ExcepcionServiciosHistorial {
		try {
			laboratorioDAO.registrarLaboratorio(lab);
			for(Equipo e: lab.getEquipos()) {
				actualizarIdLaboratorioEnEquipo(e.getId(), lab.getId());
			}
		}catch (PersistenceException ex) {
			throw new ExcepcionServiciosHistorial("No se pudo registrar el laboratorio");
		}
	}	

	private void actualizarIdLaboratorioEnEquipo(Integer idEquipo, Integer idLab) throws ExcepcionServiciosHistorial{
		try {
			if(idLab != null) {
				Equipo eq = equipoDAO.consultarEquipo(idEquipo);
				Laboratorio lab = laboratorioDAO.consultarLaboratorio(idLab);
				Equipo eq2 = null;
				if(lab != null) {
					eq2 = equipoDAO.consultarEquipoDelLaboratorio(lab);
				}
				if(eq != null) {
					equipoDAO.actualizarIdLaboratorio(null, eq.getId());
				}
				if(eq2 != null) {
					equipoDAO.actualizarIdLaboratorio(null, eq2.getId());
				}
			}
			equipoDAO.actualizarIdLaboratorio(idLab, idEquipo);
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo actualizar el idLaboratorio en el equipo.");
		}
	}
	
	@Override
	public Equipo consultarEquipoDelLaboratorio(Laboratorio lab) throws ExcepcionServiciosHistorial {
		try {
			return equipoDAO.consultarEquipoDelLaboratorio(lab);
		} catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar el equipo del laboratorio"+lab.getId());
		}
	}

}
