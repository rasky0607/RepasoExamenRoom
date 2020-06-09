package com.pabloLopez.cinesypelis.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"nombre","pelicula","fecha"})
public class Cine{
    @NonNull
    String nombre;
    @NonNull
    String pelicula;
    @NonNull
    String fecha;
    @NonNull
    String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //Constructor
    public Cine(String nombre,String pelicula, String fecha, String direccion) {
        this.nombre = nombre;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.direccion = direccion;
    }

    @Ignore
    public Cine() {

    }

}
