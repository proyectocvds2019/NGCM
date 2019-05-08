package edu.eci.cvds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

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
@RequestScoped
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
	private String nombreLaboratorio;
	private String mouseSeleccionado;
	private List<TipoElemento> tipoElementos = new ArrayList<TipoElemento>();
	private String torreSeleccionada;
	private String tecladoSeleccionado;
	private String cambioIDElemento;
	private String cambioNombreElemento;
	private List<Elemento> listaElementos;
	private List<Elemento> listaElementosDisponibles;
	private List<Equipo> listaEquipos;
	private List<Equipo> listaEquiposActivos;
	private List<String> listaEquiposRegistrarLaboratorio;
	private List<Equipo> listaEquiposSeleccionados;
	private List<Elemento> listaElementosSeleccionados;
	private List<Equipo> equiposSeleccionados;
	private Elemento enlazarEquipoSeleccionado;
	private List<TipoElemento> elementosSeleccionadoPorEquipo;
	private static final TipoElemento teclado = TipoElemento.TECLADO;
	private static final TipoElemento mouse = TipoElemento.MOUSE;
	private static final TipoElemento monitor = TipoElemento.MONITOR;
	private static final TipoElemento torre = TipoElemento.TORRE;
    @Inject
    private ServiciosHistorial serviciosHistorial;
	
	public adminBean() {
	    this.recargar();
	}

	public void recargar(){
		for(TipoElemento t: TipoElemento.values()){
	        this.tipoElementos.add(t);
        }
		Subject subject = SecurityUtils.getSubject();
		this.correo = (String) subject.getSession().getAttribute("correo");
		this.serviciosHistorial = ServiciosHistorialFactory.getInstance().getServiciosHistorial();
		try{
			this.listaElementos =  this.serviciosHistorial.consultarElementos();
			this.listaElementosDisponibles = this.serviciosHistorial.consultarElementosDisponibles();
			this.listaEquipos = this.serviciosHistorial.consultarEquipos();
			this.listaEquiposActivos = this.serviciosHistorial.consultarEquiposDisponibles();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
		}
	}
    
	public void registrarElemento(){
		try{
			Elemento elemento = new Elemento(idElemento,tipoSeleccionado,nombreElemento,true);
			serviciosHistorial.registrarElemento(elemento,this.correo,this.equipo);
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch (Exception e){
			e.printStackTrace();
			this.mensajeError();
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
	
	public void registrarEquipo(){
		try{
			Elemento torre = this.serviciosHistorial.consultarElemento(this.torreSeleccionada);
			Elemento mouse = this.serviciosHistorial.consultarElemento(this.mouseSeleccionado);
			Elemento teclado = this.serviciosHistorial.consultarElemento(this.tecladoSeleccionado);
			Elemento monitor = this.serviciosHistorial.consultarElemento(this.monitorSeleccionado);
			Laboratorio laboratorio = this.serviciosHistorial.consultarLaboratorio(this.laboratorioSeleccionado);
			if(this.laboratorioSeleccionado==-1) laboratorio = new Laboratorio();
			System.out.println(laboratorio.getId());
			ArrayList<Elemento> lista = new ArrayList<Elemento>();
			lista.add(torre);lista.add(mouse);lista.add(teclado);lista.add(monitor);
			Integer id = this.serviciosHistorial.proximoIdEquipo();
			Equipo equipo = new Equipo(id,lista,true);
			this.serviciosHistorial.registrarEquipo(equipo,laboratorio);
			this.mensajeCorrecto();
		}catch(ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}

	}
	public  void registrarLaboratorio(){
		this.mensajeCorrecto();
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
		try{
			System.out.println(elemento.getId()+" "+this.equipo);
			this.serviciosHistorial.actualizarIdEquipoEnElemento(elemento.getId(),this.equipo);
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
		}
	}

	public void eliminarElementos(){
		try{
			for(Elemento e: this.listaElementosSeleccionados){
				if(this.serviciosHistorial.consultarEquipoDeElemento(e) != null){
					this.mensajeError();
					return;
				}
			}
			for(Elemento e: this.listaElementosSeleccionados){
				this.serviciosHistorial.desactivarElemento(e.getId());
			}
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			this.mensajeError();
			e.printStackTrace();
		}
	}

	public void eliminarEquipos(){
		try{
			if(this.equiposSeleccionados.size() == 1){
				if(this.equiposSeleccionados.get(0) != null){
	                for(Elemento elemento:this.equiposSeleccionados.get(0).getElementos()){
	                    this.serviciosHistorial.actualizarIdEquipoEnElemento(elemento.getId(),null);
	                    for(TipoElemento tipo : this.elementosSeleccionadoPorEquipo){
	                        if(elemento.getTipo().name().equals(tipo.name())){
	                            this.serviciosHistorial.desactivarElemento(elemento.getId());
	                        }
	                    }
	                }
	                this.serviciosHistorial.desactivarEquipo(this.equiposSeleccionados.get(0).getId());
		        }
			}else{
				for(Equipo e: this.equiposSeleccionados){
					for(Elemento el: e.getElementos()){
						this.serviciosHistorial.actualizarIdEquipoEnElemento(el.getId(),null);
						
					}
					this.serviciosHistorial.desactivarEquipo(e.getId());
				}
			}
			this.recargar();
		}catch(ExcepcionServiciosHistorial e){
			e.printStackTrace();
		}
		
	    
	}

	public String consultarElementoDelEquipo(TipoElemento tipo, Equipo equipo){
		try{
			Elemento el = this.serviciosHistorial.consultarElementoDelEquipo(tipo,equipo);
			if(el==null){
				return null;
			}
			return el.getId();
		}catch ( ExcepcionServiciosHistorial e){
			e.printStackTrace();
			return null;
		}
	}


	
	
	public void mensajeCorrecto() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado satisfactoriamente", "Elemento registrado satisfactoriamente"));
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	public void mensajeError() {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("mensajeSolicitudAjax",true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! No se pudo registrar", "No se pudo registrar el elemento"));
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
	}
	
	public TipoElemento getTipoSeleccionado() {
		return this.tipoSeleccionado;
	}

	/*public List<SelectItem> getEquiposDisponiblesParaElemento(Elemento elemento){
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
	}*/

	// FALTA CORREGIR ESTE MÉTODO
	public List<Laboratorio> laboratoriosDisponibles(){
		List<Laboratorio> lis = new ArrayList<Laboratorio>();
		lis.add(new Laboratorio(1,"laboratorio de redes",true));
		return lis;
	}

	public void actualizarLaboratorioDeEquipo(Equipo equipo){

	}

	public void actualizarTecladoDeEquipo(Equipo equipo){
		try{
			System.out.println(this.tecladoSeleccionado+" "+equipo.getId());
			this.serviciosHistorial.actualizarIdEquipoEnElemento(this.tecladoSeleccionado,equipo.getId());
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch(Exception e){
			this.mensajeError();
			e.printStackTrace();
		}
	}

	public void actualizarMouseDeEquipo(Equipo equipo){
		try{
			this.serviciosHistorial.actualizarIdEquipoEnElemento(this.mouseSeleccionado, equipo.getId());
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch(Exception e){
			e.printStackTrace();
			this.mensajeError();
		}
	}
	public void actualizarMonitorDeEquipo(Equipo equipo){
		try{
			this.serviciosHistorial.actualizarIdEquipoEnElemento(this.monitorSeleccionado,equipo.getId());
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch(Exception e){
			e.printStackTrace();
			this.mensajeError();
		}
	}
	public void actualizarTorreDeEquipo(Equipo equipo){
		try{
			this.serviciosHistorial.actualizarIdEquipoEnElemento(this.torreSeleccionada,equipo.getId());
			this.mensajeCorrecto();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch(Exception e){
			e.printStackTrace();
			this.mensajeError();
		}
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

	public void esportarExcel(){
		try{
			this.serviciosHistorial.exportarExcelEquipos();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
		}

	}

	public void exportarElementos(){
		try {
			this.serviciosHistorial.exportarElementos();
		}catch (ExcepcionServiciosHistorial e){
			e.printStackTrace();
		}
	}
	
	public void CambiarIDElemento(Elemento elemento){
		try{
			this.serviciosHistorial.cambiarIDElemento(elemento, this.cambioIDElemento);
			this.mensajeCorrecto();
		}catch(ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch(Exception e){
			e.printStackTrace();
			this.mensajeError();
		}
	}

	public void cambiarNombreElemento(Elemento elemento){
		try{
			this.serviciosHistorial.cambiarNombreElemento(elemento, this.cambioNombreElemento);
			this.mensajeCorrecto();
		}catch(ExcepcionServiciosHistorial e){
			e.printStackTrace();
			this.mensajeError();
		}catch(Exception e){
			e.printStackTrace();
			this.mensajeError();
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
	public List<Elemento> getListaElementos(){
		return this.listaElementos;
	}

	public void setListaElementos(List<Elemento> listaElementos){
		this.listaElementos = listaElementos;
	}

	public List<Elemento> getListaElementosDisponibles(){
		return this.listaElementosDisponibles;
	}
	public void setListaElementosDisponibles(List<Elemento> listaElementosDisponibles){
		this.listaElementosDisponibles = listaElementosDisponibles;
	}

	public  List<Equipo> getListaEquipos(){
		return this.listaEquipos;
	}

	public  void setListaEquipos(List<Equipo> listaEquipos){
		this.listaEquipos = listaEquipos;
	}

	public List<Elemento> getListaElementosSeleccionados(){
		return this.listaElementosSeleccionados;
	}
	public void setListaElementosSeleccionados(List<Elemento> listaElementosSeleccionados){
		this.listaElementosSeleccionados = listaElementosSeleccionados;
	}


	public List<Equipo> getEquiposSeleccionados() {
		return equiposSeleccionados;
	}

	public void setEquiposSeleccionados(List<Equipo> equiposSeleccionados) {
		this.equiposSeleccionados = equiposSeleccionados;
	}

    public List<TipoElemento> getElementosSeleccionadoPorEquipo() {
        return elementosSeleccionadoPorEquipo;
    }

    public void setElementosSeleccionadoPorEquipo(List<TipoElemento> elementosSeleccionadoPorEquipo) {
        this.elementosSeleccionadoPorEquipo = elementosSeleccionadoPorEquipo;
    }
    public List<TipoElemento> getTipoElementos() {
        return this.tipoElementos;
    }
    public void setTipoElementos(List<TipoElemento> tipoElementos){
        this.tipoElementos = tipoElementos;
    }

	public List<Equipo> getListaEquiposSeleccionados() {
		return listaEquiposSeleccionados;
	}

	public void setListaEquiposSeleccionados(List<Equipo> listaEquiposSeleccionados) {
		this.listaEquiposSeleccionados = listaEquiposSeleccionados;
	}

	public List<Equipo> getListaEquiposActivos() {
		return listaEquiposActivos;
	}

	public void setListaEquiposActivos(List<Equipo> listaEquiposActivos) {
		this.listaEquiposActivos = listaEquiposActivos;
	}

	public List<String> getListaEquiposRegistrarLaboratorio() {
		return listaEquiposRegistrarLaboratorio;
	}

	public void setListaEquiposRegistrarLaboratorio(List<String> listaEquiposRegistrarLaboratorio) {
		this.listaEquiposRegistrarLaboratorio = listaEquiposRegistrarLaboratorio;
	}

	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio = nombreLaboratorio;
	}
	public String getNombreLaboratorio(){
		return this.nombreLaboratorio;
	}

	public String getCambioIDElemento(){
		return this.cambioIDElemento;
	}

	public void setCambioIDElemento(String cambioIDElemento){
		this.cambioIDElemento = cambioIDElemento;
	}

	public String getCambioNombreElemento(){
		return this.cambioNombreElemento;
	}

	public void setCambioNombreElemento(String cambioNombreElemento){
		this.cambioNombreElemento = cambioNombreElemento;
	}

	public Elemento getEnlazarEquipoSeleccionado() {
		return enlazarEquipoSeleccionado;
	}

	public void setEnlazarEquipoSeleccionado(Elemento enlazarEquipoSeleccionado) {
		this.enlazarEquipoSeleccionado = enlazarEquipoSeleccionado;
	}
}
