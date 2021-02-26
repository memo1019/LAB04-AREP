package edu.co.escuelaing.nanosparkweb;

import edu.co.escuelaing.httpserver.Handler;
import edu.co.escuelaing.httpserver.HttpServer;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Luis Benavidez con modificacion de Guillermo Castro
 * */
public class NanoSparkServer implements Handler<String> {
    private final String BadRequest = "HTTP/1.1 400 Not Found\r\n"
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

    private final String OkHeader = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    private static NanoSparkServer _theinstance =new NanoSparkServer();

    private HttpServer hserver= new HttpServer();


    public static NanoSparkServer getInstance() {
        return _theinstance;
    }
    private Map<String, BiFunction<HttpRequest, HttpResponse,String>> bodyMap=new HashMap();

    private NanoSparkServer(){
        hserver.registerHandler(this,"/Apps");

    }

    public void startServer() {
        try{
            hserver.startServer();
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void get(String path, BiFunction<HttpRequest, HttpResponse,String> bifunction) {
        bodyMap.put(path,bifunction);
    }
    public String getValue(String path,HttpRequest req,HttpResponse res){
        if (bodyMap.containsKey(path)){
            return OkHeader + bodyMap.get(path).apply(req , res);
        }else {
            return BadRequest;
        }
    }

    @Override
    public String Handle(String path,HttpRequest req,HttpResponse res) {
        return getValue(path,req,res);
    }
}
