package edu.co.escuelaing.httpserver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

/**
 * clase para leer los archivos solicitados
 * @author Guillermo Castrocon modificaciones de Luis Benavides
 */
public class ResReqStatic {
    private String currentDir = "";

    /**
     * obtiene de los recursos los archivos solicitados
     * @param currentDir
     */
    public void setCurrentDir(String currentDir) {
        String defaultDir = "src/main/resources";
        this.currentDir = defaultDir +currentDir;
    }

    /**
     * lee e imprime los archivos estaticos
     * @param socket
     * @param resource
     * @param type
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getWebFile(Socket socket, String resource,String type) throws FileNotFoundException, IOException{
        File index = new File(currentDir+"/"+resource);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new FileReader(index));
        String s= getDefaultOkOutputHeader();

        if (!resource.contains(".js")){
            s+= "Content-Length: " + index.length() +"\r\n";
        }
        s +="\r\n";

        String line = reader.readLine();

        while (line != null){
            s += line+"\r\n";
            line = reader.readLine();
        }
        printWriter.println(s);
        reader.close();
        printWriter.close();

    }

    /***
     *obtiene la respuesta positiva para cuando imprime un archivo
     * @return String
     */

    private String getDefaultOkOutputHeader(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";

    }
    /**
     * usando websocket lee imprime la imagen estatica
     * @param socket
     * @param resource
     * @param idpng
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getWebFile(Socket socket, String resource,int idpng) throws FileNotFoundException, IOException{
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(currentDir+"/"+resource));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
            dataOutputStream.writeBytes( "HTTP/1.1 200 \r\n");
            dataOutputStream.writeBytes("Content-Type: image/jpg\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}