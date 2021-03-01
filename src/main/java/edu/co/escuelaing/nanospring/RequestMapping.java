package edu.co.escuelaing.nanospring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Author Luis Benavidez
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 *
 * se encarga de realizar el mapeo de la conexion del framework
 * */
public @interface RequestMapping {
    public String value();


}
