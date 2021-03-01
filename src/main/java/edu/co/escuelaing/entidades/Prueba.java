package edu.co.escuelaing.entidades;

public class Prueba {/**
 * @author Guillermo Castro
 * */
    private String Nombre;
    private String Descripcion;

    public Prueba(){}

    public Prueba(String Nombre, String Descripcion) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }
    public String getDescripcion() {
        return Descripcion;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
