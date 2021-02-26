package edu.co.escuelaing.httpserver;

import edu.co.escuelaing.Persistencia.ConexionJDBCBaseDeDatos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Luis Benavidez con modificacion de Guillermo Castro
 * */
public class HttpServer {
    private Map<String, Handler<String>> Handlers =new HashMap();
    private final ResReqStatic ResReqStatic = new ResReqStatic();

    public HttpServer(){
        super();
    }
    public void registerHandler(Handler<String> h , String prefix){
        Handlers.put(prefix,h);
        System.out.println("Adding handler with key: "+prefix);
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            boolean pathRead=false;
            String path ="";
            while ((inputLine = in.readLine()) != null) {
                if(!pathRead){
                    path=inputLine.split(" ")[1];
                    System.out.println("Path read : "+path);
                    if (path.contains("%22")){

                        String name = path.split("%22")[1].split("!")[0];

                        int num = Integer.parseInt(path.split("%22")[1].split("!")[1]);

                        ConexionJDBCBaseDeDatos.getInstance().Insertar(name,num);
                    }
                    pathRead=true;
                }
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            if(path.contains("/Apps")) {
                String sufix = path.split("/")[2];
                out.println(Handlers.get("/Apps").Handle("/" + sufix, null, null));
            }else if (path.contains("/escuelaing")){
                ResReqStatic.setCurrentDir("/");
                String resource = null;
                String type = null;
                if(path.contains(".html")){
                    resource = "operacion.html";
                    type = "html";
                }
                if (path.contains(".css")){
                    resource = "style.css";
                    type = "css";
                }
                if (path.contains(".js")){
                    resource = "Busqueda.js";
                    type = "javascript";
                }
                if (path.contains(".png")) {
                    try {
                        ResReqStatic.getWebFile(clientSocket,resource,0);
                    }catch(FileNotFoundException e){
                        out.println(getDefaultOkOutput());
                    }
                }
                if (resource != null){
                    ResReqStatic.getWebFile(clientSocket,resource,type)  ;
                }else{
                    out.println(getDefaultOkOutput());
                }
            }else{
                out.println(getDefaultOkOutput());
            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private String getDefaultOkOutput(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n" ;

    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e. on localhost);
    }
}



