package edu.eci.cvds.samples.services.impl;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.entities.TipoElemento;

import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

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
	@Inject
	private NovedadDAO novedadDAO;

	public void registrarElemento(Elemento elemento, String correoUsuario, Integer equipo) throws ExcepcionServiciosHistorial {
		try {
			if(equipo != null){
				Equipo equi = equipoDAO.consultarEquipo(equipo);
				for (Elemento e: equi.getElementos()){
					if(e.getTipo().name().equals(elemento.getTipo().name())){
						elementoDAO.actualizarIdEquipo(e.getId(),null);
						this.registrarNovedad("Desasociando equipo", "Se ha desasociado el equipo "+equi.getId(), "novedadModificar", correoUsuario, null, e.getId());
					}
				}
			}
			elementoDAO.registrarElemento(elemento,correoUsuario,equipo);
			this.registrarNovedad("Registro", "Se ha registrado este elemento", "novedadRegistro", correoUsuario, null, elemento.getId());
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
			Subject subject = SecurityUtils.getSubject();
			equipoDAO.registrarEquipo(equipo, laboratorio);
			System.out.println(equipo.getId());
			this.registrarNovedad("Registro", "Se ha registrado el equipo "+equipo.getId(), "novedadRegistro", (String) subject.getSession().getAttribute("correo"), equipo.getId(), null);
			for(Elemento e: equipo.getElementos()) {
				System.out.println(e.getId());
				Integer ee = this.consultarEquipoDeElemento(e);
				actualizarIdEquipoEnElemento(e.getId(), equipo.getId());
			}
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo registrar el equipo.");
		}catch(Exception e) {
			
		}
	}
	
	@Override
	public Equipo consultarEquipo(Integer id) throws ExcepcionServiciosHistorial {
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
			Subject subject = SecurityUtils.getSubject();
			System.out.println("#######################");
			if(idEquipo != null || idEquipo == null){
				Elemento elem = elementoDAO.consultarElemento(idElemento);
				Equipo equi = equipoDAO.consultarEquipo(idEquipo);
				Elemento elem2 =null;
				System.out.println(elem+" "+equi+" "+elem2);
				if(equi != null){
					elem2 = elementoDAO.consultarElementoDelEquipo(elem.getTipo(),equi);
					if(equi.getActivo()) {
						System.out.println("siiiiiiiiiiiiiiiiii");
						this.registrarNovedad("Asociar", "el elemeto "+idElemento+" Se ha asociado al equipo "+idEquipo, "novedadModificar", (String) subject.getSession().getAttribute("correo"), null, idElemento);
						elementoDAO.actualizarIdEquipo(idElemento, idEquipo);
					}
					else {
						throw new ExcepcionServiciosHistorial("No se pudo actualizar el idEquipo en el elemento.");
					}
				}
				if(elem != null){
					this.registrarNovedad("asosiacion", "el elemento "+idElemento+" se ha asociado al equipo "+idEquipo, "novedadCambio", (String) subject.getSession().getAttribute("correo"), idEquipo, idElemento);
					elementoDAO.actualizarIdEquipo(elem.getId(),idEquipo);
				}
				if(elem2 != null){
					this.registrarNovedad("desasosiacion", "el elemento "+elem2.getId()+" se ha desasociado del equipo "+equi.getId(), "novedadCambio", (String) subject.getSession().getAttribute("correo"), idEquipo, idElemento);
					elementoDAO.actualizarIdEquipo(elem2.getId(),null);
				}
			}else {
				throw new ExcepcionServiciosHistorial("No se pudo actualizar el idEquipo en el elemento.");
			}
		}catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo actualizar el idEquipo en el elemento.");
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void desactivarElemento(String id) throws ExcepcionServiciosHistorial {
		try {
			Subject subject = SecurityUtils.getSubject();
			this.registrarNovedad("Eliminando", "Se dio de baja el elemento "+id, " novedadEliminar", (String) subject.getSession().getAttribute("correo"), null, id);
			elementoDAO.desactivarElemento(id);
		}catch (PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo dar de baja al elemento.");
		}catch(Exception e) {
			
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
	public void desactivarEquipo(Integer id) throws ExcepcionServiciosHistorial {
		try {
			System.out.println("hhhhhhhhhhhhhh");
			Subject subject = SecurityUtils.getSubject();
			this.registrarNovedad("Eliminado", "Se ha dado de baja el equipo "+id, "novedadEliminar", (String) subject.getSession().getAttribute("correo"), id, null);
			equipoDAO.desactivarEquipo(id);
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo dar de baja al equipo.");
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void exportarElementos() throws ExcepcionServiciosHistorial{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=elementos.xls");
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
			throw new ExcepcionServiciosHistorial("No se pudo consultar los equipos disponibles.");
		}
	}

	@Override
	public void cambiarIDElemento(Elemento elemento, String id) throws ExcepcionServiciosHistorial{
		try{
			Subject subject = SecurityUtils.getSubject();
			this.registrarNovedad("Cambio", "se cambio el id del elemento "+elemento.getId(), "novedadCambio", (String) subject.getSession().getAttribute("correo"), null, elemento.getId());
			this.elementoDAO.cambiarIDElemento(elemento,id);
		}catch(PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo cambiar el id del elemento.");
		}catch(Exception e) {
			
		}
	}

	@Override
	public void cambiarNombreElemento(Elemento elemento, String nombre) throws ExcepcionServiciosHistorial{
		try{
			Subject subject = SecurityUtils.getSubject();
			this.registrarNovedad("Cambio", "se cambio el nombre del elemento "+elemento.getId(), "novedadCambio", (String) subject.getSession().getAttribute("correo"), null, elemento.getId());
			this.elementoDAO.cambiarNombreElemento(elemento,nombre);
		}catch(PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo cambiar el nombre del elemento.");
		}catch(Exception e) {
			
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
		}catch (Exception e){

		}
	}	

	@Override
	public void actualizarIdLaboratorioEnEquipo(Integer idEquipo, Integer idLab) throws ExcepcionServiciosHistorial{
		try {
			System.out.println("en");
			Subject subject = SecurityUtils.getSubject();
			Laboratorio lab = laboratorioDAO.consultarLaboratorio(idLab);
			if(lab != null && lab.isActivo()) {
				System.out.println("en2");
				equipoDAO.actualizarIdLaboratorio(idLab, idEquipo);
				this.registrarNovedad("Asosiacion", "Se ha asosiado el equipo "+idEquipo+" al laboratorio "+lab.getNombre(), "novedadModificar", (String) subject.getSession().getAttribute("correo"), idEquipo, null);
			}else {
				throw new ExcepcionServiciosHistorial("No se pudo actualizar el idLaboratorio en el equipo.");
			}
		}catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo actualizar el idLaboratorio en el equipo.");
		}catch (Exception e){
			System.out.println("fallo");
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

	@Override
	public List<Laboratorio> consultarLaboratoriosDisponibles() throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarLaboratoriosDisponibles();
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("no se pudo consultar los laboratorios disponibles");
		}
	}

	@Override
	public Integer consultarSiguienteIdLaboratorio() throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarSiguienteIdLaboratorio();
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se ha podido consultar el siguiente id de laboratorios");
		}
	}

	@Override
	public List<Laboratorio> consultarLaboratorios() throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarLaboratorios();
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se han podido consultar los laboratorios");
		}
	}

	@Override
	public Integer consultarNumeroEquipos(Laboratorio laboratorio) throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarNumeroEquipos(laboratorio);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar el n�mero de equipos.");
		}
	}

	@Override
	public Date consultarFechaRegistro(Laboratorio laboratorio) throws ExcepcionServiciosHistorial {
		try{
			return laboratorioDAO.consultarFechaRegistro(laboratorio);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar la fecha de registro.");
		}
	}

	@Override
	public void eliminarLaboratorio(Laboratorio laboratorio) throws ExcepcionServiciosHistorial{
		try{
			Subject subject = SecurityUtils.getSubject();
			laboratorioDAO.eliminarLaboratorio(laboratorio);
			List<Equipo> lE =  equipoDAO.consultarEquiposDelLaboratorio(laboratorio);
			for(Equipo e: lE){
				this.registrarNovedad("Desasociacion", "Se ha desasociado el equipo "+e.getId()+" del laboratorio "+laboratorio.getNombre(), "novedadModificar", (String) subject.getSession().getAttribute("correo"), e.getId(), null);
				this.registrarNovedad("Asociacion", "Se ha asociado el equipo "+e.getId()+" al laboratorio "+null, "novedadModificar", (String) subject.getSession().getAttribute("correo"), e.getId(), null);
			}
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo eliminar el laboratorio.");
		}catch (Exception e){

		}
	}

	@Override
	public void desasociarLaboratorioDeEquipos(Laboratorio laboratorio) throws ExcepcionServiciosHistorial{
		try{
			laboratorioDAO.desasociarLaboratorioDeEquipos(laboratorio);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo desasociar.");
		}
	}

	@Override
	public void importarTablaLaboratorios() throws ExcepcionServiciosHistorial{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=laboratorios.xls");
		response.setContentType("application/vnd.ms-excel");
		try{
			HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
			HSSFSheet sheet = wb.createSheet("Laboratorios"); // crea hoja
			List<Laboratorio> laboratorios = this.consultarLaboratorios();
			int rownum = 0;
			int column = 0;
			HSSFRow row = sheet.createRow(rownum);
			HSSFCell celda = row.createCell(column);
			celda.setCellValue("NOMBRE");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("# EQUIPOS");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("ACTIVO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("FECHA CREACION");
			rownum = 1;
			for(Laboratorio l: laboratorios){
				column = 0;
				row = sheet.createRow(rownum);
				celda = row.createCell(column);
				celda.setCellValue(l.getNombre());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(this.consultarNumeroEquipos(l));
				column++;
				celda = row.createCell(column);
				celda.setCellValue(l.getActivo());
				column++;
				celda = row.createCell(column);
				celda.setCellValue(this.consultarFechaRegistro(l).toString());
				column++;
				rownum++;
			}
			OutputStream out = response.getOutputStream();
			wb.write(out);
		}catch (ExcepcionServiciosHistorial e){
			throw new ExcepcionServiciosHistorial("No se pudo exportar el excel de los Laboratorios");
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
	public Integer consultarEquiposEliminadosLaboratorio(Laboratorio laboratorio) throws ExcepcionServiciosHistorial{
		try{
			return laboratorioDAO.consultarEquiposEliminadosLaboratorio(laboratorio);
		}catch (PersistenceException e){
			throw new ExcepcionServiciosHistorial("No se pudo consultar los equipos del laboratorio.");
		}
	}

	@Override
	public void registrarNovedad(String titulo, String detalle, String clase, String usuario, Integer idEquipo, String idElemento) throws ExcepcionServiciosHistorial{
		try {
			if(idElemento != null) {
				Elemento elem = elementoDAO.consultarElemento(idElemento);
				if(elem != null) {
					Integer idEq = consultarEquipoDeElemento(elem);
					if(idEq != null) {
						novedadDAO.registrarNovedad(titulo, detalle, clase, usuario, idEq, null);
					}
					novedadDAO.registrarNovedad(titulo, detalle, clase, usuario, idEquipo, idElemento);
				}else {
					throw new ExcepcionServiciosHistorial("No se pudo registrar la novedad.");
				}
			}else if (idEquipo != null){
				Equipo eq = equipoDAO.consultarEquipo(idEquipo);
				if(eq != null) {
					novedadDAO.registrarNovedad(titulo, detalle, clase, usuario, idEquipo, idElemento);
				}else {
					novedadDAO.registrarNovedad(titulo, detalle, clase, usuario, idEquipo, idElemento);
				}
			}
		}catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcepcionServiciosHistorial("No se pudo registrar la novedad.");
		}
	}


	@Override
	public List<Novedad> consultarNovedades() throws ExcepcionServiciosHistorial {
		try {
			return novedadDAO.consultarNovedades();
		} catch(PersistenceException e) {
			throw new ExcepcionServiciosHistorial("No se pudo consultar las novedades.");
		}
	}

	@Override public void importarTablaNovedades() throws ExcepcionServiciosHistorial{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=novedades.xls");
		response.setContentType("application/vnd.ms-excel");
		try{
			HSSFWorkbook wb = new HSSFWorkbook(); // crea libro de excel
			HSSFSheet sheet = wb.createSheet("Laboratorios"); // crea hoja
			List<Novedad> novedades = this.consultarNovedades();
			int rownum = 0;
			int column = 0;
			HSSFRow row = sheet.createRow(rownum);
			HSSFCell celda = row.createCell(column);
			celda.setCellValue("TIPO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("ID ELEMENTO/EQUIPO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("ELEMENTO/EQUIPO");
			column++;
			celda = row.createCell(column);
			celda.setCellValue("USUARIO");
			column++;

			column++;
			celda = row.createCell(column);
			celda.setCellValue("TITULO");
			column++;

			column++;
			celda = row.createCell(column);
			celda.setCellValue("DETALLE");
			column++;




			celda = row.createCell(column);
			celda.setCellValue("FECHA");
			rownum = 1;
			for(Novedad novedad: novedades){
				column = 0;
				row = sheet.createRow(rownum);
				celda = row.createCell(column);
				celda.setCellValue(novedad.getTipo());
				column++;
				celda = row.createCell(column);

				if(novedad.getEquipo().getId()==null){
					celda.setCellValue(novedad.getElemento().getId());
				}else{
					celda.setCellValue(novedad.getEquipo().getId());
				}
				column++;
				celda = row.createCell(column);

				if(novedad.getEquipo().getId()==null){
					celda.setCellValue("Elemento");
				}else{
					celda.setCellValue("Equipo");
				}
				column++;
				celda = row.createCell(column);
				celda.setCellValue(novedad.getUsuario());
				column++;

				column++;
				celda = row.createCell(column);
				celda.setCellValue(novedad.getTitulo());
				column++;

				column++;
				celda = row.createCell(column);
				celda.setCellValue(novedad.getDetalle());
				column++;



				celda = row.createCell(column);
				celda.setCellValue(novedad.getFecha().toString());
				column++;
				rownum++;
			}
			OutputStream out = response.getOutputStream();
			wb.write(out);
		}catch (ExcepcionServiciosHistorial e){
			throw new ExcepcionServiciosHistorial("No se pudo exportar el excel de los Laboratorios");
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
	public List<Novedad> consultarNovedadesElemento(Elemento elemento) throws ExcepcionServiciosHistorial {
		try {
			return novedadDAO.consultarNovedadesElemento(elemento);
		}catch(PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Novedad> consultarNovedadesEquipo(Equipo equipo) throws ExcepcionServiciosHistorial {
		try {
			return novedadDAO.consultarNovedadesEquipo(equipo);
		}catch(PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

}
