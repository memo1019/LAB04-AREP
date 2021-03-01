package edu.co.escuelaing.nanospring.demo;

import edu.co.escuelaing.Persistencia.ConexionJDBCBaseDeDatos;
import edu.co.escuelaing.entidades.Prueba;
import edu.co.escuelaing.nanospring.RequestMapping;
/**
 * @Author Luis Benavidez con modificacion de Guillermo Castro
 * */
public class HelloWebService {

        @RequestMapping("/Prueba")
        /**
         * obtiene los datos que se presentan gracias a la conexion de la base creada
         * @return String
         */
        public static String getDataChange(){
            String lista = "";
            for (Prueba c: ConexionJDBCBaseDeDatos.getInstance().Obtener()){
                lista += c.getNombre()+"#"+c.getDescripcion()+"%";
            }
            return lista;
        }

}
