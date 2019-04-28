package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import edu.eci.cvds.samples.entities.Laboratorio;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.ExcepcionServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorial;
import edu.eci.cvds.samples.services.ServiciosHistorialFactory;


@ManagedBean(name = "adminBean")
@ViewScoped
public class adminBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6362614236013128488L;
	private Integer equipo;
	private TipoElemento tipoSeleccionado;
	private String correo;
	private String idElemento;
	private String nombreElemento;
	private Integer laboratorioSeleccionado;
	private String monitorSeleccionado;
	private String mouseSeleccionado;
	private String torreSeleccionada;
	private String tecladoSeleccionado;
	private static final TipoElemento teclado = TipoElemento.TECLADO;
	private static final TipoElemento mouse = TipoElemento.MOUSE;
	private static final TipoElemento monitor = TipoElemento.MONITOR;
	private static final TipoElemento torre = TipoElemento.TORRE;
    @Inject
    private ServiciosHistorial serviciosHistorial;
	
	public adminBean() {
		Subject subject = SecurityUtils.getSubject();
		this.correo = (String) subject.getSession().getAttribute("correo");
		this.serviciosHistorial = ServiciosHistorialFactory.getInstance().getServiciosHistorial();
	}
    
	public void registrarElemento() throws ExcepcionServiciosHistorial {
		Elemento elemento = new Elemento(idElemento,tipoSeleccionado,nombreElemento,true);
		try {
			serviciosHistorial.registrarElemento(elemento,this.correo,this.equipo);
			this.mensajeCorrecto();
		}catch(Exception e) {
			e.printStackTrace();
			this.mensajeError();
		}
	}
	
	public List<Elemento> consultarElementos(){
		try {
			return this.serviciosHistorial.consultarElementos();
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Elemento> consultarElementosDisponibles(){
		try {
			return this.serviciosHistorial.consultarElementosDisponibles();
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
			return null;
		}
	}
	public List<Elemento> consultarElementosDisponibles(TipoElemento tipo){
		try{
			return this.serviciosHistorial.consultarElementosDisponibles(tipo);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}

	public Integer consultarEquipoDeElemento(Elemento elemento){
		try{
			return this.serviciosHistorial.consultarEquipoDeElemento(elemento);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void registrarEquipo() {
		try{
			Elemento torre = this.serviciosHistorial.consultarElemento(this.torreSeleccionada);
			Elemento mouse = this.serviciosHistorial.consultarElemento(this.mouseSeleccionado);
			Elemento teclado = this.serviciosHistorial.consultarElemento(this.tecladoSeleccionado);
			Elemento monitor = this.serviciosHistorial.consultarElemento(this.monitorSeleccionado);
			Laboratorio laboratorio = this.serviciosHistorial.consultarLaboratorio(this.laboratorioSeleccionado);
			ArrayList<Elemento> lista = new ArrayList<Elemento>();
			lista.add(torre);lista.add(mouse);lista.add(teclado);lista.add(monitor);
			Integer id = this.serviciosHistorial.proximoIdEquipo();
			Equipo equipo = new Equipo(id,lista,true);
			this.serviciosHistorial.registrarEquipo(equipo,laboratorio);
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}
	}

	public Laboratorio consultarLaboratorio(Equipo equipo){
		try{
			return this.serviciosHistorial.consultarLaboratorio(equipo);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}

	public void enlazarElemento(Elemento elemento){

	}

	public void eliminarElemento(Elemento elemento){

	}

	public void eliminarEquipo(Equipo equipo){

	}

	public String consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo){
		try{
			Elemento el = this.serviciosHistorial.consultarElementoDelEquipo(tipo,equipo);
			return el.getId();
		}catch ( ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}


	
	
	public void mensajeCorrecto() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado satisfactoriamente", "Elemento registrado satisfactoriamente"));
	}
	
	public void mensajeError() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! No se pudo registrar", "No se pudo registrar el elemento"));
	}

	public TipoElemento[] getTipoElementos() {
		return TipoElemento.values();
	}
	
	public TipoElemento getTipoSeleccionado() {
		return this.tipoSeleccionado;
	}
	
	public List<SelectItem> getEquiposSelectItem(){
		List<SelectItem> lis = new ArrayList<SelectItem>();
		try {
			List<Equipo> l = this.serviciosHistorial.consultarEquipos();
			for(Equipo e: l) {
				lis.add(new SelectItem(e.getId()));
			}
			return lis;
		} catch (ExcepcionServiciosHistorial e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
			return null;
		}
	}

	public List<Equipo> getEquipos(){
		try{
			return this.serviciosHistorial.consultarEquipos();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}

	public List<SelectItem> getEquiposDisponiblesParaElemento(Elemento elemento){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		try{
			List<Equipo> l = this.serviciosHistorial.consultarEquiposDisponiblesParaElemento(elemento.getTipo());
			for(Equipo e: l){
				lista.add(new SelectItem(e.getId()));
			}
			return lista;
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Laboratorio> laboratoriosDisponibles(){
		List<Laboratorio> lis = new ArrayList<Laboratorio>();
		lis.add(new Laboratorio(1,"laboratorio de redes",true));
		return lis;
	}

	public void actualizarLaboratorioDeEquipo(Equipo equipo){

	}

	public void actualizarTecladoDeEquipo(Equipo equipo){

	}

	public void actualizarMouseDeEquipo(Equipo equipo){

	}
	public void actualizarMonitorDeEquipo(Equipo equipo){

	}
	public void actualizarTorreDeEquipo(Equipo equipo){

	}
	
	public List<Elemento> monitoresDisponibles(){
		try{
			return this.serviciosHistorial.consultarElementosDisponibles(TipoElemento.MONITOR);
		}catch(ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}

	}
	
	public List<Elemento> mousesDisponibles(){
		try{
			return this.serviciosHistorial.consultarElementosDisponibles(TipoElemento.MOUSE);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Elemento> torresDisponibles(){
		try{
			return this.serviciosHistorial.consultarElementosDisponibles(TipoElemento.TORRE);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Elemento> tecladosDisponibles(){
		try{
			return this.serviciosHistorial.consultarElementosDisponibles(TipoElemento.TECLADO);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	//GETS Y SETS
	
	
	
	
	
	public Integer getEquipo() {
		return this.equipo;
	}
	public void setEquipo(Integer equipo) {
		if(equipo == -1) {
			equipo = null;
		}
		this.equipo = equipo;
	}
	
	public Integer getLaboratorioSeleccionado() {
		return this.laboratorioSeleccionado;
	}
	
	public void setTipoSeleccionado(TipoElemento s) {
		this.tipoSeleccionado = s;
	}
	
	public void setLaboratorioSeleccionado(Integer laboratorio) {
		this.laboratorioSeleccionado = laboratorio;
	}
	
	public void setMonitorSeleccionado(String monitor) {
		this.monitorSeleccionado = monitor;
	}
	
	public String getMonitorSeleccionado() {
		return this.monitorSeleccionado;
	}
	
	public String getMouseSeleccionado() {
		return this.mouseSeleccionado;
	}
	
	public void setMouseSeleccionado(String mouse) {
		this.mouseSeleccionado = mouse;
	}
	
	public void setTorreSeleccionada(String torre) {
		this.torreSeleccionada = torre;
	}
	public String getTorreSeleccionada() {
		return this.torreSeleccionada;
	}
	
	public String getTecladoSeleccionado() {
		return this.tecladoSeleccionado;
	}
	
	public void setTecladoSeleccionado(String teclado) {
		this.tecladoSeleccionado = teclado;
	}

	public String getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(String idElemento) {
		this.idElemento = idElemento;
	}

	public String getNombreElemento() {
		return nombreElemento;
	}

	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}

	public TipoElemento getTeclado(){
		return this.teclado;
	}
	public TipoElemento getMouse(){
		return this.mouse;
	}
	public TipoElemento getMonitor(){
		return this.monitor;
	}
	public TipoElemento getTorre(){
		return this.torre;
	}
	

	
}
