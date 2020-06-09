package com.pabloLopez.cinesypelis.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Pelicula implements Parcelable {
    public static final String TAG="Pelicula";
    @PrimaryKey
    @NonNull
    String nombre;
    @NonNull
    String genero;
    @NonNull
    double duracion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    //Constructor
    public Pelicula(String nombre, String genero, double duracion) {
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
    }

    @Ignore
    public Pelicula(){

    }
    //region implementado por la interfaz Parcelable
    protected Pelicula(Parcel in) {
        nombre = in.readString();
        genero = in.readString();
        duracion = in.readDouble();
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(genero);
        dest.writeDouble(duracion);
    }
    //endregion
}
