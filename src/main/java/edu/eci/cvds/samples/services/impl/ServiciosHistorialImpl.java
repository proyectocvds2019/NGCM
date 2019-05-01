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
				if(elem != null){
					elementoDAO.actualizarIdEquipo(elem.getId(),null);
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
		response.addHeader("Content-disposition","attachment; filename=tabla.xls");
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
				celda.setCellValue(this.consultarElementoDelEquipo(TipoElemento.TECLADO,e).getNombre());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(this.consultarElementoDelEquipo(TipoElemento.TORRE,e).getNombre());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(this.consultarElementoDelEquipo(TipoElemento.MOUSE,e).getNombre());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(this.consultarElementoDelEquipo(TipoElemento.MONITOR,e).getNombre());
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
		response.addHeader("Content-disposition","attachment; filename=tabla.xls");
		response.setContentType("application/vnd.ms-excel");
		try{
			HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
			HSSFSheet sheet = wb.createSheet("Equipos"); // crea hoja
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

	


}
