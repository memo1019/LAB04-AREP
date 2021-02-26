package edu.co.escuelaing.nanosparkweb.demo;

import edu.co.escuelaing.Persistencia.ConexionJDBCBaseDeDatos;
import edu.co.escuelaing.entidades.Busqueda;
/**
 * @author Luis Benavidez con modificacion de Guillermo Castro
 * */

import java.sql.SQLException;

import static edu.co.escuelaing.nanosparkweb.NanoSpark.*;
public class NanoSparkWebDemo {

    public static void main(String [] args )  throws SQLException {
        get("/Busqueda",(req , res) -> Datos());
        startServer();
    }
    public static String Datos(){
        String list = "";
        for (Busqueda c: ConexionJDBCBaseDeDatos.getInstance().Obtener()){
            list += c.getNombre()+"#"+c.getEdad()+"%";
        }
        return list;
    }

}
