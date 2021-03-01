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

public class NanoSpringApplication implements Handler<String> {

    private static NanoSpringApplication _instance = new NanoSpringApplication();
    private boolean componentLoaded = false;
    private Map<String,Method> componentsRoutes = new HashMap();

    private NanoSpringApplication(){}

    public static void run(String[] arg) throws ClassNotFoundException, IOException {
        if (!_instance.componentLoaded){
            _instance.loadComponents(arg);
            _instance.componentLoaded = true;
            _instance.startServer();
        }
    }

    private void startServer() throws IOException {
        HttpServer hserver = new HttpServer();
        hserver.registerHandler(this,"/nspapp");
        hserver.startServer();
    }

    private void loadComponents(String[] components) throws ClassNotFoundException {
        for(String classpath:components) {
            for (Method m : Class.forName(classpath).getMethods()) {
                if(m.isAnnotationPresent(RequestMapping.class)){
                    componentsRoutes.put(m.getAnnotation(RequestMapping.class).value(),m);
                }
            }
        }
    }
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
