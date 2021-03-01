package edu.co.escuelaing.Persistencia;
import edu.co.escuelaing.entidades.Prueba;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Guillermo Castro
 * */
public class ConexionJDBCBaseDeDatos {

    /**
     * Retorna la instanciacion de la conexion a la base de datos.
     * @return - ConexionJDBCBaseDeDatos.
     */
    public static ConexionJDBCBaseDeDatos getInstance() { return instance; }

    /**
     * Retorna la conexion a la base de datos.
     * @return - conexion.
     */
    public static Connection getConexion() { return conexion; }
    public  Connection getConexion3() { return conexion; }

    private static ConexionJDBCBaseDeDatos instance = new ConexionJDBCBaseDeDatos();
    private static Connection conexion;
    static Statement Estado = null;
    public ConexionJDBCBaseDeDatos(){ }

    /**
     * Obtiene los elementos de la base de datos que se encuentra guardados
     *
     * */
    public List<Prueba> Obtener(){
        List<Prueba> Pruebas = new ArrayList<>();
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexion = DriverManager.getConnection("jdbc:postgresql://ec2-54-242-43-231.compute-1.amazonaws.com:5432/d7k5ebt8vo546h", "iqugxjjflyecxa", "d5b2bb3e476769ec2d55dbdea9ec44d18ac75b8d6747932c9334d2228fd2de8e");
            conexion.setAutoCommit(false);
            Estado = conexion.createStatement();
            ResultSet rs = Estado.executeQuery( "SELECT * FROM PRUEBA;" );
            while ( rs.next() ) {
                String  Nombre = rs.getString("Nombre");
                String Descripcion = rs.getString("Descripcion");
                Pruebas.add(new Prueba(Nombre,Descripcion));
            }
            rs.close();
            Estado.close();
            conexion.close();
            return Pruebas;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return Pruebas;
    }
    /**
     * crea la conexion a la base de datos creada en heroku y la inserta
     * @param descripcion
     * @param nombre
     *
     * */
    public static void Insertar(String nombre,String descripcion) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexion = DriverManager.getConnection("jdbc:postgresql://ec2-54-242-43-231.compute-1.amazonaws.com:5432/d7k5ebt8vo546h", "iqugxjjflyecxa", "d5b2bb3e476769ec2d55dbdea9ec44d18ac75b8d6747932c9334d2228fd2de8e");
            conexion.setAutoCommit(false);
            Estado = conexion.createStatement();
            String sql = "INSERT INTO PRUEBA(Nombre,Descripcion) "
                    + "VALUES ('"+nombre+"',"+descripcion+");";
            Estado.executeUpdate(sql);
            Estado.close();
            conexion.commit();
            conexion.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }


}
