package edu.co.escuelaing.entidades;

public class Busqueda {/**
 * @author Guillermo Castro
 * */
    private String Nombre;
    private int Edad;

    public Busqueda(){}

    public Busqueda(String Nombre, int Edad) {
        this.Nombre = Nombre;
        this.Edad = Edad;
    }

    public String getNombre() {
        return Nombre;
    }
    public int getEdad() {
        return Edad;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
}
