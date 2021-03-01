package edu.co.escuelaing.nanospring.demo;

import edu.co.escuelaing.nanospring.NanoSpringApplication;

import java.io.IOException;
/**
 * @Author Luis Benavidez con modificacion de Guillermo Castro
 * */
public class Application {
    /**
     * se corre el servidor web con las modificacion realizadas para crear el Spring
     * @param args
     * */
        public static void main(String[] args) throws ClassNotFoundException, IOException {

            String[] comp = {"edu.co.escuelaing.nanospring.demo.HelloWebService"};
            NanoSpringApplication.run(comp);
        }
}
