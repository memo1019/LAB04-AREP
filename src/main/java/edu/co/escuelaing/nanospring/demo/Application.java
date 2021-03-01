package edu.co.escuelaing.nanospring.demo;

import edu.co.escuelaing.nanospring.NanoSpringApplication;

import java.io.IOException;

public class Application {
        public static void main(String[] args) throws ClassNotFoundException, IOException {

            String[] comp = {"edu.co.escuelaing.nanospring.demo.HelloWebService"};
            NanoSpringApplication.run(comp);
        }
}
