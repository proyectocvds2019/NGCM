package edu.eci.cvds.samples.entities;

public class Laboratorio {

    private Integer id;
    private String nombre;
    private boolean activo;

    public Laboratorio(Integer id, String nombre, boolean activo){
        setActivo(activo);
        setId(id);
        setNombre(nombre);
    }
    public Laboratorio(){

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
