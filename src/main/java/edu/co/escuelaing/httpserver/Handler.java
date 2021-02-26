package edu.co.escuelaing.httpserver;


import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * @author Luis Benavidez con modificacion de Guillermo Castro
 * */
public interface Handler<T> {
    public T Handle(String path, HttpRequest req, HttpResponse res);

}
