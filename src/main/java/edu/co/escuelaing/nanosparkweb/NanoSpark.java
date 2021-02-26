package edu.co.escuelaing.nanosparkweb;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.BiFunction;
import java.util.function.Supplier;
/**
 * @author Luis Benavidez con modificacion de Guillermo Castro
 * */
public class NanoSpark {
    /**
     * aca se instancia el servidor para mandar el path y las funciones que va a leer el servidor
     * @param bifunction obtiene las acciones de Request and Response y da un String como resultado
     * @param path obtiene el pach que manda el servidor
     */
    public static void get(String path, BiFunction<HttpRequest, HttpResponse,String> bifunction){
        NanoSparkServer nanosvr = NanoSparkServer.getInstance();
        nanosvr.get(path,bifunction);
    }
    /**
     *
     * inicia el servidor que se instacia
     */
    public static void startServer(){
        NanoSparkServer nanosvr =NanoSparkServer.getInstance();
        nanosvr.startServer();

    }
}
