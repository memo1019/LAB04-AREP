package edu.co.escuelaing.nanospring;

import edu.co.escuelaing.httpserver.Handler;
import edu.co.escuelaing.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
/**
 * @Author Luis Benavidez con modificacion de Guillermo Castro
 * */
public class NanoSpringApplication implements Handler<String> {

    private static NanoSpringApplication _instance = new NanoSpringApplication();
    private boolean componentLoaded = false;
    private Map<String,Method> componentsRoutes = new HashMap();
    /**
     * @Author Luis Benavidez con modificacion de Guillermo Castro
     * */
    private NanoSpringApplication(){}
    /**
     *
     * Se corre el servidor web y se instancia el proceso
     * @param arg
     * */
    public static void run(String[] arg) throws ClassNotFoundException, IOException {
        if (!_instance.componentLoaded){
            _instance.loadComponents(arg);
            _instance.componentLoaded = true;
            _instance.startServer();
        }
    }
    /**
     * se inicia el servidor web ya creado
     * */
    private void startServer() throws IOException {
        HttpServer hserver = new HttpServer();
        hserver.registerHandler(this,"/nspapp");
        hserver.startServer();
    }
    /**
     * se encarga de cargar los componentes que ingresan de la clase run
     * @param components
     *
     * */

    private void loadComponents(String[] components) throws ClassNotFoundException {
        for(String classpath:components) {
            for (Method m : Class.forName(classpath).getMethods()) {
                if(m.isAnnotationPresent(RequestMapping.class)){
                    componentsRoutes.put(m.getAnnotation(RequestMapping.class).value(),m);
                }
            }
        }
    }
    /***
     * Se crea apartir de la implementacion de la clase Handle creada para la peticion y respuesta
     * @param path
     * @param req
     * @param res
     * @return String
     */

    @Override
    public String Handle(String path, HttpRequest req, HttpResponse res) {
        if(componentsRoutes.containsKey(path)){
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n" + Invoke(componentsRoutes.get(path));
        }
        return "HTTP/1.1 400 Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Error 404</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Error 404</h1>\n"
                + "<h3>URI not Found</h3>\n"
                + "</body>\n"
                +"<html>\n";
    }



    /**
     * invoca el metodo mediante la reflexion
     * @param StatictMethod
     * @return String
     *
     * */
    public static String Invoke(Method StatictMethod) {
        String resp="";
        try{
            resp = StatictMethod.invoke(null).toString();
        } catch (IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();

        }
        return resp;
    }
}
