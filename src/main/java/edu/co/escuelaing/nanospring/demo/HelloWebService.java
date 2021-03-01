package edu.co.escuelaing.nanospring.demo;

import edu.co.escuelaing.Persistencia.ConexionJDBCBaseDeDatos;
import edu.co.escuelaing.entidades.Prueba;
import edu.co.escuelaing.nanospring.RequestMapping;

public class HelloWebService {

        @RequestMapping("/Prueba")
        public static String index(){
            return  getDataChange();
        }

        /**
         *
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
